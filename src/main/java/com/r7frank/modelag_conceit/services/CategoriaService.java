package com.r7frank.modelag_conceit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.r7frank.modelag_conceit.domain.Categoria;
import com.r7frank.modelag_conceit.dto.CategoriaDTO;
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

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return catRep.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			catRep.deleteById(id);
	}
		catch(DataIntegrityViolationException e) {
			throw new com.r7frank.modelag_conceit.services.exceptions.DataIntegrityViolationException("Não é permitido excluir uma categoria que contenha produtos!");
		}
	}

	public List<Categoria> findAll() {
		return catRep.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return catRep.findAll(pageRequest);
		
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
		
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
	
}
