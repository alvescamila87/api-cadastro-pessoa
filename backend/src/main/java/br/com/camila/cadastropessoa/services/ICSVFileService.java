package br.com.camila.cadastropessoa.services;


/**
 * Interface para serviços de exportação de dados para arquivos CSV.
 * <p>
 * Esta interface define um método para exportar dados para um arquivo CSV.
 * O método {@code exportFileToCsv()} gera o arquivo CSV com os dados necessários e retorna um valor booleano
 * indicando o sucesso ou falha da operação.
 * </p>
 *
 * @see CSVFileGeneratorImpl
 *
 * @author Camila
 * @since V1
 */
public interface ICSVFileService {

    /**
     * Exporta dados para um arquivo CSV.
     * <p>
     * Este método coleta os dados necessários e escreve esses dados em um arquivo CSV.
     * O método retorna {@code true} se o arquivo CSV for gerado com sucesso e {@code false} caso contrário.
     * </p>
     *
     * @return {@code boolean} indicando o sucesso ou falha na geração do arquivo CSV.
     */
    boolean exportFileToCsv();
}
