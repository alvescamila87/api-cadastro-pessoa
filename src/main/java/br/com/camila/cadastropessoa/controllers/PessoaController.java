package br.com.camila.cadastropessoa.controllers;

import br.com.camila.cadastropessoa.model.Pessoa;
import br.com.camila.cadastropessoa.services.IPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private IPessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodasPessoas(){
        return ResponseEntity.ok().body(pessoaService.recuperarTodasPessoas());
    }

    @GetMapping("/{idPessoa}")
    public ResponseEntity<Pessoa> listarPessoaPorId(@PathVariable Long idPessoa){
        if(idPessoa != null) {
            return ResponseEntity.ok(pessoaService.recuperarPessoaPorId(idPessoa));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pessoa> inserirNovaPessoa(@RequestBody Pessoa pessoa) {
        Pessoa resultado = pessoaService.inserirNovaPessoa(pessoa);

        if(resultado != null) {
            return ResponseEntity.status(201).body(resultado);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{idPessoa}")
    public Res
}
