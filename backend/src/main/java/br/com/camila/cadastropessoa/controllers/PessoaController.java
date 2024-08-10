package br.com.camila.cadastropessoa.controllers;

import br.com.camila.cadastropessoa.dto.PessoaDTO;
import br.com.camila.cadastropessoa.model.Pessoa;
import br.com.camila.cadastropessoa.services.IPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private IPessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodasPessoas(){
        return ResponseEntity.ok().body(pessoaService.recuperarTodasPessoas());
    }

    @GetMapping("/{idPessoa}")
    public ResponseEntity<Pessoa> listarPessoaPorId(@PathVariable Long idPessoa){
        return ResponseEntity.ok(pessoaService.recuperarPessoaPorId(idPessoa));
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> inserirNovaPessoa(@RequestBody PessoaDTO pessoaDTO) {
        pessoaService.salvarPessoa(pessoaDTO);
        return ResponseEntity.ok(pessoaDTO);
    }

    @PutMapping("/{idPessoa}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable Long idPessoa, @RequestBody PessoaDTO pessoaDTO){
        pessoaService.atualizar(idPessoa, pessoaDTO);
        return ResponseEntity.ok(pessoaDTO);
    }

    @DeleteMapping("/{idPessoa}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long idPessoa){
        pessoaService.excluirPessoa(idPessoa);
        return ResponseEntity.noContent().build();
    }
}
