package com.r7frank.modelag_conceit;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.r7frank.modelag_conceit.domain.Categoria;
import com.r7frank.modelag_conceit.repositories.CategoriaRepository;

@SpringBootApplication
public class ModelagConceitApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRep;

	public static void main(String[] args) {
		SpringApplication.run(ModelagConceitApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		catRep.saveAll(Arrays.asList(cat1, cat2));
		
	}

}
