package br.com.camila.cadastropessoa.dto;

import br.com.camila.cadastropessoa.model.Endereco;
import lombok.Data;

/**
 * Data Transfer Object (DTO) para representar uma pessoa no sistema.
 * A classe {@code PessoaDTO} é usada para transportar dados de uma pessoa entre camadas
 * entre o frontend e o backend a trazer mais segurança sem expor a entidade Pessoa.
 *
 * @see Endereco
 *
 * @author Camila
 * @since V1
 */
@Data
public class PessoaDTO {

    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String telefone;
    private Endereco endereco;

}
