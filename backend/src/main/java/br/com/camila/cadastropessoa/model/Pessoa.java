package br.com.camila.cadastropessoa.model;

import jakarta.persistence.*;
import lombok.*;

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
	
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

}
