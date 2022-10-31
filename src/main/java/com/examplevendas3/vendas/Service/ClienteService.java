package com.examplevendas3.vendas.Service;

import com.examplevendas3.vendas.domain.Cliente;
import com.examplevendas3.vendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> findById(Integer id){
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void deletar(Integer id){
        clienteRepository.deleteById(id);
    }

    public List<Cliente> findAll(){

        return clienteRepository.findAll();
    }

}
