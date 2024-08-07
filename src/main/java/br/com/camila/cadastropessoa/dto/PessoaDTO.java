package br.com.camila.cadastropessoa.dto;

import lombok.Data;

@Data
public class PessoaDTO {

    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String telefone;
    private Long enderecoId;
    private String cep;
}
