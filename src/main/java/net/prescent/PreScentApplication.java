package net.prescent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class PreScentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreScentApplication.class, args);
	}
}
