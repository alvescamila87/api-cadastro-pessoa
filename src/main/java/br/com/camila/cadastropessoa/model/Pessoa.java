package br.com.camila.cadastropessoa.model;

import jakarta.persistence.Entity;

@Entity
public class Pessoa {
	
	private Long id;
	private String nomeCompleto;
	private String cpf;
	private String telefone;
	private Endereco endereco;
	
	
	

}
