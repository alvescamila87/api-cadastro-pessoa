package br.com.camila.cadastropessoa.services;

import java.util.List;
import java.util.Optional;


import br.com.camila.cadastropessoa.dto.PessoaDTO;
import br.com.camila.cadastropessoa.model.Endereco;
import br.com.camila.cadastropessoa.repositories.EnderecoRepository;
import br.com.camila.cadastropessoa.repositories.PessoaRepository;
import br.com.camila.cadastropessoa.utils.ValidaCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.camila.cadastropessoa.model.Pessoa;

/**
 * Implementação do serviço para a entidade {@link Pessoa}.
 * <p>
 * A classe {@code PessoaServiceImpl} fornece a implementação dos métodos definidos na interface
 * {@link IPessoaService}, permitindo operações de gerenciamento para a entidade {@link Pessoa}.
 * </p>
 *
 * @see IPessoaService
 * @see Pessoa
 * @see PessoaDTO
 * @see Endereco
 * @see EnderecoRepository
 * @see PessoaRepository
 * @see ValidaCPF
 *
 * @author Camila
 * @since V1
 */
@Service
public class PessoaServiceImpl implements IPessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	/**
	 * Recupera todas as pessoas do sistema.
	 *
	 * @return uma lista contendo todas as pessoas cadastradas
	 */
	@Override
	public List<Pessoa> recuperarTodasPessoas() {
		return pessoaRepository.findAll();
	}

	/**
	 * Recupera uma pessoa específica pelo seu identificador.
	 *
	 * @param idPessoa o ID da pessoa a ser recuperada
	 * @return a pessoa correspondente ao ID fornecido
	 */
	@Override
	public Pessoa recuperarPessoaPorId(Long idPessoa) {
		Optional<Pessoa> pessoaPesquisada = pessoaRepository.findById(idPessoa);

		if(pessoaPesquisada.isPresent()) {
			return pessoaPesquisada.get();
		}
		return null;
	}

	/**
	 * Salva uma nova pessoa no sistema. Este método também salva o endereço associado à pessoa.
	 *
	 * @param pessoa o objeto {@link PessoaDTO} contendo os dados da pessoa a ser salva
	 * @return a pessoa salva, com o ID gerado pelo sistema
	 */
	@Override
	public Pessoa salvarPessoa(PessoaDTO pessoa) {
		return salvarPessoaComCEP(pessoa);
	}

	/**
	 * Atualiza os dados de uma pessoa existente. Este método também atualiza o endereço associado à pessoa.
	 *
	 * @param idPessoa o identificador da pessoa a ser atualizada
	 * @param pessoa o objeto {@link PessoaDTO} contendo os dados atualizados da pessoa
	 * @return a pessoa atualizada
	 */
	@Override
	public Pessoa atualizar(Long idPessoa, PessoaDTO pessoa) {
		Optional<Pessoa> pessoaPesquisada = pessoaRepository.findById(idPessoa);

		if(pessoaPesquisada.isPresent()) {
			return salvarPessoaComCEP(pessoa);
		}
        return null;
    }

	/**
	 * Exclui uma pessoa do sistema.
	 *
	 * @param idPessoa o identificador da pessoa a ser excluída
	 */
	@Override
	public void excluirPessoa(Long idPessoa) {
		Optional<Pessoa> pessoaPesquisada = pessoaRepository.findById(idPessoa);

		if(pessoaPesquisada.isPresent()) {
			pessoaRepository.deleteById(idPessoa);
		}
	}

	/**
	 * Salva uma nova pessoa com o endereço associado e valida o CPF da pessoa.
	 *
	 * @param pessoaDTO o objeto {@link PessoaDTO} contendo os dados da pessoa a ser salva
	 * @return a pessoa salva, com o identificador gerado pelo sistema
	 * @throws IllegalArgumentException se o CPF informado for inválido
	 * @throws DataIntegrityViolationException se o CPF informado for já estiver cadastrado
	 */
	private Pessoa salvarPessoaComCEP(PessoaDTO pessoaDTO){
		Pessoa novaPessoa = new Pessoa();
		novaPessoa.setId(pessoaDTO.getId());
		novaPessoa.setNomeCompleto(pessoaDTO.getNomeCompleto());

		if (!ValidaCPF.isCPF(pessoaDTO.getCpf())) {
			throw new IllegalArgumentException("O CPF informado: " + pessoaDTO.getCpf() + " é inválido.");
		}

		String cpfFormatado = ValidaCPF.imprimeCPF(pessoaDTO.getCpf().replaceAll("\\D", ""));
		novaPessoa.setCpf(cpfFormatado);
		novaPessoa.setTelefone(pessoaDTO.getTelefone());

		Endereco novoEndereco = enderecoRepository.save(pessoaDTO.getEndereco());
		novaPessoa.setEndereco(novoEndereco);

		pessoaRepository.save(novaPessoa);
        return novaPessoa;
	}
}
