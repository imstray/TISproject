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

    @Test
    public void doesGameGetFinished(){
        Scanner mockScanner = mock(Scanner.class);
        game.setInput(mockScanner);
        when(mockScanner.nextInt()).thenReturn(1);
        assertEquals(false,game.gameHasFinished);
        game.finalRound();
        assertEquals(true,game.gameHasFinished);
    }

    @Test
    public void doesAudienceGetAsked(){
        Scanner mockScanner = mock(Scanner.class);
        game.setInput(mockScanner);
        when(mockScanner.nextInt()).thenReturn(4);
        assertEquals(false,game.hasAskedAudience);
        game.willYouUseLifeline();
        assertEquals(true,game.hasAskedAudience);
    }

    @Test
    public void gameDoesntRunIfFinished(){
        TestOutput testOutput = new TestOutput();
        game.setOutput(testOutput);
        game.setGameHasFinished(true);
        game.playGame();

        assertNull(testOutput.getNextDisplayValue());

    }
}
