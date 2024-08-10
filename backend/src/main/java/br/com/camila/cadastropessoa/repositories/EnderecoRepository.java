package br.com.camila.cadastropessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.camila.cadastropessoa.model.Endereco;
/**
 * Repositório para a entidade {@link Endereco}.
 * <p>
 * A interface {@code EnderecoRepository} estende {@link JpaRepository}, fornecendo métodos
 * para operações CRUD relacionadas à entidade {@link Endereco}.
 *
 * @see Endereco
 * @author Camila
 * @since V1
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
