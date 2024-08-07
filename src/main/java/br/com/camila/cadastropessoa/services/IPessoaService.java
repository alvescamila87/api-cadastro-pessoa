package br.com.camila.cadastropessoa.services;

import java.util.List;

import br.com.camila.cadastropessoa.model.Pessoa;

public interface IPessoaService {
	
	public List<Pessoa> recuperarTodasPessoas();
	
	public List<Pessoa> recuperarPessoaPorId(Long idPessoa);
	
	public Pessoa inserirNovaPessoa(Pessoa pessoa);
	
	public Pessoa alterarPessoa(Pessoa pessoa);	
	
	public void removerPessoa(Long idPessoa);

}
