package br.com.camila.cadastropessoa.services;

import br.com.camila.cadastropessoa.model.Pessoa;
import br.com.camila.cadastropessoa.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * Implementação do serviço para gerar arquivos CSV com informações de pessoas.
 * <p>
 * Este serviço utiliza o repositório de pessoas para buscar todos os registros e
 * gerar um arquivo CSV contendo os dados de cada pessoa.
 * O arquivo CSV é gerado na raiz do projeto e não é sobrescrito se já existir.
 * </p>
 *
 * @see ICSVFileService
 *
 * @author Camila
 * @since V1
 */
@Service
public class CSVFileGeneratorImpl implements ICSVFileService{

    @Autowired
    private PessoaRepository pessoaRepository;

    /**
     * Exporta todos os dados de pessoas para um arquivo CSV.
     * <p>
     * Este método consulta diretamente o repositório para obter todos os registros de pessoas,
     * gera um arquivo CSV e escreve os dados no arquivo. O arquivo CSV é gerado na raiz do projeto
     * e não será sobrescrito se já existir. Caso o arquivo não seja gerado com sucesso,
     * o método retorna {@code false}.
     * </p>
     *
     * @return {@code true} se o arquivo CSV for gerado com sucesso, {@code false} em caso de erro.
     */
    @Override
    public boolean exportFileToCsv() {
        List<Pessoa> listaPessoas = pessoaRepository.findAll();

        File file = new File("./pessoas.csv");

        try (FileWriter fw = new FileWriter(file, false)) {

            if (file.length() == 0) {
                fw.write("Nome;CPF;Telefone;Logradouro;Numero;Complemento;Bairro;Cidade;Estado;CEP\n");
            }

            for (Pessoa pessoa : listaPessoas) {
                String linha = pessoa.getNomeCompleto() + ";" +
                        pessoa.getCpf() + ";" +
                        pessoa.getTelefone() + ";" +
                        pessoa.getEndereco().getLogradouro() + ";" +
                        pessoa.getEndereco().getNumero() + ";" +
                        pessoa.getEndereco().getComplemento() + ";" +
                        pessoa.getEndereco().getBairro() + ";" +
                        pessoa.getEndereco().getLocalidade() + ";" +
                        pessoa.getEndereco().getUf() + ";" +
                        pessoa.getEndereco().getCep() + "\n";

                fw.write(linha);
            }

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
