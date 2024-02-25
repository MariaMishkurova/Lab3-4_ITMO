import Other.Stuff;
import People.*;
import Other.Ship;


public class Main{

    public static void main (String [] args) throws InterruptedException{

    //события текста
        PersonOnEmma j = new PersonOnEmma("Густав", "Йохансен", JobOnShip.SECOND_HELPER);
        PersonOnEmma col = new PersonOnEmma("Коллинз", JobOnShip.CAPTAIN);
        PersonOnEmma gr = new PersonOnEmma("Грин", JobOnShip.FIRST_HELPER);
        PersonOnEmma n = new PersonOnEmma("Неизвестный 1");
        PersonOnEmma n2 = new PersonOnEmma("Неизвестный 2");
        PersonOnEmma n3 = new PersonOnEmma("Неизвестный 3");
        WildMen w1 = new WildMen();
        WildMen w2 = new WildMen();
        WildMen w3 = new WildMen();
        WildMen w4 = new WildMen();
        WildMen w5 = new WildMen();
        Ship emma = new Ship("Эмма");
        Ship bd = new Ship("Бдительная");
        Stuff idol = new Stuff("страшный каменный идол", 1);

        j.hold(idol);
        j.setEmotions(Emotions.SHOCKED);
        Ship.meet(emma, bd);
        WildMen.howMuchWildmenStay();
        Ship.battle(emma, bd);
        j.setEmotions(Emotions.SUSPICIOUS);
        j.put(idol);




    //тест возможностей кода:)

        //левша и правша
        Stuff st = new Stuff("штука", Stuff.Size.SMALL);
        Stuff st2 = new Stuff("штука", Stuff.Size.MIDDLE);
        Stuff st3 = new Stuff("штука2", Stuff.Size.SMALL);
        PersonOnEmma test = new PersonOnEmma("Неизвестный n");
        j.hold(st);
        //j.hold(st2);
        j.hold(st3);
        //j.put(st)
        test.hold(st2);
        j.hold(st2);
        j.put(st, st3);

        //корабли
        Ship emma2 = new Ship("Эмма");
        Ship.meet(emma2, emma);
        //Ship.battle(emma, emma2);

        //умершие не могут ничего делать
        //test.die();
        //test.setEmotions(Emotions.OK);
        //test.setJobOnShip(JobOnShip.FIRST_HELPER);
        //test.hold(st);
        //test.die();

        //капитан бывает только один
        test.setJobOnShip(JobOnShip.CAPTAIN);

        //штука отрицательного размера
        Stuff st4 = new Stuff("штука2", -2);
    }
}