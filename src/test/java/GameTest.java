import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

    Game game = new Game();

    @Test
    public void doesQuestionGetRecorded(){
        Scanner mockScanner = mock(Scanner.class);
        game.setInput(mockScanner);
        when(mockScanner.nextLine()).thenReturn("1");
        when(mockScanner.nextInt()).thenReturn(1);
        assertEquals(0,game.questionsAsked);
        game.firstRound();
        assertEquals(1,game.questionsAsked);
    }
}
