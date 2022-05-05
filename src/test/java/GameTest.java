import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GameTest {

    Game game = new Game();

    @Test
    public void doesQuestionGetRecorded(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        game.setInput(mockScanner);
        game.setOutput(testOutput);
        when(mockScanner.nextLine()).thenReturn("1");
        when(mockScanner.nextInt()).thenReturn(1);
        assertEquals(0,game.questionsAsked);
        game.firstRound();
        assertEquals(1,game.questionsAsked);
    }

    @Test
    public void doesGameGetFinished(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        game.setInput(mockScanner);
        game.setOutput(testOutput);
        when(mockScanner.nextInt()).thenReturn(1);
        assertFalse(game.gameHasFinished);
        game.finalRound();
        assertTrue(game.gameHasFinished);
    }

    @Test
    public void doesAudienceGetAsked(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        game.setInput(mockScanner);
        game.setOutput(testOutput);
        when(mockScanner.nextInt()).thenReturn(4);
        assertFalse(game.hasAskedAudience);
        game.willYouUseLifeline();
        assertTrue(game.hasAskedAudience);
    }

    @Test
    public void doesntLetYouChoseLifelineAlreadyUsed(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        game.setInput(mockScanner);
        game.setOutput(testOutput);
        game.display.setOutput(testOutput);
        when(mockScanner.nextInt()).thenReturn(4).thenReturn(1);
        game.setHasAskedAudience(true);
        game.willYouUseLifeline();

        assertTrue(testOutput.getDisplayValues().contains("You have already asked the audience"));
    }

    @Test
    public void gameDoesntRunIfFinished(){
        TestOutput testOutput = new TestOutput();
        game.setOutput(testOutput);
        game.setGameHasFinished(true);
        game.playGame();

        assertNull(testOutput.getNextDisplayValue());
    }

    @Test
    public void gameDoesntRunIfAnsweredIncorrectly(){
        TestOutput testOutput = new TestOutput();
        game.setOutput(testOutput);
        game.setHasAnsweredIncorrectly(true);
        game.playGame();

        assertNull(testOutput.getNextDisplayValue());
    }
    @Test
    public void doesMoneyGetIncrementedForCorrectAnswer(){
        TestOutput testOutput = new TestOutput();
        game.setOutput(testOutput);
        int moneyBefore = game.getMoneyWon();
        game.correctAnswer();

        assertNotEquals(moneyBefore,game.getMoneyWon());
    }

    @Test
    public void gamePlaysFromStartToFinish(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(1);
        game.setInput(mockScanner);
        game.display.setKeyboard(mockScanner);
        game.display.setOutput(testOutput);
        game.startUp();

        assertTrue(testOutput.getDisplayValues().contains("Thank you for your time. Hopefully you enjoyed."));
    }

    @Test
    public void gameDoesntStartIfInputIs2(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(2);
        game.setInput(mockScanner);
        game.display.setKeyboard(mockScanner);
        game.setOutput(testOutput);
        game.startUp();

        assertEquals("why are you here then.", testOutput.getNextDisplayValue());

    }

    @Test
    public void displaysWinnerMessageIfYouWin(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(1);
        game.display.setKeyboard(mockScanner);
        game.display.setOutput(testOutput);
        game.setGameHasFinished(true);
        game.startUp();

        assertTrue(testOutput.getDisplayValues().contains("I hope you enjoy yourself"));
    }
}
