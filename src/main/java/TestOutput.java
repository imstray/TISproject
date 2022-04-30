import java.util.ArrayList;
import java.util.List;

public class TestOutput implements Output {
    List<String> displayValues = new ArrayList<>();

    @Override
    public void outputString(String string) {
        displayValues.add(string);
    }

    @Override
    public void outputList(List<String> list) {

    }

    @Override
    public void outputInt(int i) {

    }

    public String getNextDisplayValue() {
        if(displayValues.size() > 0) {
            return displayValues.remove(displayValues.size() - 1);
        }
        else {
            return null;
        }
    }

    public List<String> getDisplayValues(){
        return displayValues;
    }

}
