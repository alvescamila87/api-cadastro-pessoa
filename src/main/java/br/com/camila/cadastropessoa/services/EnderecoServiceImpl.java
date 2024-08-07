package br.com.camila.cadastropessoa.services;

import br.com.camila.cadastropessoa.model.Endereco;
import br.com.camila.cadastropessoa.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EnderecoServiceImpl implements IEnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Override
    public Endereco findByCep(String cep) {
        return repository.getReferenceById(cep);
    }

}
