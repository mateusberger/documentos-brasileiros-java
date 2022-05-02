package com.documentosbrasileiros.documentos.cnpj;

import com.documentosbrasileiros.documentos.DocumentoInvalidoException;
import com.documentosbrasileiros.documentos.TipoDeDocumento;

/**
 *
 * @author Mateus Berger
 */
public class CNPJInvalidoException extends DocumentoInvalidoException {
    
    public CNPJInvalidoException(String mensagem){
        super(mensagem, TipoDeDocumento.CNPJ);
    }
}
