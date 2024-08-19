package br.com.camila.cadastropessoa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

/**
 * Representa uma pessoa no sistema.
 * A classe {@code Pessoa} mapeia uma entidade do banco de dados e contém informações
 * básicas sobre a pessoa física como: nome, CPF , telefone e um endereço associado.
 *
 * <p>
 * O relacionamento com a entidade {@link Endereco} é do tipo um-para-um, onde a
 * chave estrangeira que faz a referência ao endereço é {@code endereco_id}.
 * As operações em cascata são aplicadas, e a remoção de órfãos está habilitada,
 * o que significa que se o relacionamento for removido, o endereço correspondente
 * também será removido do banco de dados.
 * </p>
 *
 * @see Endereco
 *
 * @author Camila
 * @since V1
 */
@Entity
@Getter
@Setter
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeCompleto;

	/**
	 * CPF (Cadastro de Pessoas Físicas) da pessoa.
	 * <p>
	 * Este campo é obrigatório e deve ser único no sistema.
	 * </p>
	 */
	@Column(unique = true, nullable = false)
	private String cpf;
	private String telefone;

	/**
	 * Endereço associado à pessoa.
	 * <p>
	 * Relacionamento do tipo um-para-um com a entidade {@link Endereco}.
	 * A coluna que faz a referência ao endereço é {@code endereco_id}.
	 * Cascade:  Indica que todas as operações (persistir, remover, etc.)
	 * realizadas em Pessoa serão automaticamente propagadas para o Endereco associado.
	 * OrphanRemoval: Se o relacionamento entre Pessoa e Endereco for removido (por exemplo,
	 * se a Pessoa deixar de referenciar o Endereco), o Endereco será removido do banco de dados.
	 * </p>
	 *
	 * @see Endereco
	 */
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "endereco_id")
	@JsonIgnoreProperties("pessoa")
	private Endereco endereco;

}
