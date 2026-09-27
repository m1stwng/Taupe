package dev.m1stwng.taupe;

import org.springframework.boot.SpringApplication;

public class TestTaupeApplication {

	public static void main(String[] args) {
		SpringApplication.from(TaupeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
