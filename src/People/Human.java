package People;

public abstract class Human {
    public Emotions emotions;
    public abstract void setEmotions(Emotions emotions) throws InterruptedException;
    public abstract Emotions getEmotions();
    abstract void doubt(Human h);

}
