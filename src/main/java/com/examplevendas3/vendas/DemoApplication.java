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


		Cliente c1 = new Cliente(null, "joao", "08913476168");
		Pedido p1 = new Pedido(c1, null,12, Status.valueOf("Realizado"));
		Produto pr1 = new Produto(null,"TV");
		ItemPedido it1 = new ItemPedido(null,p1,pr1,2);
		c1.getPedidos().add(p1);
		pr1.getItemPedidos().add(it1);

		clienteRepository.save(c1);
		pedidoRepository.save(p1);
		produtoRespository.save(pr1);
		itemPedidoRepository.save(it1);



	}
}
