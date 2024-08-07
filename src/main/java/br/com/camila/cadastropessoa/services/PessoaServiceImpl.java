package br.com.camila.cadastropessoa.services;

import java.util.List;
import java.util.Optional;

import br.com.camila.cadastropessoa.dto.EnderecoDTO;
import br.com.camila.cadastropessoa.model.Endereco;
import br.com.camila.cadastropessoa.repositories.EnderecoRepository;
import br.com.camila.cadastropessoa.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.cadastropessoa.model.Pessoa;


@Service
public class PessoaServiceImpl implements IPessoaService {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private IEnderecoService enderecoService;

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
			salvarPessoaComCEP(pessoa);
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

				// Atualiza os dados da pessoa com os novos valores
				Pessoa pessoaExistente = pessoaPesquisada.get();

				pessoaExistente.setNomeCompleto(pessoa.getNomeCompleto());
				pessoaExistente.setCpf(pessoa.getCpf());
				pessoaExistente.setTelefone(pessoa.getTelefone());

				// Atualiza o endereço da pessoa se necessário
				if (pessoa.getEndereco() != null && pessoa.getEndereco().getCep() != null) {
					pessoaExistente = salvarPessoaComCEP(pessoaExistente);
				}
				validatePessoa(pessoaExistente);
				pessoaExistente.setId(idPessoa);
				return repository.save(pessoaExistente);
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

	private void validatePessoa(Pessoa pessoa) {
		if (
				pessoa != null
						&& pessoa.getNomeCompleto().trim().isEmpty() && pessoa.getNomeCompleto() != null
						&& pessoa.getCpf() != null && pessoa.getCpf().trim().isEmpty()
						&& pessoa.getTelefone() != null && pessoa.getTelefone().trim().isEmpty()
						&& pessoa.getEndereco() != null
		) {
			throw new IllegalArgumentException("Registro de pessoa inválido.");
		}
	}

	private Pessoa salvarPessoaComCEP(Pessoa pessoa) {
		try {
			String cep = pessoa.getEndereco().getCep();
			if(cep == null || cep.trim().isEmpty()) {
				throw new IllegalArgumentException("CEP não informado.");
			}

			EnderecoDTO enderecoDTO = enderecoService.buscarEnderecoPorCEP(cep);

			if(enderecoDTO == null || enderecoDTO.getCep() == null) {
				throw new IllegalArgumentException("Endereço não encontrado para o CEP informado: " + cep);
			}

			Endereco endereco = new Endereco();
			endereco.setCep(enderecoDTO.getCep());
			endereco.setLogradouro(enderecoDTO.getLogradouro());
			endereco.setBairro(enderecoDTO.getBairro());
			endereco.setCidade(enderecoDTO.getLocalidade());
			endereco.setUf(enderecoDTO.getUf());

			endereco = enderecoRepository.save(endereco);
			pessoa.setEndereco(endereco);
			validatePessoa(pessoa);
			return repository.save(pessoa);

		} catch (IllegalArgumentException e) {
			System.out.println("DEBUG: " + e.getMessage());
		}
		return null;
	}

}
