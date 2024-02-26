package People;
import Other.Location;

public abstract class Human {
    private Location.Locations microLocations;
    public Emotions emotions;
    public abstract void setEmotions(Emotions emotions) throws InterruptedException;
}
