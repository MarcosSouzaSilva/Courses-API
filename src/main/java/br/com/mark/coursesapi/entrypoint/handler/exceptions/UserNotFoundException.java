package br.com.mark.coursesapi.entrypoint.handler.exceptions;

public class UserNotFoundException extends Exception {


    public UserNotFoundException(String message) {
        super(message);

    }

}