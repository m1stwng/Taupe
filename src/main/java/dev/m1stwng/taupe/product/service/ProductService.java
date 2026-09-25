package dev.m1stwng.taupe.product.service;

import dev.m1stwng.taupe.product.dto.request.CreateProductRequest;
import dev.m1stwng.taupe.product.dto.request.UpdateProductRequest;
import dev.m1stwng.taupe.product.dto.response.ProductResponse;
import dev.m1stwng.taupe.product.entity.Product;
import dev.m1stwng.taupe.product.mapper.ProductMapper;
import dev.m1stwng.taupe.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
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
        return productRepository
                .findById(id)
                .map(productMapper::toResponse)
                .orElse(null);
    }

    public ProductResponse create(CreateProductRequest request) {
        final Product product = Product.builder()
                .sku(request.sku())
                .name(request.name())
                .build();

        return productMapper.toResponse(productRepository.save(product));
    }

    public ProductResponse update(UUID id, UpdateProductRequest request) {
        final Product product = productRepository
                .findById(id)
                .orElse(null);

        if (product == null) {
            return null;
        }

        product.setSku(request.sku());
        product.setName(request.name());

        return productMapper.toResponse(productRepository.save(product));
    }

    public void delete(UUID id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }
}
