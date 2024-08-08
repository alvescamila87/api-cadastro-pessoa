package br.com.camila.cadastropessoa.utils;

import java.util.InputMismatchException;

public class ValidaCPF {

    public static boolean isCPF(String CPF) {
        // Remove caracteres especiais do CPF
        CPF = CPF.replaceAll("\\D", "");

        // Verifica se o CPF tem 11 dígitos ou se é uma sequência de números iguais
        if (CPF.length() != 11 || CPF.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Cálculo do primeiro dígito verificador
            int soma = 0, peso = 10;
            for (int i = 0; i < 9; i++) {
                soma += (CPF.charAt(i) - '0') * peso--;
            }

            int digito1 = 11 - (soma % 11);
            digito1 = (digito1 > 9) ? 0 : digito1;

            // Cálculo do segundo dígito verificador
            soma = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                soma += (CPF.charAt(i) - '0') * peso--;
            }

            int digito2 = 11 - (soma % 11);
            digito2 = (digito2 > 9) ? 0 : digito2;

            // Verifica se os dígitos calculados são iguais aos dígitos do CPF
            return (digito1 == CPF.charAt(9) - '0' && digito2 == CPF.charAt(10) - '0');
        } catch (InputMismatchException e) {
            return false;
        }
    }

}
