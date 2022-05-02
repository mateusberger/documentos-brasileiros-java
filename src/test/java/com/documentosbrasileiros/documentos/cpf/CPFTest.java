package com.documentosbrasileiros.documentos.cpf;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;


/**
 *  Testes de unidade do Cpf
 * 
 * @author Mateus Berger
 */
public class CPFTest {
    
    @Test
    public void deveriaCriarCpfComStringNaoFormatada() throws CPFInvalidoException {
        CPF cpf = new CPF("03382790084");
        assertEquals("033.827.900-84", cpf.getStringComMascara());
        assertEquals("03382790084", cpf.getStringSemMascara());
        assertEquals(Long.valueOf(3382790084L), cpf.getLong());
    }

    @Test
    public void deveriaCriarCpfComLongIncompleta() throws CPFInvalidoException {
        CPF cpf = new CPF(3382790084L);
        assertEquals("033.827.900-84", cpf.getStringComMascara());
        assertEquals("03382790084", cpf.getStringSemMascara());
        assertEquals(Long.valueOf(3382790084L), cpf.getLong());
    }

    @Test
    public void deveriaCriarCpfComStringNaoFormatadaEIncompleta() throws CPFInvalidoException {
        CPF cpf = new CPF("3382790084");
        assertEquals("033.827.900-84", cpf.getStringComMascara());
        assertEquals("03382790084", cpf.getStringSemMascara());
        assertEquals(Long.valueOf(3382790084L), cpf.getLong());
    }

    @Test
    public void deveriaCriarCpfComStringFormatadaECompleta() throws CPFInvalidoException {
        CPF cpf = new CPF("033.827.900-84");
        assertEquals("033.827.900-84", cpf.getStringComMascara());
        assertEquals("03382790084", cpf.getStringSemMascara());
        assertEquals(Long.valueOf(3382790084L), cpf.getLong());
    }

    @Test
    public void deveriaCriarCpfValidos() throws CPFInvalidoException {
        new CPF("716.854.020-38");
        new CPF("404.755.860-50");
    }

    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharCriarCpfInvalidos() throws CPFInvalidoException {
        new CPF("493.456.510-86");
        new CPF("404.725.460-50");
    }

    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComStringGrandeDemais() throws CPFInvalidoException {
        new CPF("5033.5827.900-84");
    }

    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComLongGrandeDemais() throws CPFInvalidoException {
        new CPF(503382790084L);
    }

    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComCaracteresForaDoPadrao1() throws CPFInvalidoException {

        new CPF("033a827b900c84");
    }
    
    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComCaracteresForaDoPadrao2() throws CPFInvalidoException {

        new CPF("033a827b900-84");
    }
    
    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComCaracteresForaDoPadrao3() throws CPFInvalidoException {

        new CPF("033.827.900c84");
    }
    
    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComApenasZero() throws CPFInvalidoException{
        
        new CPF("00000000000");
    }
    
    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComApenasUm() throws CPFInvalidoException{
        
        new CPF("11111111111");
    }
    
    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComApenasDois() throws CPFInvalidoException{
        
        new CPF("22222222222");
    }
    
    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComApenasTres() throws CPFInvalidoException{
        
        new CPF("33333333333");
    }
    
    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComApenasQuatro() throws CPFInvalidoException{
        
        new CPF("44444444444");
    }
    
    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComApenasCinco() throws CPFInvalidoException{
        
        new CPF("55555555555");
    }
    
    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComApenasSeis() throws CPFInvalidoException{
        
        new CPF("66666666666");
    }
    
    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComApenasSete() throws CPFInvalidoException{
        
        new CPF("77777777777");
    }
    
    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComApenasOito() throws CPFInvalidoException{
        
        new CPF("88888888888");
    }
    
    @Test(expected = CPFInvalidoException.class)
    public void deveriaFalharAoCriarCpfComApenasNove() throws CPFInvalidoException{
        
        new CPF("99999999999");
    }
}
