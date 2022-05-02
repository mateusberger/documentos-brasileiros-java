package com.documentosbrasileiros.documentos;

/**
 * @author Mateus Berger
 */
public class DocumentoInvalidoException extends Exception {
    
    private TipoDeDocumento tipoDeDocumento;
    
    public DocumentoInvalidoException(String mensagem, TipoDeDocumento tipoDocumento){
        super(mensagem);
    }
    
    public DocumentoInvalidoException(String mensagem){
        super(mensagem);
    }

    /**
     * Tipo de Documento que gerou a exception
     * 
     * @return Enum TipoDeDocumento
     */
    public TipoDeDocumento getTipoDeDocumento() {
        return tipoDeDocumento;
    }
}
