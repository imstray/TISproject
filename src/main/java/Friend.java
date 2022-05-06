import java.util.Random;

public class Friend {
    Random random = new Random();
    private String name;
    private int intelligence;

    public Friend(){
        this.intelligence = random.nextInt(1,4);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getIntelligence() {
        return intelligence;
    }
}
