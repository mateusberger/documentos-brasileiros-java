package com.documentosbrasileiros.documentos;

/**
 *  Interface para documentos
 * 
 * @author Mateus Berger
 */
public interface DocumentoInterface {
    
    /**
     *  Retorna o identificador do documento em String com máscara
     * 
     * @return String com documento com máscara
     */
    public String getStringComMascara();
    
    /**
     *  Retorna o identificador do documento em String sem mácara
     * 
     * @return String com documento sem máscara
     */
    public String getStringSemMascara();
    
    /**
     *  Retorna o identificador do documento em Long
     * 
     * @return Long com documento
     */
    public Long getLong();
    
    /**
     *  Retorno o tipo de documento (Enum TipoDeDocumento)
     * 
     * @return Enum TipoDeDocumento
     */
    public TipoDeDocumento getTipoDeDocumento();
}
