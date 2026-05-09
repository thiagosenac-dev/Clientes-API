package br.com.senac.clientes_api.dtos;

public class EnderecoFiltroDto {

    private String numero;
    private String cep;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
