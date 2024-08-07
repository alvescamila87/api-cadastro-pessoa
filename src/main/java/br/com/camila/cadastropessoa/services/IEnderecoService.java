package br.com.camila.cadastropessoa.services;

import br.com.camila.cadastropessoa.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "enderecoViaCEP", url = "https://viacep.com.br/ws")
public interface IEnderecoService {

    @GetMapping("/{cep}/json/")
    Endereco buscarEnderecoPorCEP(@PathVariable("cep") String cep);

}
