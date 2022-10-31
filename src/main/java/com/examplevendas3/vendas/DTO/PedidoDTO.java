package com.examplevendas3.vendas.DTO;

import com.examplevendas3.vendas.domain.Status;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Integer cliente;
    private Integer pedido;
    private Integer total;
    private Status status;
    private List<ItemPedidoDTO> itemPedidos = new ArrayList<>();

}
