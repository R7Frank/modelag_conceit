package com.r7frank.modelag_conceit;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.r7frank.modelag_conceit.domain.Categoria;
import com.r7frank.modelag_conceit.domain.Cidade;
import com.r7frank.modelag_conceit.domain.Cliente;
import com.r7frank.modelag_conceit.domain.Endereco;
import com.r7frank.modelag_conceit.domain.Estado;
import com.r7frank.modelag_conceit.domain.Produto;
import com.r7frank.modelag_conceit.domain.enums.TipoCliente;
import com.r7frank.modelag_conceit.repositories.CategoriaRepository;
import com.r7frank.modelag_conceit.repositories.CidadeRepository;
import com.r7frank.modelag_conceit.repositories.ClienteRepository;
import com.r7frank.modelag_conceit.repositories.EnderecoRepository;
import com.r7frank.modelag_conceit.repositories.EstadoRepository;
import com.r7frank.modelag_conceit.repositories.ProdutoRepository;

@SpringBootApplication
public class ModelagConceitApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRep;
	@Autowired
	private ProdutoRepository prodRep;
	@Autowired
	private EstadoRepository estadoRep;
	@Autowired
	private CidadeRepository cidadeRep;
	@Autowired
	private ClienteRepository clienteRep;
	@Autowired
	private EnderecoRepository enderecoRep;
	

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
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		catRep.saveAll(Arrays.asList(cat1, cat2));
		prodRep.saveAll(Arrays.asList(p1, p2, p3));	
		
		estadoRep.saveAll(Arrays.asList(est1, est2));
		cidadeRep.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRep.saveAll(Arrays.asList(cli1));
		enderecoRep.saveAll(Arrays.asList(e1, e2));
		
	}

}
