import java.util.List;

public class ConsoleOutput implements Output {
    @Override
    public void outputString(String string) {
        System.out.println(string);
    }

    @Override
    public void outputList(List<String> list) {
        System.out.println(list);
    }

    @Override
    public void outputInt(int i) {
        System.out.println(i);
    }
}
