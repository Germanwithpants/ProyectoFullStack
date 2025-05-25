package com.proyecto._d.springbootcrud.springboot_crud_pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringbootCrudPedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudPedidosApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
    return new RestTemplate();
}

}
