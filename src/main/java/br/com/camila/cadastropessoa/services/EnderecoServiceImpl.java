package br.com.camila.cadastropessoa.services;

import br.com.camila.cadastropessoa.dto.EnderecoDTO;
import br.com.camila.cadastropessoa.model.Endereco;
import br.com.camila.cadastropessoa.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl {

    @Autowired
    private IEnderecoService enderecoService;

    @Autowired
    private EnderecoRepository repository;

    public Endereco buscarEnderecoPorCEP(String cep) {
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

        return repository.save(endereco);
    }

}
