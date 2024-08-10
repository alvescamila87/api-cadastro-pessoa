package br.com.camila.cadastropessoa.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Classe global para tratamento de exceções na aplicação.
 * <p>
 * Esta classe usa a anotação {@code @RestControllerAdvice} para fornecer tratamento centralizado de exceções lançadas
 * pelos controladores da aplicação.
 * </p>
 *
 * @author Camila
 * @since V1
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manipulador para exceções {@code IllegalArgumentException}.
     * <p>
     * Quando uma {@code IllegalArgumentException} é lançada, este método captura a exceção e retorna uma resposta com
     * o status HTTP 400 Bad Request e a mensagem da exceção.
     * </p>
     *
     * @param ex a exceção {@code IllegalArgumentException} lançada
     * @return uma {@code ResponseEntity} contendo a mensagem da exceção e o status HTTP 400 Bad Request
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Manipulador para exceções {@code DataIntegrityViolationException}.
     * <p>
     * Quando uma {@code DataIntegrityViolationException} é lançada, este método captura a exceção e retorna uma resposta
     * com o status HTTP 400 Bad Request e uma mensagem indicando que o CPF já está cadastrado.
     * </p>
     *
     * @param ex a exceção {@code DataIntegrityViolationException} lançada
     * @return uma {@code ResponseEntity} contendo uma mensagem de erro e o status HTTP 400 Bad Request
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = "Este CPF está já cadastrado. Favor utilizar um CPF diferente.";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
