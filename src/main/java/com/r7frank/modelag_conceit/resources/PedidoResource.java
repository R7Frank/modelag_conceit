package com.r7frank.modelag_conceit.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r7frank.modelag_conceit.domain.Pedido;
import com.r7frank.modelag_conceit.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoServ;

	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Optional<Pedido> obj = Optional.ofNullable(pedidoServ.find(id));
			
		return ResponseEntity.ok().body(obj);
		
	}
	
}
