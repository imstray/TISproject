import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DisplayTest {

//    Display testDisplay = new Display();

    @Test
    public void onlyNumberInBoundsAllowed(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        Display testDisplay = new Display(testOutput, mockScanner);
        when(mockScanner.nextInt()).thenReturn(1);
        testDisplay.getTheChoice(1,2);
        assertNull(testOutput.getNextDisplayValue());

    }

    @Test
    public void numberOutOfBoundsNotAllowed(){
        TestOutput testOutput = new TestOutput();

        Scanner mockScanner = mock(Scanner.class);
        Display testDisplay = new Display(testOutput, mockScanner);
        when(mockScanner.nextInt()).thenReturn(3).thenReturn(1);

        testDisplay.getTheChoice(1,2);

        assertTrue(testOutput.displayValues.contains("Thats not an option"));
    }

    @Test
    public void mainMenuGetsDisplayed(){
        TestOutput testOutput = new TestOutput();
        Display testDisplay = new Display(testOutput);
        testDisplay.mainMenu();
        assertTrue(testOutput.displayValues.contains("This is 'Who Wants To Be A Somervillionare'"));
    }

    @Test
    public void correctAnswerMessageGetsDisplayed(){
        TestOutput testOutput = new TestOutput();
        Display testDisplay = new Display(testOutput);
        testDisplay.answeredCorrectly();

        assertTrue(testOutput.displayValues.contains("You got the right answer"));
    }

    @Test
    public void aQuestionGetsDisplayed(){
        TestOutput testOutput = new TestOutput();
        Display testDisplay = new Display(testOutput);
        Map<String, List<String>> testMap = new HashMap<>();
        List<String> testList = Arrays.asList("Derek Somerville", "Daniel Lyasota", "1");
        testMap.put("Who is this game based on?", testList);
        CSVreader mockReader = mock(CSVreader.class);
        testDisplay.setReader(mockReader);
        when(mockReader.getTheQuestionsAndAnswers()).thenReturn(testMap);

        testDisplay.showAQuestion();

        assertTrue(testOutput.displayValues.contains("Who is this game based on?"));
    }

}
