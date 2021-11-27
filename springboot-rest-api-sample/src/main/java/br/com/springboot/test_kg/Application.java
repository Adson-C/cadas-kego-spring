package br.com.springboot.test_kg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "br.com.springboot.test_kg.model") 
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
    	
        SpringApplication.run(Application.class, args);
    }
}
