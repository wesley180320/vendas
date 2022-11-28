package com.examplevendas3.vendas;

import com.examplevendas3.vendas.domain.*;
import com.examplevendas3.vendas.repository.ClienteRepository;
import com.examplevendas3.vendas.repository.ItemPedidoRepository;
import com.examplevendas3.vendas.repository.PedidoRepository;
import com.examplevendas3.vendas.repository.ProdutoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoRespository produtoRespository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {






	}
}
