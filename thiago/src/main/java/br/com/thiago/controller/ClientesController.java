package br.com.thiago.controller;

import br.com.thiago.dtos.ClientesRequestDTO;
import br.com.thiago.entidades.Clientes;
import br.com.thiago.repositorios.ClientesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
@CrossOrigin
public class ClientesController {

    private ClientesRepository clientesRepository;

    public ClientesController(ClientesRepository clientesRepository) { this.clientesRepository = clientesRepository; }

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listar(){
        List<Clientes> clientesList = clientesRepository.findAll();

        if (clientesList.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.ok(clientesList);
    }

    @PostMapping("/criar")
    public ResponseEntity<Clientes> criar(@RequestBody ClientesRequestDTO cliente) {

        Clientes clientePersist = new Clientes();
        clientePersist.setNome(cliente.getNome());
        clientePersist.setEmail(cliente.getEmail());

        Clientes retorno = clientesRepository.save(clientePersist);

        return ResponseEntity.ok(retorno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Clientes> atualizar(@RequestBody ClientesRequestDTO cliente, @PathVariable Long id){

        if (clientesRepository.existsById(id)) {
            Clientes clientePersist = new Clientes();
            clientePersist.setNome(cliente.getNome());
            clientePersist.setEmail(cliente.getEmail());
            clientePersist.setId(id);

            Clientes retorno = clientesRepository.save(clientePersist);

            return ResponseEntity.ok(retorno);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        if (clientesRepository.existsById(id)){
            clientesRepository.deleteById(id);

            return ResponseEntity.ok(null);
        }
        return ResponseEntity.badRequest().body(null);
    }

}