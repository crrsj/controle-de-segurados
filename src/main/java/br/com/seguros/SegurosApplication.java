package br.com.seguros;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API - Controle De Segurados",
				version = "1.0",
				description = " Sistema de gestão para controle de segurados",
				contact = @Contact()
		)
)
public class SegurosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SegurosApplication.class, args);
	}

}
