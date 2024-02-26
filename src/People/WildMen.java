package People;
import Exceptions.ImpossibleActionException;
import Other.Lokation;

import java.util.ArrayList;


public class WildMen extends Human implements Die{
    private Emotions emotions;

    private Lokation.Lokations microLokations;
    public WildMen(){}
    public static ArrayList<WildMen> wildMenArrayList = new ArrayList<>();

    {
        wildMenArrayList.add(this);
        this.emotions = Emotions.OK;
    }


    public void setMicroLokations(Lokation.Lokations microLokations){
        this.microLokations = microLokations;
    }
    @Override
    public void setEmotions(Emotions emotions) {
        if(wildMenArrayList.contains(this)) {
            this.emotions = emotions;
        } else try {
            throw new ImpossibleActionException("\u001B[31mЭтот дикарь мёртв\u001B[0m");
        } catch ( ImpossibleActionException e) {
            throw new RuntimeException(e);
        }
    }
    public Emotions getEmotions(){
        return this.emotions;
    }

    @Override
    public void die(){
        wildMenArrayList.remove(this);
        System.out.println("\u001B[35mМинус один дикарь\u001B[0m");
        if(wildMenArrayList.isEmpty()){
            System.out.println("\u001B[35mПОБЕДА!\u001B[0m Все дикари умерли");
        }
    }
    public static void howMuchWildmenStay(){
        System.out.println("(Осталось " + WildMen.wildMenArrayList.size() + " дикаря)");
    }

    public void doubt(Object o){}
}
