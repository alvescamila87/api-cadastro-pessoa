package br.com.camila.cadastropessoa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa um endereço de localização do Brasil no sistema.
 * A classe {@code Endereco} mapeia uma entidade do banco de dados e contém informações
 * detalhadas sobre o endereço, como CEP, logradouro, número bairro, cidade e UF e outros
 * atributos relacionados mantidos caso houver interesse em consutá-los.
 *
 * @see Pessoa
 *
 * @author Camila
 * @since V1
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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

	@OneToOne(mappedBy = "endereco")
	@JsonIgnoreProperties("endereco")
	private Pessoa pessoa;


}
