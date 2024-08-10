package br.com.camila.cadastropessoa.services;

import java.util.List;

import br.com.camila.cadastropessoa.dto.PessoaDTO;
import br.com.camila.cadastropessoa.model.Pessoa;
import org.springframework.stereotype.Service;

@Service
public interface IPessoaService {
	
	List<Pessoa> recuperarTodasPessoas();
	
	Pessoa recuperarPessoaPorId(Long idPessoa);
	
	Pessoa salvarPessoa(PessoaDTO pessoa);
	
	Pessoa atualizar(Long idPessoa, PessoaDTO pessoa);
	
	void excluirPessoa(Long idPessoa);

}
