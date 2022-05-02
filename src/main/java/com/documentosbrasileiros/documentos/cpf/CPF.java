package com.documentosbrasileiros.documentos.cpf;

import com.documentosbrasileiros.documentos.DocumentoInterface;
import com.documentosbrasileiros.documentos.TipoDeDocumento;

/**
 *  Representa um CPF
 *      Só pode ser construido caso seja informado CPF válido.
 * 
 * @author Mateus Berger
 */
public class CPF implements DocumentoInterface {

    private String cpfFormatado;
    
    /**
     *  Constroi um CPF usando uma String
     *      Aceita os seguintes padrões:
     *          ###.###.###-##
     *          #.###.###-##
     *          ###########
     *          #########
     * 
     * @param cpf
     * @throws CPFInvalidoException (Caso os CPF seja inválido)
     */
    public CPF(String cpf) throws CPFInvalidoException {
        this.cpfFormatado = tratarCpf(cpf);
    }
    
    /**
     *  Constroi um CPF usando um Long (Se válido)
     * 
     * @param cpf
     * @throws CPFInvalidoException 
     */
    public CPF(Long cpf) throws CPFInvalidoException {
        this.cpfFormatado = tratarCpf(cpf.toString());
    }
    
    @Override
    public TipoDeDocumento getTipoDeDocumento() {
        return TipoDeDocumento.CPF;
    }

    @Override
    public String toString() {
        return cpfFormatado;
    }

    @Override
    public String getStringComMascara() {
        return cpfFormatado;
    }
    
    @Override
    public String getStringSemMascara() {
        return limparCpf(cpfFormatado);
    }
    
    @Override
    public Long getLong(){
        return Long.valueOf(limparCpf(cpfFormatado));
    }
    
    private String tratarCpf(String cpf) throws CPFInvalidoException {
        if (ehInvalidoStringCpfEntrada(cpf)) {
            throw new CPFInvalidoException("Cpf incorreto.");
        }
        String cpfEmTratamento = limparCpf(cpf);
        cpfEmTratamento = adicionarZerosNaFrente(cpfEmTratamento);
        if (ehCpfBloqueado(cpfEmTratamento)) {
            throw new CPFInvalidoException("Cpf bloqueado.");
        }
        cpfEmTratamento = aplicarMascara(cpfEmTratamento);
        if (ehInvalidoCalculoDoCpf(cpfEmTratamento)) {
            throw new CPFInvalidoException("Cpf inválido.");
        }
        return cpfEmTratamento;
    }
    
    private boolean ehInvalidoStringCpfEntrada(String cpf){
        return !cpf.matches("([0-9]{0,3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2})|([0-9]{1,11})");
    }
    
    private boolean ehCpfBloqueado(String cpf){
        return cpf.matches("[0]{11}|[1]{11}|[2]{11}|[3]{11}|[4]{11}|[5]{11}|[6]{11}|[7]{11}|[8]{11}|[9]{11}");
    }
        
    private String limparCpf(String cpfNaoValidado){
        return cpfNaoValidado.replaceAll("[^0-9]", "");
    }
    
    private String adicionarZerosNaFrente(String cpfNaoValidado){
        if (cpfNaoValidado.length() < 11) {
          return adicionarZerosNaFrente("0" + cpfNaoValidado);
        } else {
          return cpfNaoValidado;
        }
    }
    
    private String aplicarMascara(String cpfParaMascarar){
        return 
                cpfParaMascarar.substring(0, 3) + "." +
                cpfParaMascarar.substring(3, 6) + "." +
                cpfParaMascarar.substring(6, 9) + "-" +
                cpfParaMascarar.substring(9, 11);
    }
    
    private boolean ehInvalidoCalculoDoCpf(String cpf){
        return !(validaPrimeiroDigitoCpf(cpf) && validaSegundoDigitoCpf(cpf));
    }
    
    private boolean validaPrimeiroDigitoCpf(String cpf){
        String cpfLimpo = limparCpf(cpf);

        //*** *** *** #*
        int primeiroDigitoVerificadorCpf = Character.getNumericValue(cpfLimpo.charAt(9));

        //### ### ### **
        int[] numerosCpf = cpfLimpo
                .substring(0, 9)
                .chars()
                .map(ich -> Character.getNumericValue(ich))
                .toArray();

        int multiplicador = 10;
        int totalSoma = 0;
        
        for (int n : numerosCpf) {
            totalSoma = (n * multiplicador) + totalSoma;
            multiplicador = multiplicador - 1;
        }
        
        return (totalSoma*10)%11 == primeiroDigitoVerificadorCpf;
    }
    
    private boolean validaSegundoDigitoCpf(String cpf){
        String cpfLimpo = limparCpf(cpf);
        
        //*** *** *** *#
        int segundoDigitoVerificadorCpf = Character.getNumericValue(cpfLimpo.charAt(10));
        
        //### ### ### #*
        int[] numerosCpf = cpfLimpo
                .substring(0, 10)
                .chars()
                .map(ich -> Character.getNumericValue(ich))
                .toArray();
        
        int multiplicador = 11;
        int totalSoma = 0;
        
        for (int n : numerosCpf) {
            totalSoma = (n * multiplicador) + totalSoma;
            multiplicador = multiplicador - 1;
        }
        
        return (totalSoma*10)%11 == segundoDigitoVerificadorCpf;
    }
}


