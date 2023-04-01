package com.r7frank.modelag_conceit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r7frank.modelag_conceit.domain.Categoria;
import com.r7frank.modelag_conceit.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository catRep;

	public Optional<Categoria> buscar(Integer id) {
		
		Optional<Categoria> obj = catRep.findById(id);
		
		return obj;
	}
	
	public List<Categoria> listar() {
		
		return catRep.findAll();
	}
}