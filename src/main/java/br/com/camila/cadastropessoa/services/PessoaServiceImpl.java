package br.com.camila.cadastropessoa.services;

import java.util.List;
import java.util.Optional;

import br.com.camila.cadastropessoa.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.cadastropessoa.model.Pessoa;


@Service
public class PessoaServiceImpl implements IPessoaService {

	@Autowired
	private PessoaRepository repository;

	@Override
	public List<Pessoa> recuperarTodasPessoas() {
		return repository.findAll();
	}

	@Override
	public Pessoa recuperarPessoaPorId(Long idPessoa) {
		try {
			Optional<Pessoa> pessoaPesquisada = repository.findById(idPessoa);

			if(pessoaPesquisada.isPresent()) {
				return pessoaPesquisada.get();
			}
		} catch (IllegalArgumentException e) {
			System.out.println("DEBUG: " + e.getMessage());
		}
			return null;
		}

	@Override
	public Pessoa inserirNovaPessoa(Pessoa pessoa) {
		try {
			validatePessoa(pessoa);
			return repository.save(pessoa);
		} catch (IllegalArgumentException e) {
			System.out.println("DEBUG: " + e.getMessage());
		}
		return null;
	}

	@Override
	public Pessoa alterarPessoa(Long idPessoa, Pessoa pessoa) {
		try {
			Optional<Pessoa> pessoaPesquisada = repository.findById(idPessoa);

			if(pessoaPesquisada.isPresent()) {
				validatePessoa(pessoa);
				pessoa.setId(idPessoa);
				return repository.save(pessoa);
			}
			return null;

		} catch (IllegalArgumentException e) {
			System.out.println("DEBUG: " + e.getMessage());
		}
		return null;
	}

	@Override
	public void removerPessoa(Long idPessoa) {
		try {
			Optional<Pessoa> pessoaPesquisada = repository.findById(idPessoa);

			if(pessoaPesquisada.isPresent()) {
				repository.delete(pessoaPesquisada.get());
			}
		} catch (IllegalArgumentException e) {
			System.out.println("DEBUG: " + e.getMessage());
		}
	}

	protected void validatePessoa(Pessoa pessoa) {
		if (
				pessoa != null
						&& pessoa.getNomeCompleto().trim().isEmpty() && pessoa.getNomeCompleto() != null
						&& pessoa.getCpf() != null && pessoa.getCpf().trim().isEmpty()
						&& pessoa.getTelefone() != null && pessoa.getTelefone().trim().isEmpty()
						&& pessoa.getEndereco() != null
		) {
			throw new IllegalArgumentException("Registro de pessoa inválido");
		}
	}

}
