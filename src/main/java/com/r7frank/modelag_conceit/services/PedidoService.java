package com.r7frank.modelag_conceit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r7frank.modelag_conceit.domain.Pedido;
import com.r7frank.modelag_conceit.repositories.PedidoRepository;
import com.r7frank.modelag_conceit.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRep;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "
		+ id + ", Tipo: " + Pedido.class.getName()));
	}

	public List<Pedido> listar() {
		return pedidoRep.findAll();
	}
}
