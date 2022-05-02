package com.documentosbrasileiros.documentos.cpf;

import com.documentosbrasileiros.documentos.DocumentoInvalidoException;
import com.documentosbrasileiros.documentos.TipoDeDocumento;

/**
 *
 * @author Mateus Berger
 */
public class CPFInvalidoException extends DocumentoInvalidoException {
    
    public CPFInvalidoException(String mensagem){
        super(mensagem, TipoDeDocumento.CPF);
    }
}
