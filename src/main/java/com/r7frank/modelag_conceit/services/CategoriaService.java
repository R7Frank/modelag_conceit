package com.r7frank.modelag_conceit.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r7frank.modelag_conceit.domain.Categoria;
import com.r7frank.modelag_conceit.repositories.CategoriaRepository;
import com.r7frank.modelag_conceit.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository catRep;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = catRep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "
		+ id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return catRep.save(obj);
	}
}
