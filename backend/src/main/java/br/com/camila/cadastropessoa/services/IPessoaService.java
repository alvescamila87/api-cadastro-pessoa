package br.com.camila.cadastropessoa.services;

import java.util.List;
import br.com.camila.cadastropessoa.dto.PessoaDTO;
import br.com.camila.cadastropessoa.model.Pessoa;
/**
 * Interface de serviço para a entidade {@link Pessoa}.
 * <p>
 * A interface {@code IPessoaService} define os métodos necessários para realizar operações
 * de gerenciamento de {@link Pessoa}, como consultar, salvar, atualizar e excluir registros de pessoas.
 * </p>
 *
 * @see Pessoa
 * @see PessoaDTO
 *
 * @author Camila
 * @since V1
 */
public interface IPessoaService {
	/**
	 * Recupera todas as pessoas do sistema.
	 *
	 * @return uma lista contendo todas as pessoas cadastradas
	 */
	List<Pessoa> recuperarTodasPessoas();

	/**
	 * Recupera uma pessoa específica pelo seu identificador.
	 *
	 * @param idPessoa o ID da pessoa a ser recuperada
	 * @return a pessoa correspondente ao ID fornecido
	 */
	Pessoa recuperarPessoaPorId(Long idPessoa);

	/**
	 * Salva uma nova pessoa no sistema.
	 *
	 * @param pessoa o objeto {@link PessoaDTO} contendo os dados da pessoa a ser salva
	 * @return a pessoa salva, com o ID gerado pelo sistema
	 */
	PessoaDTO salvarPessoa(PessoaDTO pessoa);

	/**
	 * Atualiza os dados de uma pessoa existente.
	 *
	 * @param idPessoa o identificador da pessoa a ser atualizada
	 * @param pessoa   o objeto {@link PessoaDTO} contendo os dados atualizados da pessoa
	 * @return a pessoa atualizada
	 */
	PessoaDTO atualizar(Long idPessoa, PessoaDTO pessoa);

	/**
	 * Exclui uma pessoa do sistema.
	 *
	 * @param idPessoa o ID da pessoa a ser excluída
	 */
	void excluirPessoa(Long idPessoa);

}