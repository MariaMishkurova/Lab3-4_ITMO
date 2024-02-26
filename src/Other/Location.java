package Other;

public class Location {

    private enum GlobalLocation{
        OCEAN("в океане"), EURASIA("в Евразиии"), AFRICA("в Африке"),
        AUSTRALIA("в Австралии"), SOUTH_AMERICA("в Южной Америке"), LAND("на суше"),
        NORTH_AMERICA("в Северной Америке"), ANTARCTICA("в Антарктиде"), NONEXISTENCE("");
        final String describe;
        GlobalLocation (String describe){
            this.describe = describe;
        }
        @Override
        public String toString(){
            return this.describe;
        }
    }

    public enum Locations{
        ISLAND("в острове", GlobalLocation.OCEAN), SYDNEY("в Сиднее", GlobalLocation.AUSTRALIA),
        AUCKLAND("в Окленде",GlobalLocation.EURASIA), SOMEWHERE_IN_WATER("в водах", GlobalLocation.OCEAN),
        ROOM("в месте разговоров", GlobalLocation.EURASIA), UNKNOWN_LAND("", GlobalLocation.LAND),
        SOMEWHERE_IN_NONEXISTENCE("в небытие", GlobalLocation.NONEXISTENCE);

        final GlobalLocation globalLocation;
        final String describe;
        Locations (String describe, GlobalLocation globalLocation){
            this.describe = describe;
            this.globalLocation = globalLocation;
        }
        @Override
        public String toString(){
            return this.describe + " " + this.globalLocation;
        }
    }

}
