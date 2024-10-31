package br.com.helpcar.service;

import java.util.regex.Pattern;

public class ValidadorUtil {
    public static boolean isCPFValid(String cpf) {
        // Remove caracteres especiais e espaços
        cpf = cpf.replaceAll("[.-]", "");

        // Valida o tamanho do CPF
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (10 - i);
        }
        int resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11) {
            resto = 0;
        }
        if (resto != Integer.parseInt(String.valueOf(cpf.charAt(9)))) {
            return false;
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (11 - i);
        }
        resto = 11 - (soma % 11);
        if (resto == 10 || resto == 11) {
            resto = 0;
        }
        if (resto != Integer.parseInt(String.valueOf(cpf.charAt(10)))) {
            return false;
        }

        // CPF válido
        return true;
    }

    public static boolean isTelefoneValid(String telefone) {
        // Remove caracteres especiais e espaços
        telefone = telefone.replaceAll("[^0-9]", "");

        // Valida o tamanho do telefone
        if (telefone.length() != 11) {
            return false;
        }

        // Valida o formato do telefone
        String regex = "^(\\d{2})\\d{4,5}(\\d{4})";
        if (!Pattern.matches(regex, telefone)) {
            return false;
        }

        // Telefone válido
        return true;
    }

    public static boolean isEmailValid(String email) {
        // Expressão regular para validar emails
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        // Verifica se o email corresponde à expressão regular
        return Pattern.matches(regex, email);
    }

    public static boolean isPlacaValid(String placa) {
        // Remove espaços e caracteres especiais
        placa = placa.replaceAll("[^A-Za-z0-9]", "");
        placa = placa.toUpperCase();

        // Valida o tamanho da placa
        if (placa.length() != 7 && placa.length() != 8) {
            return false;
        }

        // Valida o formato da placa (padrão Mercosul)
        if (placa.length() == 7) {
            // Padrão Mercosul (3 letras, 4 números)
            String regex = "^[A-Z]{3}[0-9]{4}$";
            if (Pattern.matches(regex, placa)) {
                return true; // Se coincidir com o padrão Mercosul, é válido
            } else  {
                // Padrão antigo (3 letras, 1 número, 1 letra, 2 números)
                regex = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$";
                if (Pattern.matches(regex, placa)) {
                    return true; // Se coincidir com o padrão antigo, é válido
                }
            }
        }
        // Placa inválida
        return false;
    }
}
