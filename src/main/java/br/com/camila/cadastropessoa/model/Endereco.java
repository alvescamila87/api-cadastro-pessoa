package br.com.camila.cadastropessoa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
	
	@Id
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade; //localidade no viacep
	private String uf;


}
