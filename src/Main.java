import Other.*;
import People.*;

public class Main{

    public static void main (String [] args) throws InterruptedException, CloneNotSupportedException{

    //content based on a text
        PersonOnEmma johansen = new PersonOnEmma("Густав", "Йохансен", JobOnShip.SECOND_HELPER);
        PersonOnEmma collins = new PersonOnEmma("Коллинз", JobOnShip.CAPTAIN);
        PersonOnEmma grin = new PersonOnEmma("Грин", JobOnShip.FIRST_HELPER);
        PersonOnEmma NoName = new PersonOnEmma("Неизвестный");
        PersonOnEmma NoName2 = NoName.clone();
        PersonOnEmma NoName3 = NoName.clone();
        PersonOnEmma NoName4 = NoName.clone();
        PersonOnEmma NoName5 = NoName.clone();
        PersonOnEmma NoName6 = NoName.clone();
        PersonOnEmma NoName7 = NoName.clone();
        PersonOnEmma NoName8 = NoName.clone();

        WildMen wild1 = new WildMen();
        WildMen wild2 = wild1.clone();
        WildMen wild3 = wild1.clone();
        WildMen wild4 = wild1.clone();
        WildMen wild5 = wild1.clone();
        Ship emma = new Ship("Эмма", Location.Locations.AUCKLAND);
        Ship bditelnaya = new Ship("Бдительная");
        Stuff idol = new Stuff("идол", 1);

        johansen.hold(idol);
        BehindTheScenes sydneyScientists = new BehindTheScenes(){
            private Emotions emotions;
            @Override
            public void doubt(Object o){
                if(((Stuff)o).getName().equals("идол")){
                    this.emotions = Emotions.DISCOURAGED;
                    System.out.println("Ученые " + Location.Locations.SYDNEY + " " + emotions + " " + o);
                }
            }
        };
        sydneyScientists.doubt(idol);

        johansen.setEmotions(Emotions.SHOCKED);
        emma.setSail();
        bditelnaya.setSail();
        Ship.meet(emma, bditelnaya);
        WildMen.howMuchWildmenStay();
        Ship.battle(emma, bditelnaya);
        emma.land();
        PersonOnEmma.liveOnIsland();
        johansen.put(idol);


    //features test:)
        System.out.println("\u001B[35m---------------------------------------------------------------");
        System.out.println("СОБЫТИЯ ТЕКСТА ЗАКОНЧИЛИСЬ, НИЖЕ ПРОВЕРКА ФИЧ\u001B[0m");
        Stuff testStuff1 = new Stuff("штука", Stuff.Size.SMALL);
        Stuff testStuff2 = new Stuff("штука", Stuff.Size.MIDDLE);
        Stuff testStuff3 = new Stuff("штука2", Stuff.Size.SMALL);
        PersonOnEmma testPerson = new PersonOnEmma("Неизвестный n");
        johansen.hold(testStuff1);
        johansen.hold(testStuff2);
        johansen.hold(testStuff3);
        johansen.put(testStuff1);
        testPerson.hold(testStuff2);
        johansen.hold(testStuff2);
        johansen.put(testStuff1, testStuff3);


        //negative number as size
        Stuff testStuff4 = new Stuff("штука2", -2);
        System.out.println(testStuff4.getSize());


        Ship emmaTest2 = new Ship("Эмма");
        Ship.meet(emmaTest2, emma);
        Ship.battle(emma, emmaTest2);
    }
}