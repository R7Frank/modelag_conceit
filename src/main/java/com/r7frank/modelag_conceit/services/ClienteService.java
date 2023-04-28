package com.r7frank.modelag_conceit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.r7frank.modelag_conceit.domain.Cliente;
import com.r7frank.modelag_conceit.dto.ClienteDTO;
import com.r7frank.modelag_conceit.repositories.ClienteRepository;
import com.r7frank.modelag_conceit.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository cliRepos;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = cliRepos.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "
		+ id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return cliRepos.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			cliRepos.deleteById(id);
	}
		catch(DataIntegrityViolationException e) {
			throw new com.r7frank.modelag_conceit.services.exceptions.DataIntegrityViolationException("Não é permitido excluir um cliente que contenha entidades relacionadas a ele!");
		}
	}

	public List<Cliente> findAll() {
		return cliRepos.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return cliRepos.findAll(pageRequest);
		
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
		
	}	
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	
}
