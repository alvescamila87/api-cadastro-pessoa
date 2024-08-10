package br.com.camila.cadastropessoa.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa uma pessoa no sistema.
 * A classe {@code Pessoa} mapeia uma entidade do banco de dados e contém informações
 * básicas sobre a pessoa física como: nome, CPF , telefone e um endereço associado.
 *
 * @see Endereco
 *
 * @author Camila
 * @since V1
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeCompleto;

	@Column(unique = true, nullable = false)
	private String cpf;
	private String telefone;

	/**
	 * Endereço associado à pessoa.
	 * Este é um relacionamento de muitos-para-um com a entidade {@code Endereco}.
	 * A coluna que faz a referência ao endereço é {@code endereco_id}.
	 */
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

}
