package People;

public enum JobOnShip {
    CAPTAIN("Капитан"), FIRST_HELPER("Первый помощник"), SECOND_HELPER("Второй помощник"), OTHER("");
    final String jobName;
    JobOnShip(String jobName){
        this.jobName = jobName;
    }
    @Override
    public String toString(){
        return jobName;
    }
}
