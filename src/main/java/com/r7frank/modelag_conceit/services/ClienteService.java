package com.r7frank.modelag_conceit.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r7frank.modelag_conceit.domain.Cliente;
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

}
