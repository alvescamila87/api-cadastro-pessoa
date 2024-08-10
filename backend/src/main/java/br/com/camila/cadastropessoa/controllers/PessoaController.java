package br.com.camila.cadastropessoa.controllers;

import br.com.camila.cadastropessoa.dto.PessoaDTO;
import br.com.camila.cadastropessoa.model.Pessoa;
import br.com.camila.cadastropessoa.services.IPessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gerenciar as operações relacionadas à pessoa.
 * <p>
 * Este controlador expõe endpoints para listar, buscar, criar, atualizar e excluir pessoas.
 * </p>
 *
 * @author Camila
 * @since V1
 */
@RestController
@RequestMapping("/pessoas")
@Tag(name = "Gerenciamento de pessoas", description = "Endpoints referentes à pessoa")
public class PessoaController {

    @Autowired
    private IPessoaService pessoaService;

    /**
     * Obtém a lista de todas as pessoas cadastradas.
     *
     * @return uma lista de {@link Pessoa} com status HTTP 200 OK
     */
    @Operation(
            summary = "Exibe a lista de pessoas cadastradas",
            description = "Fornece uma lista de todas as pessoas cadastradas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodasPessoas(){
        return ResponseEntity.ok().body(pessoaService.recuperarTodasPessoas());
    }

    /**
     * Obtém uma pessoa pelo ID.
     *
     * @param idPessoa o ID da pessoa a ser recuperada
     * @return a pessoa correspondente com status HTTP 200 OK
     */
    @Operation(
            summary = "Obtém uma pessoa pelo ID",
            description = "Fornece detalhes de uma pessoa específica identificada pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso")
    })
    @GetMapping("/{idPessoa}")
    public ResponseEntity<Pessoa> listarPessoaPorId(@PathVariable Long idPessoa){
        return ResponseEntity.ok(pessoaService.recuperarPessoaPorId(idPessoa));
    }

    /**
     * Adiciona uma nova pessoa.
     *
     * @param pessoaDTO os dados da pessoa a ser criada
     * @return os dados da pessoa criada com status HTTP 200 OK
     */
    @Operation(
            summary = "Adiciona uma nova pessoa",
            description = "Cria uma nova pessoa com base nas informações fornecidas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<PessoaDTO> inserirNovaPessoa(@RequestBody PessoaDTO pessoaDTO) {
        pessoaService.salvarPessoa(pessoaDTO);
        return ResponseEntity.ok(pessoaDTO);
    }

    /**
     * Atualiza uma pessoa existente.
     *
     * @param idPessoa o ID da pessoa a ser atualizada
     * @param pessoaDTO os novos dados da pessoa
     * @return os dados atualizados da pessoa com status HTTP 200 OK
     */
    @Operation(
            summary = "Atualiza uma pessoa existente",
            description = "Atualiza as informações de uma pessoa identificada pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (CPF inválido ou já cadastrado)")
    })
    @PutMapping("/{idPessoa}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable Long idPessoa, @RequestBody PessoaDTO pessoaDTO){
        pessoaService.atualizar(idPessoa, pessoaDTO);
        return ResponseEntity.ok(pessoaDTO);
    }

    /**
     * Remove uma pessoa pelo ID.
     *
     * @param idPessoa o ID da pessoa a ser removida
     * @return uma resposta com status HTTP 204 No Content
     */
    @Operation(
            summary = "Remove uma pessoa pelo ID",
            description = "Remove uma pessoa identificada pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pessoa removida com sucesso"),
    })
    @DeleteMapping("/{idPessoa}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long idPessoa){
        pessoaService.excluirPessoa(idPessoa);
        return ResponseEntity.noContent().build();
    }
}
