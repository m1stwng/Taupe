package dev.m1stwng.taupe.product.service;

import dev.m1stwng.taupe.product.dto.request.CreateProductRequest;
import dev.m1stwng.taupe.product.dto.request.UpdateProductRequest;
import dev.m1stwng.taupe.product.dto.response.ProductResponse;
import dev.m1stwng.taupe.product.entity.Product;
import dev.m1stwng.taupe.product.exception.DuplicateSkuException;
import dev.m1stwng.taupe.product.exception.ProductNotFoundException;
import dev.m1stwng.taupe.product.mapper.ProductMapper;
import dev.m1stwng.taupe.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public List<ProductResponse> findAll() {
        return productRepository
                .findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    public ProductResponse findById(UUID id) {
        return productRepository.findById(id)
                .map(productMapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public ProductResponse create(CreateProductRequest request) {
        if (productRepository.existsBySku(request.sku())) {
            throw new DuplicateSkuException(request.sku());
        }

        final Product product = Product.builder()
                .sku(request.sku())
                .name(request.name())
                .build();

        return productMapper.toResponse(productRepository.save(product));
    }

    public ProductResponse update(UUID id, UpdateProductRequest request) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        if (!product.getSku().equals(request.sku()) && productRepository.existsBySku(request.sku())) {
            throw new DuplicateSkuException(request.sku());
        }

        product.setSku(request.sku());
        product.setName(request.name());

        return productMapper.toResponse(productRepository.save(product));
    }

    public void delete(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }

        productRepository.deleteById(id);
    }
}
