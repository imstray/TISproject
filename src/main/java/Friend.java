import java.util.Random;

public class Friend {
    Random random = new Random();
    String name;
    int intelligence;

    public Friend(){
        this.intelligence = random.nextInt(1,3);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
