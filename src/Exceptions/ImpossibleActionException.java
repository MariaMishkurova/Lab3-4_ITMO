package Exceptions;

public class ImpossibleActionException extends Exception{
    public ImpossibleActionException(){
        super();
    }
    public ImpossibleActionException(String message){
        super(message);
    }
}
