package com.examplevendas3.vendas.Controller;

import com.examplevendas3.vendas.Service.ClienteService;
import com.examplevendas3.vendas.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findById(@PathVariable Integer id) {

        Optional<Cliente> cliente = clienteService.findById(id);

        if (!cliente.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found");

        }

        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> save(@RequestBody Cliente cliente){

        Cliente obj = clienteService.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> findAll(){

        List<Cliente> obj = clienteService.findAll();

        return ResponseEntity.ok().body(obj);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete (@PathVariable Integer id) {

        Optional<Cliente> obj = clienteService.findById(id);

        if (obj.isPresent()){

            clienteService.deletar(id);
            return ResponseEntity.ok().body("Cliente deletado id " + id);

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Not Found");
    }

}
