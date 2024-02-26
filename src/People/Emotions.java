package People;

public enum Emotions {
    SHOCKED("шокирован"), SUSPICIOUS("подозрительный"), OK("не испытывает эмоций"),
    DOUBTING("сомневаются"), DISCOURAGED("озадачены");
    final String describe;
    Emotions (String describe){
        this.describe = describe;
    }
    @Override
    public String toString(){
        return describe;
    }
}
