package com.r7frank.modelag_conceit.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r7frank.modelag_conceit.domain.Cliente;
import com.r7frank.modelag_conceit.dto.ClienteDTO;
import com.r7frank.modelag_conceit.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cliente>> find(@PathVariable Integer id) {
		Optional<Cliente> obj = Optional.ofNullable(clienteService.find(id));
			
		return ResponseEntity.ok().body(obj);
		
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		Cliente obj = clienteService.fromDTO(objDto);
		obj.setId(id);
		obj = clienteService.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
	clienteService.delete(id);
	return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = clienteService.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}	
	
	
}
