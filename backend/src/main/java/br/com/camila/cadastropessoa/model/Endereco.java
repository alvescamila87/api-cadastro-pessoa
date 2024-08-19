package br.com.camila.cadastropessoa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa um endereço de localização no Brasil no sistema.
 *
 * <p>
 * A classe {@code Endereco} mapeia uma entidade do banco de dados e contém
 * informações detalhadas sobre o endereço, como CEP, logradouro, número,
 * complemento, bairro, localidade, unidade federativa (UF), além de outros
 * atributos relacionados ao endereço que podem ser mantidos para fins de consulta.
 * </p>
 *
 * <p>
 * O relacionamento entre {@code Endereco} e {@code Pessoa} é de um-para-um.
 * Um endereço está associado a uma única pessoa e vice-versa.
 * </p>
 *
 * @see Pessoa
 *
 * @author Camila
 * @since V1
 */
@Entity
@Getter
@Setter
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String ibge;
	private String gia;
	private String ddd;
	private String siafi;

	/**
	 * Pessoa associada a este endereço.
	 * <p>
	 * Relacionamento de um-para-um entre {@code Endereco} e {@code Pessoa}.
	 * </p>
	 *
	 * O atributo fetch = FetchType.LAZY significa que a entidade Pessoa associada
	 * só será carregada do banco de dados quando for acessada pela primeira vez,
	 * economizando recursos se a informação não for necessária imediatamente.
	 *
	 */
	@OneToOne(fetch = FetchType.LAZY)
	private Pessoa pessoa;


}
