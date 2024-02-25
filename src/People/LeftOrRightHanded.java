package People;

public enum LeftOrRightHanded {
    LEFT_HANDED("левша"), RIGHT_HANDED("правша");
    final String describe;
    LeftOrRightHanded(String describe){
        this.describe = describe;
    }
    @Override
    public String toString(){
        return describe;
    }
    }
