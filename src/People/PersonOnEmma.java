package People;
import Exceptions.*;
import Other.Location;
import Other.Ship;
import Other.Stuff;
import java.util.ArrayList;
public class PersonOnEmma extends Human implements Die, Cloneable {
    private String name, surname;
    private JobOnShip jobOnShip;
    private Emotions emotions;
    private Location.Locations location;
    private final Ship microLocation;
    private static PersonOnEmma emmaCaptain;
    public static ArrayList<PersonOnEmma> peopleOnEmmaArrayList = new ArrayList<>();
    private final Hand leftHand, rightHand;
    private final LeftOrRightHanded leftOrRightHandedStatus;

    {
        peopleOnEmmaArrayList.add(this);
        leftHand = new Hand(true);
        rightHand = new Hand(true);
        this.leftOrRightHandedStatus = randomLeftOrRightHanded();
        this.emotions = Emotions.OK;
        this.microLocation = searchShip("Эмма");
        this.location = Location.Locations.SOMEWHERE_IN_WATER;
    }
    public static Ship searchShip(String name){
        for(Ship s : Ship.shipsArrayList){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }

//constructors
    private PersonOnEmma() {
        jobOnShip = JobOnShip.OTHER;
    }
    public PersonOnEmma(String surname) {
        this();
        this.surname = surname;
    }
    public PersonOnEmma(String surname, JobOnShip jobOnShip) {
        this(surname);
        checkCaptain(jobOnShip);
        this.jobOnShip = jobOnShip;
    }
    public PersonOnEmma(String name, String surname, JobOnShip jobOnShip) {
        this(surname, jobOnShip);
        this.name = name;

    }



    public void setLokation(Location.Locations location){
        this.location = location;
        System.out.println(this + " теперь " + location);
    }



 //left- or right-handed person
    private LeftOrRightHanded randomLeftOrRightHanded(){
        float a = (float) Math.random();
        int i = Math.round(a);
        if(i == 0){
            return LeftOrRightHanded.LEFT_HANDED;
        } else return LeftOrRightHanded.RIGHT_HANDED;
    }


//job
    public void setJobOnShip(JobOnShip jobOnShip) {
        checkAlive("Бедный, даже после смерти работает...");
        checkCaptain(jobOnShip);
        this.jobOnShip = jobOnShip;
    }
    public static PersonOnEmma getEmmaCaptain(){
        return emmaCaptain;
    }
    public static void setEmmaCaptain(PersonOnEmma p){
        emmaCaptain = p;
        System.out.println("Капитаном Эммы стал " + emmaCaptain);
    }



//death
    public static void liveOnIsland() throws InterruptedException {
        int aliveCount = 0;
        for(PersonOnEmma p : PersonOnEmma.peopleOnEmmaArrayList){
            aliveCount++;
            }
        if(aliveCount < 7){
            try{
                throw new ImpossibleActionException();
            } catch (ImpossibleActionException e){
                throw new RuntimeException("Не хватает людей для действия", e);
            }
        } else {
            fall();
            Ship.search("Йохансен").setEmotions(Emotions.SUSPICIOUS);
        }
    }
    private static void fall(){
        for (int i = 0; i < 6; i++) {
            int index = (int) Math.round(Math.random() * (peopleOnEmmaArrayList.size() - 1) - 0.5);
            if (!peopleOnEmmaArrayList.get(index).surname.equals("Йохансен")) {
                System.out.println("\u001B[35m" + peopleOnEmmaArrayList.get(index) + " упал в расщелину\u001B[0m");
                peopleOnEmmaArrayList.get(index).die();
            } else i--;
        }
    }
    @Override
    public void die() {
        checkAlive("Хватит его уже убивать:(");

        System.out.println("\u001B[35m" + this + " умер\u001B[0m");
        this.setLokation(Location.Locations.SOMEWHERE_IN_NONEXISTENCE);
        peopleOnEmmaArrayList.remove(this);

        // if captain dies, make someone captain - get index by random

        if (this == emmaCaptain) {
            int i;

            double a = Math.random() * peopleOnEmmaArrayList.size() - 0.5;
            i = (int) Math.round(a);
            emmaCaptain = peopleOnEmmaArrayList.get(i);
            System.out.println("Умер капитан:(");
            System.out.println(peopleOnEmmaArrayList.get(i) + " стал капитаном");
        }

    }




//hands and interaction with other things

            private class Hand{
                boolean free;
                Hand(boolean free){
                    this.free = free;
                }
            }
    public void hold(Stuff stuff){
        checkAlive("...Он мёртв, кстати");

        if(stuff.getIsHold()){
            try {
                throw new ImpossibleActionException();
            } catch (ImpossibleActionException e) {
                System.out.println("\u001B[31mЭтот предмет уже в руках\u001B[0m");
            }


        }   else {
            //small stuff takes one hand
                if (stuff.getSize() == Stuff.Size.SMALL) {

                    //left-handed person uses left hand firstly, if both hands are full, we get exception
                    if (this.leftOrRightHandedStatus == LeftOrRightHanded.LEFT_HANDED) {
                        if (leftHand.free) {
                            leftHand.free = false;
                            stuff.setIsHold(true);
                            stuff.setInWhichHand(Stuff.InWhichHand.IN_LEFT);
                            System.out.println(this + " " + Stuff.InWhichHand.IN_LEFT + " " + stuff);
                        } else if (rightHand.free) {
                            rightHand.free = false;
                            stuff.setIsHold(true);
                            stuff.setInWhichHand(Stuff.InWhichHand.IN_RIGHT);
                            System.out.println(this + " " + Stuff.InWhichHand.IN_RIGHT + " " + stuff);
                        } else checkHands();
                    }

                    //same for right-handed
                    if (this.leftOrRightHandedStatus == LeftOrRightHanded.RIGHT_HANDED) {
                        if (rightHand.free) {
                            rightHand.free = false;
                            stuff.setIsHold(true);
                            stuff.setInWhichHand(Stuff.InWhichHand.IN_RIGHT);
                            System.out.println(this + " " + Stuff.InWhichHand.IN_RIGHT + " " + stuff);
                        } else if (leftHand.free) {
                            leftHand.free = false;
                            stuff.setIsHold(true);
                            stuff.setInWhichHand(Stuff.InWhichHand.IN_LEFT);
                            System.out.println(this + " " + Stuff.InWhichHand.IN_LEFT + " " + stuff);
                        } else checkHands();
                    }

                    //not small stuff uses both the hands
                } else {
                    if (!leftHand.free || !rightHand.free) {
                        checkHands();
                    } else {
                        leftHand.free = false;
                        rightHand.free = false;
                        stuff.setIsHold(true);
                        stuff.setInWhichHand(Stuff.InWhichHand.IN_BOTH);
                        System.out.println(this + " " + Stuff.InWhichHand.IN_BOTH + " " + stuff);
                }
            }
        }
    }

    public void put(Stuff stuff) {
        checkAlive("...Он мёртв, кстати");
        //if both the hands are free, it means that we can't put anything
        if (!stuff.getIsHold()) {
            try {
                throw new ImpossibleActionException();
            } catch (ImpossibleActionException e) {
                System.out.println("\u001B[31m" + this + " не может положить то, чего у него нет\u001B[0m");
            }
        } else {
            if (stuff.getInWhichHand() == Stuff.InWhichHand.IN_LEFT) {
                leftHand.free = true;
                stuff.setInWhichHand(Stuff.InWhichHand.NONE);
                stuff.setIsHold(false);
                System.out.println(this + " кладёт " + stuff);
            } else if (stuff.getInWhichHand() == Stuff.InWhichHand.IN_RIGHT) {
                rightHand.free = true;
                stuff.setInWhichHand(Stuff.InWhichHand.NONE);
                stuff.setIsHold(false);
                System.out.println(this + " кладёт " + stuff);
            } else {
                leftHand.free = true;
                rightHand.free = true;
                stuff.setInWhichHand(Stuff.InWhichHand.NONE);
                stuff.setIsHold(false);
                System.out.println(this + " кладёт " + stuff);
            }
        }

    }

    public void put(Stuff stuff, Stuff stuff2){
        checkAlive("...Он мёртв, кстати");

        if (!stuff.getIsHold() || !stuff2.getIsHold()) {
            try {
                throw new ImpossibleActionException();
            } catch (ImpossibleActionException e) {
                System.out.println("\u001B[31m" + this + " не может положить то, чего у него нет\u001B[0m");
            }
        } else {
            leftHand.free = true;
            rightHand.free = true;
            stuff.setIsHold(false);
            stuff2.setIsHold(false);

            System.out.println(this + " кладёт " + stuff + " и " + stuff2);
        }
    }




//Feelings
    @Override
    public void setEmotions(Emotions emotions) throws InterruptedException{
        checkAlive("...Он мёртв, кстати");
        this.emotions = emotions;
            BehindTheScenes listeners = new BehindTheScenes(){
                private Emotions emotions;
                @Override
                public void doubt(Object o){
                        if(((PersonOnEmma)o).getEmotions().equals(Emotions.SUSPICIOUS)){
                            this.emotions = Emotions.DOUBTING;
                            System.out.println("Слушатели " + Location.Locations.ROOM + " " + this.emotions);
                        }
                }
            };
        listeners.doubt(this);
        if(emotions.equals(Emotions.SHOCKED)){
            this.passOut();
        }
    }

    private void passOut() throws InterruptedException {
        System.out.print("\u001B[35m" + this + " ");
        System.out.print("теряет сознание ... ");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new InterruptedException();
        }
        System.out.println("приходит в чувство\u001B[0m");
        setEmotions(Emotions.OK);
    }

    public Emotions getEmotions(){
        return emotions;
    }




//Utility
    @Override
    public String toString(){
        if(this.surname.equals("Неизвестный")) {
            return surname;
        } else if(name == null && jobOnShip ==JobOnShip.OTHER) {
            return surname + "(" + leftOrRightHandedStatus + ")";
        } else if (jobOnShip != JobOnShip.OTHER && name == null) {
            return jobOnShip + " " + surname + "(" + leftOrRightHandedStatus + ")";
        } else return jobOnShip + " " + name + " " + surname + "(" + leftOrRightHandedStatus + ")";
    }


    public String getSurname(){
        return this.surname;
    }

    @Override
    public PersonOnEmma clone() throws CloneNotSupportedException{
        peopleOnEmmaArrayList.add(this);
        return (PersonOnEmma) super.clone();
    }



//Exceptions searching
    private void checkAlive(String messageForDied){
        if(!peopleOnEmmaArrayList.contains(this)){
            try {
                throw new ImpossibleActionException(messageForDied);
            } catch (ImpossibleActionException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void checkHands(){
        try {
            throw new ImpossibleActionException();
        } catch (ImpossibleActionException e) {
            System.out.println("\u001B[31m" + this + " не может взять предмет, так как у него заняты руки\u001B[0m");
        }
    }
    private void checkCaptain(JobOnShip jobOnShip){
        if(jobOnShip == JobOnShip.CAPTAIN) {
            if(emmaCaptain == null) {
                emmaCaptain = this;
            } else if(emmaCaptain.hashCode() == this.hashCode()){
                try {
                    throw new ImpossibleActionException();
                } catch (ImpossibleActionException e) {
                    System.out.println("\u001B[31mЭтот человек уже капитан!\u001B[0m");
                }
            } else try {
                throw new ImpossibleActionException();
            } catch (ImpossibleActionException e) {
                System.out.println("\u001B[31mПрошлого капитана придётся уволить! \u001B[0m" + emmaCaptain + " теперь никто");
                emmaCaptain.setJobOnShip(JobOnShip.OTHER);
                emmaCaptain = this;
            }
        }
    }
}
