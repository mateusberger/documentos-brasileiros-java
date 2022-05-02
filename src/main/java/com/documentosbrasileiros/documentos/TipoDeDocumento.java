package com.documentosbrasileiros.documentos;

import com.documentosbrasileiros.documentos.cnpj.CNPJ;
import com.documentosbrasileiros.documentos.cpf.CPF;

/**
 *  Enum que identifica o tipo de documento
 * 
 * @author Mateus Berger
 */
public enum TipoDeDocumento {
    CPF {
        @Override
        public DocumentoInterface criarDocumento(
                String cpf
        ) throws DocumentoInvalidoException {
            return new CPF(cpf);
        }

        @Override
        public DocumentoInterface criarDocumento(
                Long cpf
        ) throws DocumentoInvalidoException {
            return new CPF(cpf);
        }
    },
    
    CNPJ {
        @Override
        public DocumentoInterface criarDocumento(
                String cnpj
        ) throws DocumentoInvalidoException {
            return new CNPJ(cnpj);
        }

        @Override
        public DocumentoInterface criarDocumento(
                Long cnpj
        ) throws DocumentoInvalidoException {
            return new CNPJ(cnpj);
        }
    };

    public static DocumentoInterface criarDocumento(
            String documento,
            TipoDeDocumento tipoDeDocumento
    ) throws DocumentoInvalidoException {

        return tipoDeDocumento.criarDocumento(documento);
    }

    public static DocumentoInterface criarDocumento(
            Long documento,
            TipoDeDocumento tipoDeDocumento
    ) throws DocumentoInvalidoException {

        return tipoDeDocumento.criarDocumento(documento);
    }

    public abstract DocumentoInterface criarDocumento(
            Long documento
    ) throws DocumentoInvalidoException;

    public abstract DocumentoInterface criarDocumento(
            String documento
    ) throws DocumentoInvalidoException;
}
