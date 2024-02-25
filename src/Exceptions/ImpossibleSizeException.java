package Exceptions;

public class ImpossibleSizeException extends Exception{
    private double ft_height;

    public ImpossibleSizeException() {
        super();
        ft_height = 0.01;
    }

    public double getFt_height(){
        return ft_height;
    }

}
