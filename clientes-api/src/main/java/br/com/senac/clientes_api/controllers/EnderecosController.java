package br.com.senac.clientes_api.controllers;
import br.com.senac.clientes_api.dtos.EnderecoFiltroDto;
import br.com.senac.clientes_api.dtos.EnderecoRequestDto;
import br.com.senac.clientes_api.entidades.Enderecos;
import br.com.senac.clientes_api.services.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enderecos")
public class EnderecosController {
    private EnderecoService enderecoService;

    public EnderecosController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Enderecos>> listar(EnderecoFiltroDto filtro) {
        return ResponseEntity
                .ok(enderecoService.listar(filtro));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Enderecos> listarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(enderecoService.listarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<Enderecos> criar(
            @RequestBody EnderecoRequestDto enderecos) {
        try {
            return ResponseEntity
                    .ok(enderecoService.criar(enderecos));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Enderecos> atualizar(
            @RequestBody EnderecoRequestDto enderecos,
            @PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok(enderecoService.atualizar(id, enderecos));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {
        try {
            enderecoService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }
}
