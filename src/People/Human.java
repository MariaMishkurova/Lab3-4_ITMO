package People;
import Other.Lokation;

public abstract class Human {
    private Lokation.Lokations microLokations;
    public Emotions emotions;
    public abstract void setEmotions(Emotions emotions) throws InterruptedException;
    public abstract void doubt(Object o);
}
