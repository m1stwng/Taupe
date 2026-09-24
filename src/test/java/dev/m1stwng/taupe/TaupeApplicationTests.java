package dev.m1stwng.taupe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TaupeApplicationTests {

	@Test
	void contextLoads() {
	}

}
