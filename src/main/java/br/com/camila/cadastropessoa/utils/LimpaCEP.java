package br.com.camila.cadastropessoa.utils;

public class LimpaCEP {

    public static String limpaCep(String cep) {
        if (cep != null) {
            // Remove todos os espaços, traços e letras
            return cep.replaceAll("[^0-9]", "");
        }
        return cep;
    }

}
