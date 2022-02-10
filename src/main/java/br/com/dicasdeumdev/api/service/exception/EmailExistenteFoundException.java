package br.com.dicasdeumdev.api.service.exception;

public class EmailExistenteFoundException extends Exception {
    public EmailExistenteFoundException(String msg) {
        super(msg);
    }
}
