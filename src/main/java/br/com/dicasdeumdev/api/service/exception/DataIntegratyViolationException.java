package br.com.dicasdeumdev.api.service.exception;

public class DataIntegratyViolationException extends RuntimeException {
    public DataIntegratyViolationException(String msg) {
        super(msg);
    }
}
