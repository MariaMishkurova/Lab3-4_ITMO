package Other;
import Exceptions.InteractionWithItselfException;
import Exceptions.MainCharactersNeverDieException;
import Exceptions.NotMetException;
import People.PersonOnEmma;
import People.WildMen;
import java.util.ArrayList;

public class Ship {
    private final String name;
    private final static ArrayList<Integer> haveMet = new ArrayList<>();

    public Ship(String name){
        this.name = name;
    }

    public static void meet(Ship s, Ship s2) {
        if (s.equals(s2)) {
            try {
                throw new InteractionWithItselfException();
            } catch (InteractionWithItselfException e) {
                System.out.println("\u001B[31mСамого себя встретить нельзя\u001B[0m");
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
                throw new NotMetException("\u001B[31mКорабли не могут вступать в бой, не встретившись\u001B[0m");
            } catch (NotMetException e) {
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
              gun.shoot();
              if(!PersonOnEmma.getEmmaCaptain().getSurname().equals("Йохансен")) {
                  PersonOnEmma.setEmmaCaptain(search("Йохансен"));
              }
              }
    }


    static class Weapon{
        int charge;
        void shoot() {
            int killedPeopleOnEmma = 0; //счетчик убитых с эммы - должен дойти до 3
            if (WildMen.wildMenArrayList.isEmpty() || PersonOnEmma.peopleOnEmmaArrayList.size() < 4) {
                try {
                    throw new InteractionWithItselfException();
                } catch (InteractionWithItselfException e) {
                    System.out.println("\u001B[31mУмирать некому, сражнение не произошло\u001B[0m");
                }
            } else {
                System.out.println("Началось сражение...");
                    for (int i = 0; i < charge; i++) {

                        double a = Math.random();
                        double personOnEmmaDieChance = (double) 1 / charge * 3; //примерная вероятность, но мы все равно контролируем
                        int indexE;
                        int indexW;


                        //если дикарь один, но еще не успел убить троих с эммы (по тексту), выстрел точно по людям с эммы
                        //когда бьем по людям с эммы, добиваем Коллинза и Грина, третий рандом
                        if (WildMen.wildMenArrayList.size() == 1 && killedPeopleOnEmma < 3) {
                            int whoDie = (int) Math.round(Math.random() * 2 - 0.5);
                            if (whoDie == 0 && searchAlive("Коллинз")) {
                                search("Коллинз").die();
                                killedPeopleOnEmma++;
                            } else if (whoDie == 1 && searchAlive("Грин")) {
                                search("Грин").die();
                                killedPeopleOnEmma++;
                            } else {
                                do {
                                    indexE = (int) Math.round(Math.random() * PersonOnEmma.peopleOnEmmaArrayList.size() - 0.5);
                                } while (checkJoAlive(PersonOnEmma.peopleOnEmmaArrayList.get(indexE)));
                                PersonOnEmma.peopleOnEmmaArrayList.get(indexE).die();
                                killedPeopleOnEmma++;
                            }
                        }

                        //если всех нужных добили с эммы, умирают только дикари, больше 3 не убиваем
                        else if (killedPeopleOnEmma == 3) {
                            indexW = (int) Math.round(Math.random() * WildMen.wildMenArrayList.size() - 0.5);
                            WildMen.wildMenArrayList.get(indexW).die();
                        }

                    /*если условий выше не выполнилось - обычное сражение на рандоме
                    либо по дикарям, либо по людям с эммы, вероятность примерно соответсвует соотношению убитых в конце
                    округлением рандомного числа от -0,5 до (количества - 0,5) получаем индекс в массиве человека (математическое
                    объяснение см в PersonOnEmma die())*/
                        else if (a < personOnEmmaDieChance && killedPeopleOnEmma < 3) {
                            int whoDie = (int) Math.round(Math.random() * 2 - 0.5);
                            if (whoDie == 0 && searchAlive("Коллинз")) {
                                search("Коллинз").die();
                                killedPeopleOnEmma++;
                            } else if (whoDie == 1 && searchAlive("Грин")) {
                                search("Грин").die();
                                killedPeopleOnEmma++;
                            } else {
                                do {
                                    indexE = (int) Math.round(Math.random() * PersonOnEmma.peopleOnEmmaArrayList.size() - 0.5);
                                } while (checkJoAlive(PersonOnEmma.peopleOnEmmaArrayList.get(indexE)));
                                PersonOnEmma.peopleOnEmmaArrayList.get(indexE).die();
                                killedPeopleOnEmma++;
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
                throw new MainCharactersNeverDieException();
                } catch (MainCharactersNeverDieException e) {
                    System.out.println("\u001B[31mГлавные герои не умирают\u001B[0m");
                    return true;
                }
        } else return false;
    }
    private static PersonOnEmma search(String surname){
        for(PersonOnEmma p : PersonOnEmma.peopleOnEmmaArrayList){
            if(p.getSurname().equals(surname)){
                return p;
            }
        }
        return null;
    }
    private static boolean searchAlive(String surname){
        return search(surname) != null; //есть или нет в эррейлисте = жив или нет
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
