package br.com.camila.cadastropessoa.services;

import java.util.List;
import br.com.camila.cadastropessoa.dto.PessoaDTO;
import br.com.camila.cadastropessoa.model.Pessoa;

public interface IPessoaService {

	List<Pessoa> recuperarTodasPessoas();

	Pessoa recuperarPessoaPorId(Long idPessoa);

	PessoaDTO salvarPessoa(PessoaDTO pessoa);

	PessoaDTO atualizar(Long idPessoa, PessoaDTO pessoa);

	void excluirPessoa(Long idPessoa);

}