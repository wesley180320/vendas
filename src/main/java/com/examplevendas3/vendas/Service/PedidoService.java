package com.examplevendas3.vendas.Service;


import com.examplevendas3.vendas.DTO.ItemPedidoDTO;
import com.examplevendas3.vendas.DTO.PedidoDTO;
import com.examplevendas3.vendas.domain.*;
import com.examplevendas3.vendas.repository.ClienteRepository;
import com.examplevendas3.vendas.repository.ItemPedidoRepository;
import com.examplevendas3.vendas.repository.PedidoRepository;
import com.examplevendas3.vendas.repository.ProdutoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoRespository produtoRespository;

    public Optional<Pedido> findById(Integer id){

        return pedidoRepository.findById(id);
    }

    public void deletar(Integer id){

        pedidoRepository.deleteById(id);
    }

    @Transactional
    public Pedido save(PedidoDTO pedidoDTO) {

        Integer id = pedidoDTO.getCliente();

        Pedido pedido = new Pedido();
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("Código de cliente inválido. " + id));

        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setCliente(cliente);
        pedido.setStatus(Status.valueOf(pedidoDTO.getStatus().name()));

        List<ItemPedido> itemPedidos = converterItemns(pedido, pedidoDTO.getItemPedidos());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemPedidos);
        pedido.setItemPedidos(itemPedidos);
        return null;

    }

    private List<ItemPedido> converterItemns(Pedido pedido, List<ItemPedidoDTO> items) {

        if (items.isEmpty()) {
            throw new RegraDeNegocioException("Não é possível realizar pedidos sem items");
        }

        return items
                .stream()
                .map(dto -> {

                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRespository.findById(idProduto).orElseThrow(() -> new RegraDeNegocioException("Código do produto inválido " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setPedido(pedido);
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setProduto(produto);
                    return itemPedido;

                }).collect(Collectors.toList());


    }

    @Transactional
    public Pedido  atualizarStatusPedido (PedidoDTO pedidoDTO){


        Integer pedidoId = pedidoDTO.getPedido();

        Integer id = pedidoDTO.getCliente();

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("Código de cliente inválido. " + id));
        Pedido p1 = pedidoRepository.findById(pedidoId).orElseThrow(() -> new RegraDeNegocioException("Códido do pedido inválido " + pedidoId));

        p1.setTotal(pedidoDTO.getTotal());
        p1.setCliente(cliente);
        p1.setStatus(Status.valueOf(pedidoDTO.getStatus().name()));

        pedidoRepository.save(p1);
        return null;



    }
}
