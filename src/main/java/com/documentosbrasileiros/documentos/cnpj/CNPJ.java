package com.documentosbrasileiros.documentos.cnpj;

import com.documentosbrasileiros.documentos.DocumentoInterface;
import com.documentosbrasileiros.documentos.TipoDeDocumento;

/**
 *  Representa um CNPJ
 *      Só pode ser construido caso seja informado CNPJ válido.
 * 
 * @author Mateus Berger
 */
public class CNPJ implements DocumentoInterface {

    private String cnpjFormatado;

    /**
     *  Constroi um CNPJ usando uma String
     *      Aceita os seguintes padrões:
     *          ##.###.###/####-##
     *          #.###.###/####-##
     *          ##############
     *          ############
     * 
     * @param cnpj
     * @throws CNPJInvalidoException (caso o CNPJ seja inválido)
     */
    public CNPJ(String cnpj) throws CNPJInvalidoException {
        cnpjFormatado = tratarCnpj(cnpj);
    }

    /**
     *  Constroi um CNPJ usando um Long (Se válido)
     * 
     * @param cnpj
     * @throws CNPJInvalidoException 
     */
    public CNPJ(Long cnpj) throws CNPJInvalidoException {
        cnpjFormatado = tratarCnpj(cnpj.toString());
    }

    @Override
    public String getStringComMascara() {
        return cnpjFormatado;
    }

    @Override
    public String getStringSemMascara() {
        return limparCnpj(cnpjFormatado);
    }

    @Override
    public Long getLong() {
        return Long.valueOf(limparCnpj(cnpjFormatado));
    }

    @Override
    public TipoDeDocumento getTipoDeDocumento() {
        return TipoDeDocumento.CNPJ;
    }

    private String tratarCnpj(String cnpj) throws CNPJInvalidoException {
        if (ehInvalidoStringCnpjEntrada(cnpj)) {
            throw new CNPJInvalidoException("Cnpj inválido.");
        }

        String cnpjEmTratamento = limparCnpj(cnpj);
        cnpjEmTratamento = adicionarZerosNaFrente(cnpjEmTratamento);
        if (ehBloqueadoCnpj(cnpjEmTratamento)) {
            throw new CNPJInvalidoException("Cnpj inválido.");
        }

        if (ehInvalidoCalculoDoCnpj(cnpjEmTratamento)) {
            throw new CNPJInvalidoException("Cnpj inválido.");
        }

        cnpjEmTratamento = aplicarMascara(cnpjEmTratamento);
        return cnpjEmTratamento;
    }

    private boolean ehInvalidoStringCnpjEntrada(String cnpj) {
        return !cnpj.matches("([0-9]{0,2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2})|([0-9]{1,14})");
    }

    private String limparCnpj(String cnpjNaoValidado) {
        return cnpjNaoValidado.replaceAll("[^0-9]", "");
    }

    private String adicionarZerosNaFrente(String cnpjNaoValidado) {
        if (cnpjNaoValidado.length() < 14) {
            return adicionarZerosNaFrente("0" + cnpjNaoValidado);
        } else {
            return cnpjNaoValidado;
        }
    }
    
    private boolean ehBloqueadoCnpj(String cnpjEmTratamento) {
        return cnpjEmTratamento.matches("([0]{14})");
    }

    private String aplicarMascara(String cnpjParaMascarar) {
        return cnpjParaMascarar.substring(0, 2) + "."
                + cnpjParaMascarar.substring(2, 5) + "."
                + cnpjParaMascarar.substring(5, 8) + "/"
                + cnpjParaMascarar.substring(8, 12) + "-"
                + cnpjParaMascarar.substring(12, 14);
    }

    private boolean ehInvalidoCalculoDoCnpj(String cnpj) {
        return !(validaPrimeiroDigitoCnpj(cnpj) && validaSegundoDigitoCnpj(cnpj));
    }

    private boolean validaPrimeiroDigitoCnpj(String cnpj) {
        final int[] multiplicadores = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int digitoVerificado = Character.getNumericValue(cnpj.charAt(12));

        int[] numerosCnpj = cnpj
                .substring(0, 12)
                .chars()
                .map(ich -> Character.getNumericValue(ich))
                .toArray();

        int totalSoma = 0;

        for (int i = 0; i < 12; i++) {
            totalSoma = totalSoma + (numerosCnpj[i] * multiplicadores[i]);
        }

        int resto = totalSoma % 11;

        if (resto < 2 && digitoVerificado == 0) {
            return true;
        }

        return (11 - resto) == digitoVerificado;
    }

    private boolean validaSegundoDigitoCnpj(String cnpj) {
        final int[] multiplicadores = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int digitoVerificado = Character.getNumericValue(cnpj.charAt(13));

        int[] numerosCnpj = cnpj
                .substring(0, 13)
                .chars()
                .map(ich -> Character.getNumericValue(ich))
                .toArray();

        int totalSoma = 0;

        for (int i = 0; i < 13; i++) {
            totalSoma = totalSoma + (numerosCnpj[i] * multiplicadores[i]);
        }

        int resto = totalSoma % 11;

        if (resto < 2 && digitoVerificado == 0) {
            return true;
        }

        return (11 - resto) == digitoVerificado;
    }
}
