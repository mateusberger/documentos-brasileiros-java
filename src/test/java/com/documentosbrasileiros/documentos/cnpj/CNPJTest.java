package com.documentosbrasileiros.documentos.cnpj;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;


/**
 *
 * @author Mateus Berger
 */
public class CNPJTest {
    
    @Test
    public void deveriaCriarCnpjComStringNaoFormatada() throws CNPJInvalidoException {
        CNPJ cnpj = new CNPJ("01220033000100");
        assertEquals("01.220.033/0001-00", cnpj.getStringComMascara());
        assertEquals("01220033000100", cnpj.getStringSemMascara());
        assertEquals(Long.valueOf(1220033000100L), cnpj.getLong());
    }

    @Test
    public void deveriaCriarCnpjComLongIncompleta() throws CNPJInvalidoException {
        CNPJ cnpj = new CNPJ(1220033000100L);
        assertEquals("01.220.033/0001-00", cnpj.getStringComMascara());
        assertEquals("01220033000100", cnpj.getStringSemMascara());
        assertEquals(Long.valueOf(1220033000100L), cnpj.getLong());
    }

    @Test
    public void deveriaCriarCnpjComStringNaoFormatadaEIncompleta() throws CNPJInvalidoException {
        CNPJ cnpj = new CNPJ("1220033000100");
        assertEquals("01.220.033/0001-00", cnpj.getStringComMascara());
        assertEquals("01220033000100", cnpj.getStringSemMascara());
        assertEquals(Long.valueOf(1220033000100L), cnpj.getLong());
    }

    @Test
    public void deveriaCriarCnpjComStringFormatadaECompleta() throws CNPJInvalidoException {
        CNPJ cnpj = new CNPJ("01.220.033/0001-00");
        assertEquals("01.220.033/0001-00", cnpj.getStringComMascara());
        assertEquals("01220033000100", cnpj.getStringSemMascara());
        assertEquals(Long.valueOf(1220033000100L), cnpj.getLong());
    }

    @Test
    public void deveriaCriarCnpjValidos() throws CNPJInvalidoException {
        new CNPJ("29.663.374/0001-79");
        new CNPJ("91.544.152/0001-00");
        new CNPJ("33.123.857/0001-58");
    }

    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharCriarCnpjInvalidos() throws CNPJInvalidoException {
        new CNPJ("29.663.474/0001-79");
        new CNPJ("91.644.152/0001-00");
        new CNPJ("33.123.867/0001-58");
    }

    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharAoCriarCnpjComStringGrandeDemais() throws CNPJInvalidoException {
        new CNPJ("533.123.857/0001-58");
    }

    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharAoCriarCnpjComLongGrandeDemais() throws CNPJInvalidoException {
        new CNPJ(533123857000158L);
    }

    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharAoCriarCnpjComCaracteresForaDoPadrao() throws CNPJInvalidoException {
        new CNPJ("33d123c857a0001b58");
    }
    
    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharAoCriarCnpjComApenasZero() throws CNPJInvalidoException {
        new CNPJ("00000000000000");
    }
    
    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharAoCriarCnpjComApenasUm() throws CNPJInvalidoException {
        new CNPJ("11111111111111");
    }
    
    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharAoCriarCnpjComApenasDois() throws CNPJInvalidoException {
        new CNPJ("22222222222222");
    }
    
    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharAoCriarCnpjComApenasTres() throws CNPJInvalidoException {
        new CNPJ("33333333333333");
    }
    
    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharAoCriarCnpjComApenasQuatro() throws CNPJInvalidoException {
        new CNPJ("44444444444444");
    }
    
    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharAoCriarCnpjComApenasCinco() throws CNPJInvalidoException {
        new CNPJ("55555555555555");
    }
    
    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharAoCriarCnpjComApenasSeis() throws CNPJInvalidoException {
        new CNPJ("66666666666666");
    }
    
    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharAoCriarCnpjComApenasSete() throws CNPJInvalidoException {
        new CNPJ("77777777777777");
    }
    
    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharAoCriarCnpjComApenasOito() throws CNPJInvalidoException {
        new CNPJ("88888888888888");
    }
    
    @Test(expected = CNPJInvalidoException.class)
    public void deveriaFalharAoCriarCnpjComApenasNove() throws CNPJInvalidoException {
        new CNPJ("99999999999999");
    }
    
}
