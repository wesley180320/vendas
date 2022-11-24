package com.examplevendas3.vendas.controller;


import com.examplevendas3.vendas.DTO.PedidoDTO;
import com.examplevendas3.vendas.service.PedidoService;


import com.examplevendas3.vendas.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> save(@RequestBody PedidoDTO pedidoDTO){

        Pedido pedido = pedidoService.save(pedidoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);

    }

    @RequestMapping(value = "/{id}", method =RequestMethod.GET)
    public ResponseEntity<Object> findById( @PathVariable Integer id){

        Optional<Pedido> pedido = pedidoService.findById(id);

        return ResponseEntity.ok().body(pedido);

    }

    @RequestMapping(value = "/atualizar", method = RequestMethod.POST)
    public ResponseEntity<Object> atualizarStatusPedido(@RequestBody PedidoDTO pedidoDTO){

        Pedido pedido = pedidoService.atualizarStatusPedido(pedidoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);

    }

}


