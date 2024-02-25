import Other.Stuff;
import People.*;
import Other.Ship;


public class Main{

    public static void main (String [] args) throws InterruptedException{

    //content based on a text
        PersonOnEmma johansen = new PersonOnEmma("Густав", "Йохансен", JobOnShip.SECOND_HELPER);
        PersonOnEmma collins = new PersonOnEmma("Коллинз", JobOnShip.CAPTAIN);
        PersonOnEmma grin = new PersonOnEmma("Грин", JobOnShip.FIRST_HELPER);
        PersonOnEmma person1 = new PersonOnEmma("Неизвестный 1");
        PersonOnEmma person2 = new PersonOnEmma("Неизвестный 2");
        PersonOnEmma person3 = new PersonOnEmma("Неизвестный 3");
        WildMen wild1 = new WildMen();
        WildMen wild2 = new WildMen();
        WildMen wild3 = new WildMen();
        WildMen wild4 = new WildMen();
        WildMen wild5 = new WildMen();
        Ship emma = new Ship("Эмма");
        Ship bditelnaya = new Ship("Бдительная");
        Stuff idol = new Stuff("страшный каменный идол", 1);

        johansen.hold(idol);
        johansen.setEmotions(Emotions.SHOCKED);
        Ship.meet(emma, bditelnaya);
        WildMen.howMuchWildmenStay();
        Ship.battle(emma, bditelnaya);
        johansen.setEmotions(Emotions.SUSPICIOUS);
        johansen.put(idol);




    //features test:)


        Stuff testStuff1 = new Stuff("штука", Stuff.Size.SMALL);
        Stuff testStuff2 = new Stuff("штука", Stuff.Size.MIDDLE);
        Stuff testStuff3 = new Stuff("штука2", Stuff.Size.SMALL);
        PersonOnEmma testPerson = new PersonOnEmma("Неизвестный n");
        johansen.hold(testStuff1);
        //johansen.hold(testStuff2);
        johansen.hold(testStuff3);
        //johansen.put(testStuff1)
        testPerson.hold(testStuff2);
        johansen.hold(testStuff2);
        johansen.put(testStuff1, testStuff3);


        Ship emmaTest2 = new Ship("Эмма");
        Ship.meet(emmaTest2, emma);
        //Ship.battle(emma, emmaTest2);


        //testPerson.die();
        //testPerson.setEmotions(Emotions.OK);
        //testPerson.setJobOnShip(JobOnShip.FIRST_HELPER);
        //testPerson.hold(testStuff1);
        //testPerson.die();


        testPerson.setJobOnShip(JobOnShip.CAPTAIN);

        //negative number as size
        Stuff testStuff4 = new Stuff("штука2", -2);
        System.out.println(testStuff4.getSize());

    }
}