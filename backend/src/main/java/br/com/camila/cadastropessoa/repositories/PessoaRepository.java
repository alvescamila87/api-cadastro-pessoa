package br.com.camila.cadastropessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.camila.cadastropessoa.model.Pessoa;

/**
 * Repositório para a entidade {@link Pessoa}.
 * <p>
 * A interface {@code PessoaRepository} estende {@link JpaRepository}, oferecendo métodos para
 * realizar operações CRUD associadas à entidade {@link Pessoa}.
 * </p>
 *
 * @see Pessoa
 * @author Camila
 * @since V1
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
