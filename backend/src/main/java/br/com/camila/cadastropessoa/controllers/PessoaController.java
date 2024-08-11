package br.com.camila.cadastropessoa.controllers;

import br.com.camila.cadastropessoa.dto.PessoaDTO;
import br.com.camila.cadastropessoa.model.Pessoa;
import br.com.camila.cadastropessoa.services.ICSVFileService;
import br.com.camila.cadastropessoa.services.IPessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @Autowired
    private ICSVFileService csvFileService;

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

    /**
     * Exporta todos os dados de pessoas para um arquivo CSV e o disponibiliza para download.
     * <p>
     * Este método gera um arquivo CSV contendo todas as informações das pessoas armazenadas
     * no repositório. Após a geração do arquivo, ele é disponibilizado para download com o nome
     * "pessoas.csv". O método verifica se o arquivo foi gerado com sucesso e se o arquivo existe
     * antes de retornar a resposta. Se ocorrer algum erro durante a geração ou leitura do arquivo,
     * uma mensagem de erro é retornada.
     * </p>
     *
     * @return {@code ResponseEntity<byte[]>} contendo o arquivo CSV para download, ou uma mensagem
     * de erro em caso de falha. Se o arquivo for gerado com sucesso, o cabeçalho "Content-Disposition"
     * é configurado para indicar que o arquivo deve ser baixado como "pessoas.csv".
     */
    @Operation(summary = "Exportar dados de pessoas para CSV",
            description = "Gera um arquivo CSV com informações de todas as pessoas cadastradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arquivo CSV gerado com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao gerar o arquivo CSV.")
    })
    @GetMapping("/relatorio")
    public ResponseEntity<byte[]> exportarPessoasParaCSV() {
        File file = new File("./pessoas.csv");

        // Gera o CSV
        boolean arquivoGerado = csvFileService.exportFileToCsv();

        if (!arquivoGerado || !file.exists()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao gerar o arquivo CSV.".getBytes());
        }

        try (InputStream inputStream = new FileInputStream(file)) {
            byte[] fileContent = inputStream.readAllBytes();

            // Configura o cabeçalho para o arquivo CSV
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=pessoas.csv");
            headers.add("Content-Type", "text/csv");

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao ler o arquivo CSV.".getBytes());
        }
    }



}
