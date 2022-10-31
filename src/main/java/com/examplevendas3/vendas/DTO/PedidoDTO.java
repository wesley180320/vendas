package com.examplevendas3.vendas.DTO;

import com.examplevendas3.vendas.domain.Status;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    @NotNull(message = "Informe o código do cliente")
    private Integer cliente;
    private Integer pedido;
    @NotNull( message = "Campo total do pedido e obrigatório ")
    private Integer total;
    private Status status;
    private List<ItemPedidoDTO> itemPedidos = new ArrayList<>();

}
