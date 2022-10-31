package com.examplevendas3.vendas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Produto  implements Serializable {


    private static final long serialVersionUID = 6663362305373797330L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Campo Descrição é obrigatório. ")
    private String descricao;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ItemPedido> itemPedidos = new ArrayList<>();

    public Produto (){}

    public Produto (Integer id, String descricao){

        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(List<ItemPedido> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }
}
