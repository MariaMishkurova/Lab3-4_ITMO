package People;
import Other.*;
import Exceptions.ImpossibleActionException;
import java.util.ArrayList;


public class WildMen extends Human implements Die, Cloneable{
    private Emotions emotions;
    private Location.Locations location;
    private final Ship microLocation;
    public WildMen(){}
    public static ArrayList<WildMen> wildMenArrayList = new ArrayList<>();

    {
        wildMenArrayList.add(this);
        this.emotions = Emotions.OK;
        this.microLocation = PersonOnEmma.searchShip("Бдительная");
    }

    @Override
    public void setEmotions(Emotions emotions) {
        if(wildMenArrayList.contains(this)) {
            this.emotions = emotions;
        } else try {
            throw new ImpossibleActionException("\u001B[31mЭтот дикарь мёртв\u001B[0m");
        } catch (ImpossibleActionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void die(){
        wildMenArrayList.remove(this);
        this.location = Location.Locations.SOMEWHERE_IN_NONEXISTENCE;
        System.out.println("\u001B[35mМинус один дикарь\u001B[0m");
        if(wildMenArrayList.isEmpty()){
            System.out.println("\u001B[35mПОБЕДА!\u001B[0m Все дикари умерли");
        }
    }
    public static void howMuchWildmenStay(){
        System.out.println("(Осталось " + WildMen.wildMenArrayList.size() + " дикаря)");
    }

    @Override
    public WildMen clone() throws CloneNotSupportedException{
        wildMenArrayList.add(this);
        return (WildMen)super.clone();
    }

}
