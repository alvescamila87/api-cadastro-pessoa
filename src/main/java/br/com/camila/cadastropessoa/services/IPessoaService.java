package br.com.camila.cadastropessoa.services;

import java.util.List;

import br.com.camila.cadastropessoa.model.Pessoa;
import org.springframework.stereotype.Service;

@Service
public interface IPessoaService {
	
	public List<Pessoa> recuperarTodasPessoas();
	
	public Pessoa recuperarPessoaPorId(Long idPessoa);
	
	public Pessoa inserirNovaPessoa(Pessoa pessoa);
	
	public Pessoa alterarPessoa(Long idPessoa, Pessoa pessoa);
	
	public void removerPessoa(Long idPessoa);

}
