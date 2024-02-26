package Other;

public class Lokation {

    private enum GlobalLokation{
        OCEAN("в океане"), EURASIA("в Евразиии"), AFRICA("в Африке"),
        AUSTRALIA("в Австралии"), SOUTH_AMERICA("в Южной Америке"), LAND("на суше"),
        NORTH_AMERICA("в Северной Америке"), ANTARCTICA("в Антарктиде"), NONEXISTENCE("");
        final String describe;
        GlobalLokation (String describe){
            this.describe = describe;
        }
        @Override
        public String toString(){
            return this.describe;
        }
    }

    public enum Lokations{
        ISLAND("в острове", GlobalLokation.OCEAN), SYDNEY("в Сиднее", GlobalLokation.AUSTRALIA),
        AUCKLAND("в Окленде",GlobalLokation.EURASIA), SOMEWHERE_IN_WATER("в водах", GlobalLokation.OCEAN),
        ROOM("в месте разговоров", GlobalLokation.EURASIA), UNKNOWN_LAND("", GlobalLokation.LAND),
        SOMEWHERE_IN_NONEXISTENCE("в небытие", GlobalLokation.NONEXISTENCE);

        final GlobalLokation globalLokation;
        final String describe;
        Lokations (String describe, GlobalLokation globalLokation){
            this.describe = describe;
            this.globalLokation = globalLokation;
        }
        @Override
        public String toString(){
            return this.describe + " " + this.globalLokation;
        }
    }

}
