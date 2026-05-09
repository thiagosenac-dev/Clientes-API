package br.com.senac.clientes_api.services;
import br.com.senac.clientes_api.dtos.EnderecoFiltroDto;
import br.com.senac.clientes_api.dtos.EnderecoRequestDto;
import br.com.senac.clientes_api.entidades.Enderecos;
import br.com.senac.clientes_api.repositorios.EnderecoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private EnderecoRepositorio enderecoRepositorio;

    public EnderecoService(EnderecoRepositorio enderecoRepositorio) {
        this.enderecoRepositorio = enderecoRepositorio;
    }

    public List<Enderecos> listar(EnderecoFiltroDto filtro) {
        if(filtro.getCep() != null) {
            return enderecoRepositorio.findByCep(filtro.getCep());
        }

        if(filtro.getNumero() != null) {
            return enderecoRepositorio.findByNumero(filtro.getNumero());
        }

        return enderecoRepositorio.findAll();
    }

    public Enderecos criar(EnderecoRequestDto endereco) {
        Enderecos enderecosPersist =
                this.enderecosResquestDtoParaEnderecos(endereco);

        return enderecoRepositorio.save(enderecosPersist);
    }

    public Enderecos atualizar(
            Long id,
            EnderecoRequestDto enderecos) {
        if(enderecoRepositorio.existsById(id)) {
            Enderecos enderecosPersist =
                    this.enderecosResquestDtoParaEnderecos(enderecos);
            enderecosPersist.setId(id);

            return enderecoRepositorio.save(enderecosPersist);
        }

        throw new RuntimeException("Endereço não encontrado");
    }

    public void deletar(Long id) {
        if(enderecoRepositorio.existsById(id)) {
            enderecoRepositorio.deleteById(id);
        }

        throw new RuntimeException("Endereço não encontrado");
    }

    public Enderecos listarPorId(Long id) {
        Optional<Enderecos> retorno = enderecoRepositorio.findById(id);
        if(retorno.isPresent()) {
            return retorno.get();
        }

        throw new RuntimeException("Endereço não encontrado");
    }

    private Enderecos enderecosResquestDtoParaEnderecos
            (EnderecoRequestDto entrada) {
        Enderecos saida = new Enderecos();
        saida.setRua(entrada.getRua());
        saida.setBairro(entrada.getBairro());
        saida.setCidade(entrada.getCidade());
        saida.setEstado(entrada.getEstado());
        saida.setNumero(entrada.getNumero());
        saida.setComplemento(entrada.getComplemento());
        saida.setCep(entrada.getCep());

        return saida;
    }

}
