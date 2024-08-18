package br.com.camila.cadastropessoa.services;

import java.util.List;


import br.com.camila.cadastropessoa.dto.PessoaDTO;
import br.com.camila.cadastropessoa.model.Endereco;
import br.com.camila.cadastropessoa.repositories.EnderecoRepository;
import br.com.camila.cadastropessoa.repositories.PessoaRepository;
import br.com.camila.cadastropessoa.utils.ValidaCPF;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class PessoaServiceImpl implements IPessoaService {

	private final PessoaRepository pessoaRepository;

	private final EnderecoRepository enderecoRepository;


	/**
	 * Recupera todas as pessoas cadastradas no sistema.
	 *
	 * @return uma lista contendo todas as pessoas
	 */
	@Override
	public List<Pessoa> recuperarTodasPessoas() {
		return pessoaRepository.findAll();
	}

	/**
	 * Recupera uma pessoa específica pelo seu ID.
	 *
	 * @param idPessoa o ID da pessoa a ser recuperada
	 * @return a pessoa correspondente ao ID fornecido
	 * @throws IllegalArgumentException se a pessoa com o ID fornecido não for encontrada
	 */
	@Override
	public Pessoa recuperarPessoaPorId(Long idPessoa) {
		return pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new IllegalArgumentException("A Pessoa de ID: " + idPessoa + " não foi encontrada."));
	}

	/**
	 * Salva uma nova pessoa no sistema. Este método também salva o endereço associado à pessoa.
	 *
	 * @param pessoaDTO o objeto {@link PessoaDTO} contendo os dados da pessoa a ser salva
	 * @return o objeto {@link PessoaDTO} da pessoa salva, com o ID gerado pelo sistema
	 * @throws IllegalArgumentException se o CPF informado for inválido
	 * @throws DataIntegrityViolationException se o CPF informado já estiver cadastrado
	 */
	@Override
	public PessoaDTO salvarPessoa(PessoaDTO pessoaDTO) {
		return salvarPessoaComCEP(pessoaDTO);
	}

	/**
	 * Atualiza os dados de uma pessoa existente. Este método também atualiza o endereço associado à pessoa.
	 *
	 * <p>
	 * Este método recupera a pessoa com o ID fornecido, atualiza seus dados com base nas
	 * informações presentes no objeto {@link PessoaDTO}, e salva as alterações no repositório. O método
	 * também realiza a validação do CPF e atualiza o endereço associado à pessoa.
	 * </p>
	 *
	 * @param idPessoa  o ID da pessoa a ser atualizada
	 * @param pessoaDTO o objeto {@link PessoaDTO} contendo os dados atualizados da pessoa
	 * @return o objeto {@link PessoaDTO} atualizado e salvo no repositório
	 * @throws IllegalArgumentException se a pessoa com o ID fornecido não for encontrada
	 */
	@Override
	public PessoaDTO atualizar(Long idPessoa, PessoaDTO pessoaDTO) {
		Pessoa pessoaPesquisada = recuperarPessoaPorId(idPessoa);

		return salvarPessoaComCEP(pessoaDTO);
	}

	/**
	 * Exclui uma pessoa do sistema com base no ID fornecido.
	 * <p>
	 * Este método recupera a pessoa com o identificador fornecido e a exclui do repositório de pessoas.
	 * Caso a pessoa não seja encontrada, será lançada uma exceção durante o processo de recuperação.
	 * </p>
	 *
	 * @param idPessoa o ID da pessoa a ser excluída
	 * @throws IllegalArgumentException se a pessoa com o ID fornecido não for encontrada
	 */
	@Override
	public void excluirPessoa(Long idPessoa) {
		Pessoa pessoaPesquisada = recuperarPessoaPorId(idPessoa);

		pessoaRepository.delete(pessoaPesquisada);
	}

	/**
	 * Salva uma nova pessoa no sistema, incluindo a validação do CPF e a persistência do endereço associado.
	 * <p>
	 * Este método cria uma nova instância de {@link Pessoa} com os dados fornecidos no {@link PessoaDTO}.
	 * Valida o CPF da pessoa e formata-o corretamente antes de persistir a pessoa e o endereço no banco de dados.
	 * Após salvar a pessoa, um novo {@link PessoaDTO} é retornado com os dados atualizados.
	 * </p>
	 *
	 * @param pessoaDTO o objeto {@link PessoaDTO} contendo os dados da pessoa a ser salva
	 * @return o objeto {@link PessoaDTO} contendo os dados da pessoa salva, com o ID gerado pelo sistema
	 * @throws IllegalArgumentException se o CPF informado no {@link PessoaDTO} for inválido
	 * @throws DataIntegrityViolationException se o CPF informado já estiver cadastrado no sistema
	 */
	private PessoaDTO salvarPessoaComCEP(PessoaDTO pessoaDTO){
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

		PessoaDTO novaPessoaDTO = new PessoaDTO();
		novaPessoaDTO.setId(novaPessoa.getId());
		novaPessoaDTO.setNomeCompleto(novaPessoa.getNomeCompleto());
		novaPessoaDTO.setCpf(novaPessoa.getCpf());
		novaPessoaDTO.setTelefone(novaPessoaDTO.getTelefone());
		novaPessoaDTO.setEndereco(novaPessoa.getEndereco());

		return novaPessoaDTO;
	}
}