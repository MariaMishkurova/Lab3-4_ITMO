package People;
import Exceptions.AlreadyDeathException;
import java.util.ArrayList;


public class WildMen extends Human implements Die{
    private Emotions emotions;
    public WildMen(){}
    public static ArrayList<WildMen> wildMenArrayList = new ArrayList<>();

    {
        wildMenArrayList.add(this);
        this.emotions = Emotions.OK;
    }


    @Override
    public void setEmotions(Emotions emotions) {
        if(wildMenArrayList.contains(this)) {
            this.emotions = emotions;
        } else try {
            throw new AlreadyDeathException("\u001B[31mЭтот дикарь мёртв\u001B[0m");
        } catch (AlreadyDeathException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Emotions getEmotions(){
        return this.emotions;
    }

    @Override
    public void die(){
        wildMenArrayList.remove(this);
        System.out.println("\u001B[34mМинус один дикарь\u001B[0m");
        if(wildMenArrayList.isEmpty()){
            System.out.println("\u001B[34mПОБЕДА!\u001B[0m Все дикари умерли");
        }
    }
    public static void howMuchWildmenStay(){
        System.out.println("осталось " + WildMen.wildMenArrayList.size() + " дикаря");
    }

    void doubt(Human h){}
}
