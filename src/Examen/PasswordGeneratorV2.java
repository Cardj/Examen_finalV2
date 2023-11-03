package Examen;

import java.util.Random;

public class PasswordGeneratorV2 {

    public static void main(String[] args) {
        Politicascodigos politicas = new Politicascodigos();
        politicas.setRestrictMinDigits(true);
        politicas.setMinDigits(2);
        politicas.setRestrictMinUpperCaseLetters(true);
        politicas.setMinUpperCaseLetters(1);
        politicas.setRestrictMinLowerCaseLetters(true);
        politicas.setMinLowerCaseLetters(2);
        politicas.setRestrictMinNonAlphanumericCharacters(true);
        politicas.setMinNonAlphanumericCharacters(2); 
        politicas.setMinLength(8);

        GeneradorV2 generador = new GeneradorV2(politicas, 3);
        generador.generarcodigos();
    }
}

class GeneradorV2 {

    private Politicascodigos politicas;
    private int cantidadContraseñas;

    public GeneradorV2(Politicascodigos politicas, int cantidadContraseñas) {
        this.politicas = politicas;
        this.cantidadContraseñas = cantidadContraseñas;
    }

    public void generarcodigos() {
        for (int i = 0; i < cantidadContraseñas; i++) {
            String codigo = generarCodigo();
            System.out.println("Contraseña generada: " + codigo);
        }
    }

    private String generarCodigo() {
        StringBuilder codigo = new StringBuilder();

        while (!cumpleTodasLasPoliticas(codigo)) {
            codigo.setLength(0);  

            Random random = new Random();
            int longitud = politicas.getMinLength();

            while (codigo.length() < longitud) {
                char c = generarCharAleatorio();

                if (esValido(c)) {
                    codigo.append(c);
                }
            }
        }

        return codigo.toString();
    }

    private boolean cumpleTodasLasPoliticas(StringBuilder codigo) {
        return cumplePoliticaDigitos(codigo)
                && cumplePoliticaUpperCaseLetters(codigo)
                && cumplePoliticaLowerCaseLetters(codigo)
                && cumplePoliticaNonAlphanumericCharacters(codigo);
    }

    private boolean cumplePoliticaDigitos(StringBuilder codigo) {
        return !politicas.isRestrictMinDigits() || contarDigitos(codigo) >= politicas.getMinDigits();
    }

    private boolean cumplePoliticaUpperCaseLetters(StringBuilder codigo) {
        return !politicas.isRestrictMinUpperCaseLetters() || contarUpperCaseLetters(codigo) >= politicas.getMinUpperCaseLetters();
    }

    private boolean cumplePoliticaLowerCaseLetters(StringBuilder codigo) {
        return !politicas.isRestrictMinLowerCaseLetters() || contarLowerCaseLetters(codigo) >= politicas.getMinLowerCaseLetters();
    }

    private boolean cumplePoliticaNonAlphanumericCharacters(StringBuilder codigo) {
        return !politicas.isRestrictMinNonAlphanumericCharacters() || contarNonAlphanumericCharacters(codigo) >= politicas.getMinNonAlphanumericCharacters();
    }

    private int contarDigitos(StringBuilder codigo) {
        int count = 0;
        for (int i = 0; i < codigo.length(); i++) {
            if (Character.isDigit(codigo.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    private int contarUpperCaseLetters(StringBuilder codigo) {
        int count = 0;
        for (int i = 0; i < codigo.length(); i++) {
            if (Character.isUpperCase(codigo.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    private int contarLowerCaseLetters(StringBuilder codigo) {
        int count = 0;
        for (int i = 0; i < codigo.length(); i++) {
            if (Character.isLowerCase(codigo.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    private int contarNonAlphanumericCharacters(StringBuilder codigo) {
        int count = 0;
        for (int i = 0; i < codigo.length(); i++) {
            if (!Character.isLetterOrDigit(codigo.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    private char generarCharAleatorio() {
        Random random = new Random();
        char c;
        do {
            c = (char) random.nextInt(127);
        } while (!esValido(c));
        return c;
    }

    private boolean esValido(char c) {
        if (Character.isDigit(c) && politicas.isRestrictMinDigits()) {
            politicas.decrementMinDigits();
        } else if (Character.isUpperCase(c) && politicas.isRestrictMinUpperCaseLetters()) {
            politicas.decrementMinUpperCaseLetters();
        } else if (Character.isLowerCase(c) && politicas.isRestrictMinLowerCaseLetters()) {
            politicas.decrementMinLowerCaseLetters();
        } else if (!Character.isLetterOrDigit(c) && politicas.isRestrictMinNonAlphanumericCharacters()) {
            politicas.decrementMinNonAlphanumericCharacters();
        } else if (c == '@' || c == '' || c == '6' || c == 'A' || c == '' || c == '') {
            return false;  
        }

        return true;
    }
}

class Politicascodigos {

    private boolean restrictMinDigits = false;
    private int minDigits = 0;
    private boolean restrictMinUpperCaseLetters = false;
    private int minUpperCaseLetters = 0;
    private boolean restrictMinLowerCaseLetters = false;
    private int minLowerCaseLetters = 0;
    private boolean restrictMinNonAlphanumericCharacters = false;
    private int minNonAlphanumericCharacters = 0;
    private int minLength = 0;

    public boolean isRestrictMinDigits() {
        return restrictMinDigits;
    }

    public void setRestrictMinDigits(boolean restrictMinDigits) {
        this.restrictMinDigits = restrictMinDigits;
    }

    public int getMinDigits() {
        return minDigits;
    }

    public void setMinDigits(int minDigits) {
        this.minDigits = minDigits;
    }

    public void decrementMinDigits() {
        if (restrictMinDigits && minDigits > 0) {
            minDigits--;
        }
    }

    public boolean isRestrictMinUpperCaseLetters() {
        return restrictMinUpperCaseLetters;
    }

    public void setRestrictMinUpperCaseLetters(boolean restrictMinUpperCaseLetters) {
        this.restrictMinUpperCaseLetters = restrictMinUpperCaseLetters;
    }

    public int getMinUpperCaseLetters() {
        return minUpperCaseLetters;
    }

    public void setMinUpperCaseLetters(int minUpperCaseLetters) {
        this.minUpperCaseLetters = minUpperCaseLetters;
    }

    public void decrementMinUpperCaseLetters() {
        if (restrictMinUpperCaseLetters && minUpperCaseLetters > 0) {
            minUpperCaseLetters--;
        }
    }

    public boolean isRestrictMinLowerCaseLetters() {
        return restrictMinLowerCaseLetters;
    }

    public void setRestrictMinLowerCaseLetters(boolean restrictMinLowerCaseLetters) {
        this.restrictMinLowerCaseLetters = restrictMinLowerCaseLetters;
    }

    public int getMinLowerCaseLetters() {
        return minLowerCaseLetters;
    }

    public void setMinLowerCaseLetters(int minLowerCaseLetters) {
        this.minLowerCaseLetters = minLowerCaseLetters;
    }

    public void decrementMinLowerCaseLetters() {
        if (restrictMinLowerCaseLetters && minLowerCaseLetters > 0) {
            minLowerCaseLetters--;
        }
    }

    public boolean isRestrictMinNonAlphanumericCharacters() {
        return restrictMinNonAlphanumericCharacters;
    }

    public void setRestrictMinNonAlphanumericCharacters(boolean restrictMinNonAlphanumericCharacters) {
        this.restrictMinNonAlphanumericCharacters = restrictMinNonAlphanumericCharacters;
    }

    public int getMinNonAlphanumericCharacters() {
        return minNonAlphanumericCharacters;
    }

    public void setMinNonAlphanumericCharacters(int minNonAlphanumericCharacters) {
        this.minNonAlphanumericCharacters = minNonAlphanumericCharacters;
    }

    public void decrementMinNonAlphanumericCharacters() {
        if (restrictMinNonAlphanumericCharacters && minNonAlphanumericCharacters > 0) {
            minNonAlphanumericCharacters--;
        }
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }
}
