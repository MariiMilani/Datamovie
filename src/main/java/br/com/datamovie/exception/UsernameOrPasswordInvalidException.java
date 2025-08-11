package br.com.datamovie.exception;

public class UsernameOrPasswordInvalidException extends RuntimeException{

    public UsernameOrPasswordInvalidException (String message){
        super(message);
    }
}
