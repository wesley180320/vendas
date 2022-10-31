package com.examplevendas3.vendas.Controller;


import com.examplevendas3.vendas.Service.ProdutoService;
import com.examplevendas3.vendas.domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {


    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findById(@PathVariable Integer id) {

        Optional<Produto> produto = produtoService.findById(id);

        if (produto.isPresent()) {

            return ResponseEntity.ok().body(produto);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto not found");
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delelteById(@PathVariable Integer id) {


        Optional<Produto> obj = produtoService.findById(id);


        if (obj.isPresent()) {

            produtoService.deleteById(id);

            return ResponseEntity.ok().body("Produto deletado id " + id);

        } else {


            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto not found");
        }

    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> findAll() {


        List<Produto> produtos = produtoService.findAll();


        if (!produtos.isEmpty()) {

            return ResponseEntity.ok().body(produtos);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> save (@RequestBody Produto produto){


        Produto produto1 = produtoService.save(produto);

        return ResponseEntity.ok(HttpStatus.CREATED);

    }

}
