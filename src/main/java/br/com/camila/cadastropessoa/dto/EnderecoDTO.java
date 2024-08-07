package br.com.camila.cadastropessoa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
