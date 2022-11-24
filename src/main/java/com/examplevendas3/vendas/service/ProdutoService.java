package com.examplevendas3.vendas.service;

import com.examplevendas3.vendas.domain.Produto;
import com.examplevendas3.vendas.repository.ProdutoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRespository produtoRespository;

    public ProdutoService(ProdutoRespository produtoRespository) {
        this.produtoRespository = produtoRespository;
    }

    public Optional<Produto> findById(Integer id){
          return  produtoRespository.findById(id);
    }

    public void deleteById(Integer id){
        produtoRespository.deleteById(id);
    }

    public Produto save (Produto produto){

       return produtoRespository.save(produto);
    }

    public List<Produto> findAll (){
        return produtoRespository.findAll();
    }

}

