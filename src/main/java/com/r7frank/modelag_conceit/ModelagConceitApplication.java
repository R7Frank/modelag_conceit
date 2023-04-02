package com.r7frank.modelag_conceit;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.r7frank.modelag_conceit.domain.Categoria;
import com.r7frank.modelag_conceit.domain.Produto;
import com.r7frank.modelag_conceit.repositories.CategoriaRepository;
import com.r7frank.modelag_conceit.repositories.ProdutoRepository;

@SpringBootApplication
public class ModelagConceitApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRep;
	@Autowired
	private ProdutoRepository prodRep;

	public static void main(String[] args) {
		SpringApplication.run(ModelagConceitApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
	
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		catRep.saveAll(Arrays.asList(cat1, cat2));
		prodRep.saveAllAndFlush(Arrays.asList(p1, p2, p3));	
		
	}

}
