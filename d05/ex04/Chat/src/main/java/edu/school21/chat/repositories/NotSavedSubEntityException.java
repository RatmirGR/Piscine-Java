package edu.school21.chat.repositories;

public class NotSavedSubEntityException extends RuntimeException{

    public NotSavedSubEntityException(String messageError){
        super(messageError);
    }
}
