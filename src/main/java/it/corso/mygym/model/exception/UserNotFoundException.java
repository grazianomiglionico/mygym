package it.corso.mygym.model.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String msg, final Long userId){
        super(String.format(msg, userId));
    }
}
