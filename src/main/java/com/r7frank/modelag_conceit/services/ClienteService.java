package com.r7frank.modelag_conceit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.r7frank.modelag_conceit.domain.Cidade;
import com.r7frank.modelag_conceit.domain.Cliente;
import com.r7frank.modelag_conceit.domain.Endereco;
import com.r7frank.modelag_conceit.domain.enums.TipoCliente;
import com.r7frank.modelag_conceit.dto.ClienteDTO;
import com.r7frank.modelag_conceit.dto.ClienteNewDTO;
import com.r7frank.modelag_conceit.repositories.ClienteRepository;
import com.r7frank.modelag_conceit.repositories.EnderecoRepository;
import com.r7frank.modelag_conceit.services.exceptions.ObjectNotFoundException;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository cliRepos;
	
	@Autowired
	private EnderecoRepository endRepos;	
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = cliRepos.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "
		+ id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = cliRepos.save(obj);
		endRepos.saveAll(obj.getEnderecos());
		return obj;
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
			throw new com.r7frank.modelag_conceit.services.exceptions.DataIntegrityViolationException("Não é permitido excluir um cliente que contenha pedidos vinculados a ele!");
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
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!= null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!= null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}		
		return cli;
	}	
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	
}
