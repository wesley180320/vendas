package com.examplevendas3.vendas.domain;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente implements Serializable {


    private static final long serialVersionUID = -1384898673559598417L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Campo nome e obrigatório")
    private String name;

    @Column(length = 11)
    @NotEmpty(message = "Campo CPF obrigatório")
    @CPF(message = "Informe um CPF válido.")
    private String cpf;

    @OneToMany(mappedBy ="cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Integer id, String name, String cpf) {
        this.id = id;
        this.name = name;
//        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
