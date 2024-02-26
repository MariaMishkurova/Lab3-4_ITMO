package Other;
import Exceptions.ImpossibleActionException;
import Exceptions.ImpossibleInteractionException;
import People.PersonOnEmma;
import People.WildMen;
import java.util.ArrayList;

public class Ship {
    private final String name;
    public static ArrayList<Ship> shipsArrayList = new ArrayList<>();
    private final static ArrayList<Integer> haveMet = new ArrayList<>();
    private Lokation.Lokations lokation;

    {
        shipsArrayList.add(this);

    }
    public Ship(String name, Lokation.Lokations startplace){
        this.name = name;
        this.lokation = startplace;
    }
    public Ship(String name){
        this.name = name;
        this.lokation = Lokation.Lokations.UNKNOWN_LAND;
    }

    public void setSail(){
        if(lokation != Lokation.Lokations.UNKNOWN_LAND){
            System.out.println(this + " отчалил (был в " + lokation + ")");
        }
        this.lokation = Lokation.Lokations.SOMEWHERE_IN_WATER;
    }
    public void land(){
        this.lokation = Lokation.Lokations.ISLAND;
        //dead people aren't in arraylist, so they stay in nonexistence
        for(PersonOnEmma p : PersonOnEmma.peopleOnEmmaArrayList){
            p.setLokation(Lokation.Lokations.ISLAND);
        }
        System.out.println(this + " причалил. Теперь " + this + " " + Lokation.Lokations.ISLAND);

    }
    public Lokation.Lokations getLokation(){
        return lokation;
    }

    public String getName(){
        return name;
    }
    public static void meet(Ship s, Ship s2) {
        if (s.equals(s2)) {
            try {
                throw new ImpossibleInteractionException();
            } catch (ImpossibleInteractionException e) {
                System.out.println("\u001B[31mСамого себя встретить нельзя\u001B[0m");
            }
        } else if (s.lokation != Lokation.Lokations.SOMEWHERE_IN_WATER || s2.lokation != Lokation.Lokations.SOMEWHERE_IN_WATER){
            try{
                throw new ImpossibleInteractionException();
            } catch (ImpossibleInteractionException ex) {
                System.out.println("\u001B[31mНельзя встретиться, находясь в разных местах\u001B[0m");
            }
        } else {
            if (!haveMet.contains(s.hashCode() + s2.hashCode())) {
                haveMet.add(s.hashCode() + s2.hashCode());
                System.out.println(s + " встретился с " + s2);
            }
        }
    }
    private static boolean haveMet(Ship s, Ship s2){
        return haveMet.contains(s.hashCode() + s2.hashCode());
    }

    public static void battle(Ship s, Ship s2){
        if(!haveMet(s, s2)){
            try {
                throw new ImpossibleInteractionException("\u001B[31mКорабли не могут вступать в бой, не встретившись\u001B[0m");
            } catch (ImpossibleInteractionException e) {
                throw new RuntimeException(e);
            }
        }   else {
                    class Gun extends Weapon{
                        //зарядов ровно на 3 с эммы и всех дикарей
                        {
                            charge = 3 + WildMen.wildMenArrayList.size();
                        }
                        @Override
                        void shoot() {
                            super.shoot();
                        }
                    }
              Gun gun = new Gun();
                    if(s.lokation != Lokation.Lokations.SOMEWHERE_IN_WATER || s2.lokation != Lokation.Lokations.SOMEWHERE_IN_WATER){
                        try {
                            throw new ImpossibleInteractionException("\u001B[31mКорабли не могут вступать в бой не в водах\u001B[0m");
                        } catch (ImpossibleInteractionException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        gun.shoot();
                        if (!PersonOnEmma.getEmmaCaptain().getSurname().equals("Йохансен")) {
                            PersonOnEmma.setEmmaCaptain(search("Йохансен"));
                        }
                    }
              }
    }

    static class Weapon{
        int charge;
        void shoot() {
            int killedPeopleOnEmmaCount = 0;
            if (WildMen.wildMenArrayList.isEmpty() || PersonOnEmma.peopleOnEmmaArrayList.size() < 4) {
                try {
                    throw new ImpossibleInteractionException();
                } catch (ImpossibleInteractionException e) {
                    System.out.println("\u001B[31mУмирать некому, сражнение не произошло\u001B[0m");
                }
            } else {
                System.out.println("Началось сражение...");
                    for (int i = 0; i < charge; i++) {

                        double a = Math.random();
                        double personOnEmmaDieChance = (double) 1 / charge * 3; //approximate probability
                        int indexE;
                        int indexW;


                        //one wildman stays means we must firstly have 3 people from emma killed
                        if (WildMen.wildMenArrayList.size() == 1 && killedPeopleOnEmmaCount < 3) {
                            int whoDie = (int) Math.round(Math.random() * 2 - 0.5);
                            if (whoDie == 0 && searchAlive("Коллинз")) {
                                search("Коллинз").die();
                                killedPeopleOnEmmaCount++;
                            } else if (whoDie == 1 && searchAlive("Грин")) {
                                search("Грин").die();
                                killedPeopleOnEmmaCount++;
                            } else {
                                do {
                                    indexE = (int) Math.round(Math.random() * PersonOnEmma.peopleOnEmmaArrayList.size() - 0.5);
                                } while (checkJoAlive(PersonOnEmma.peopleOnEmmaArrayList.get(indexE)));
                                PersonOnEmma.peopleOnEmmaArrayList.get(indexE).die();
                                killedPeopleOnEmmaCount++;
                            }
                        }

                        //each of 3 people on emma died -> kill only wildmen
                        else if (killedPeopleOnEmmaCount == 3) {
                            indexW = (int) Math.round(Math.random() * WildMen.wildMenArrayList.size() - 0.5);
                            WildMen.wildMenArrayList.get(indexW).die();
                        }

                    //usual fight below - killing by random//
                        else if (a < personOnEmmaDieChance && killedPeopleOnEmmaCount < 3) {
                            int whoDie = (int) Math.round(Math.random() * 2 - 0.5);
                            if (whoDie == 0 && searchAlive("Коллинз")) {
                                search("Коллинз").die();
                                killedPeopleOnEmmaCount++;
                            } else if (whoDie == 1 && searchAlive("Грин")) {
                                search("Грин").die();
                                killedPeopleOnEmmaCount++;
                            } else {
                                do {
                                    indexE = (int) Math.round(Math.random() * PersonOnEmma.peopleOnEmmaArrayList.size() - 0.5);
                                } while (checkJoAlive(PersonOnEmma.peopleOnEmmaArrayList.get(indexE)));
                                PersonOnEmma.peopleOnEmmaArrayList.get(indexE).die();
                                killedPeopleOnEmmaCount++;
                            }
                        } else if (a >= personOnEmmaDieChance) {
                            int index = (int) Math.round(Math.random() * WildMen.wildMenArrayList.size() - 0.5);
                            WildMen.wildMenArrayList.get(index).die();
                        }
                }
            }
        }
    }

    private static boolean checkJoAlive(PersonOnEmma p) {
        if (p.getSurname().equals("Йохансен")) {
            try {
                throw new ImpossibleActionException();
                } catch (ImpossibleActionException e) {
                    System.out.println("\u001B[31mГлавные герои не умирают\u001B[0m");
                    return true;
                }
        } else return false;
    }
    public static PersonOnEmma search(String surname){
        for(PersonOnEmma p : PersonOnEmma.peopleOnEmmaArrayList){
            if(p.getSurname().equals(surname)){
                return p;
            }
        }
        return null;
    }
    private static boolean searchAlive(String surname){
        return search(surname) != null;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship s = (Ship) o;
        return this.name.equals(s.name);
    }
    @Override
    public int hashCode(){
        return this.name.hashCode();
    }
    @Override
    public String toString(){
        return "корабль " + name;
    }
}
