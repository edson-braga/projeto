package br.com.braga.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.braga.pedidos"})
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
