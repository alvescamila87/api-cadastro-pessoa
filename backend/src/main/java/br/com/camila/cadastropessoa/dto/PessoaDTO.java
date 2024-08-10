package br.com.camila.cadastropessoa.dto;

import br.com.camila.cadastropessoa.model.Endereco;
import lombok.Data;

@Data
public class PessoaDTO {

    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String telefone;
    private Endereco endereco;

}
