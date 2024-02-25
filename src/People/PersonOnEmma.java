package People;
import Exceptions.*;
import Other.Stuff;
import java.util.ArrayList;
public class PersonOnEmma extends Human implements Die {
    private String name, surname;
    private JobOnShip jobOnShip;
    private Emotions emotions;
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

    }

//конструкторы
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


 //левша или правша (при создании)
    private LeftOrRightHanded randomLeftOrRightHanded(){
        float a = (float) Math.random();
        int i = Math.round(a);
        if(i == 0){
            return LeftOrRightHanded.LEFT_HANDED;
        } else return LeftOrRightHanded.RIGHT_HANDED;
    }


//работа
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



//смерть
    @Override
    public void die() {
        checkAlive("Хватит его уже убивать:(");

        System.out.println("\u001B[34m" + this + " умер\u001B[0m");
        peopleOnEmmaArrayList.remove(this);

        /*если умер капитан, надо рандомного живого человека назначить капитаном
        при округлении, если рандомом получить число от нуля до максимального индекса, у последнего вероятность меньше
        выпасть, так как вместо промежутка в 1, для него должно выпасть 0.5 "снизу", для первого так же
        например, 6 человек, индексы от 0 до 5, округление рандомного должно быть от (-0.5 до 4.5]*/
        if (this == emmaCaptain) {
            int i;

            double a = Math.random() * peopleOnEmmaArrayList.size() - 0.5;
            i = (int) Math.round(a);
            emmaCaptain = peopleOnEmmaArrayList.get(i);
            System.out.println("Умер капитан:(");
            System.out.println(peopleOnEmmaArrayList.get(i) + " стал капитаном");
        }

    }




//руки и действия с руками

    // приватный класс, контролируемо создали две руки на человека прямо во внешнем классе
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
                throw new AlreadyInHandsException();
            } catch (AlreadyInHandsException e) {
                System.out.println("\u001B[31mЭтот предмет уже в руках\u001B[0m");
            }

        //в else нормальная работа метода без ошибки с уже взятым предметом
        }   else {

            //маленькое идет в одну руку
                if (stuff.getSize() == Stuff.Size.SMALL) {

                    //левша в первую очередь занимает левую руку, если обе заняты - ошибка
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

                    //правша в первую очередь занимает правую руку, если обе заняты - ошибка
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

                    //большим сразу обе руки заполняем, если хоть одна занята - ошибка и не берем, иначе обе руки занимаем
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
        //если изначально обе руки свободны, значит, в руках ничего не было
        if (!stuff.getIsHold()) {
            try {
                throw new TwoHandsException();
            } catch (TwoHandsException e) {
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
        //если изначально чего-то не было, это не положить
        if (!stuff.getIsHold() || !stuff2.getIsHold()) {
            try {
                throw new TwoHandsException();
            } catch (TwoHandsException e) {
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




//Эмоции
    @Override
    public void setEmotions(Emotions emotions) throws InterruptedException{
        checkAlive("...Он мёртв, кстати");
        this.emotions = emotions;
            Human listeners = new Human(){
                @Override
                void doubt(Human h){
                    if(h.getEmotions().equals(Emotions.SUSPICIOUS)){
                        this.emotions = Emotions.DOUBTING;
                        System.out.println("Слушатели " + this.getEmotions());
                    }
                }

                @Override
                public void setEmotions(Emotions emotions){
                    this.emotions = emotions;
                }
                @Override
                public Emotions getEmotions(){
                    return emotions;
                }

            };
        listeners.doubt(this);
        if(emotions.equals(Emotions.SHOCKED)){
            this.passOut();
        }
    }

    private void passOut() throws InterruptedException {
        System.out.print("\u001B[34m" + this + " ");
        System.out.print("теряет сознание ... ");

        // Thread.sleep() - прерывание на время одного потока, при использовании нескольких потоков может вызвать ошибку
        // InterruptedException(проблемы во взаимодействиях потоков) - оно проверяемое, его нужно обработать
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new InterruptedException();
        }
        System.out.println("приходит в чувство\u001B[0m");
        setEmotions(Emotions.OK);
    }

    @Override
    public Emotions getEmotions(){
        return emotions;
    }
    // потеря сознания, проверки на смерть нет, так как она возникает после смены эмоций, которая уже проверила, живой ли человек




//вспомогательное
    @Override
    public String toString(){
        if(name == null && jobOnShip ==JobOnShip.OTHER) {
            return surname + "(" + leftOrRightHandedStatus + ")";
        } else if (jobOnShip != JobOnShip.OTHER && name == null) {
            return jobOnShip + " " + surname + "(" + leftOrRightHandedStatus + ")";
        } else return jobOnShip + " " + name + " " + surname + "(" + leftOrRightHandedStatus + ")";
    }

    void doubt(Human h){}

    public String getSurname(){
        return this.surname;
    }



//проверки исключений
    private void checkAlive(String messageForDied){
        if(!peopleOnEmmaArrayList.contains(this)){
            try {
                throw new AlreadyDeathException(messageForDied);
            } catch (AlreadyDeathException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void checkHands(){
        try {
            throw new TwoHandsException();
        } catch (TwoHandsException e) {
            System.out.println("\u001B[31m" + this + " не может взять предмет, так как у него заняты руки\u001B[0m");
        }
    }
    private void checkCaptain(JobOnShip jobOnShip){
        if(jobOnShip == JobOnShip.CAPTAIN) {
            if(emmaCaptain == null) {
                emmaCaptain = this;
            } else if(emmaCaptain.hashCode() == this.hashCode()){
                try {
                    throw new TwoCaptainsException();
                } catch (TwoCaptainsException e) {
                    System.out.println("\u001B[31mЭтот человек уже капитан!\u001B[0m");
                }
            } else try {
                throw new TwoCaptainsException();
            } catch (TwoCaptainsException e) {
                System.out.println("\u001B[31mПрошлого капитана придётся уволить! \u001B[0m" + emmaCaptain + " теперь никто");
                emmaCaptain.setJobOnShip(JobOnShip.OTHER);
                emmaCaptain = this;
            }
        }
    }
}