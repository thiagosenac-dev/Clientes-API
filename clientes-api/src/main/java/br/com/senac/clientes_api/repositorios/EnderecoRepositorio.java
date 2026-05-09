package br.com.senac.clientes_api.repositorios;

import br.com.senac.clientes_api.entidades.Clientes;
import br.com.senac.clientes_api.entidades.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepositorio extends JpaRepository <Enderecos, Long> {
    List<Enderecos> findByRua(String rua);
    List<Enderecos> findByBairro(String bairro);
    List<Enderecos> findByEstado(String estado);
    List<Enderecos> findByNumero(String numero);
    List<Enderecos> findByComplemento(String complemento);
    List<Enderecos> findByCep(String cep);
}
