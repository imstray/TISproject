import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GameTest {


    @Test
    public void doesQuestionGetRecorded(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        Game game = new Game(testOutput, mockScanner);
        when(mockScanner.nextLine()).thenReturn("1");
        when(mockScanner.nextInt()).thenReturn(1);
        assertEquals(0,game.getQuestionsAsked());
        game.firstRound();
        assertEquals(1,game.getQuestionsAsked());
    }

    @Test
    public void doesGameGetFinished(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        Game game = new Game(testOutput, mockScanner);
        when(mockScanner.nextInt()).thenReturn(1);
        assertFalse(game.getGameHasFinished());
        game.finalRound();
        assertTrue(game.getGameHasFinished());
    }

    @Test
    public void doesAudienceGetAsked(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        Game game = new Game(testOutput, mockScanner);
        when(mockScanner.nextInt()).thenReturn(4);
        assertFalse(game.getHasAskedAudience());
        game.willYouUseLifeline();
        assertTrue(game.getHasAskedAudience());
    }

    @Test
    public void doesntLetYouChoseLifelineAlreadyUsed(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        Game game = new Game(testOutput, mockScanner);
        when(mockScanner.nextInt()).thenReturn(4).thenReturn(1);
        game.setHasAskedAudience(true);
        game.willYouUseLifeline();

        assertTrue(testOutput.getDisplayValues().contains("You have already asked the audience"));
    }

    @Test
    public void gameDoesntRunIfFinished(){
        TestOutput testOutput = new TestOutput();
        Game game = new Game(testOutput);
        game.setOutput(testOutput);
        game.setGameHasFinished(true);
        game.playGame();

        assertNull(testOutput.getNextDisplayValue());
    }

    @Test
    public void gameDoesntRunIfAnsweredIncorrectly(){
        TestOutput testOutput = new TestOutput();
        Game game = new Game(testOutput);
        game.setOutput(testOutput);
        game.setHasAnsweredIncorrectly(true);
        game.playGame();

        assertNull(testOutput.getNextDisplayValue());
    }
    @Test
    public void doesMoneyGetIncrementedForCorrectAnswer(){
        TestOutput testOutput = new TestOutput();
        Game game = new Game(testOutput);
        int moneyBefore = game.getMoneyWon();
        game.correctAnswer();

        assertNotEquals(moneyBefore,game.getMoneyWon());
    }

    @Test
    public void gamePlaysFromStartToFinish(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        Game game = new Game(testOutput, mockScanner);
        when(mockScanner.nextInt()).thenReturn(1);
        game.startUp();

        assertTrue(testOutput.getDisplayValues().contains("Thank you for your time. Hopefully you enjoyed."));
    }

    @Test
    public void gameDoesntStartIfInputIs2(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        Game game = new Game(testOutput, mockScanner);
        when(mockScanner.nextInt()).thenReturn(2);
        game.startUp();

        assertEquals("why are you here then.", testOutput.getNextDisplayValue());

    }

    @Test
    public void displaysWinnerMessageIfYouWin(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        Game game = new Game(testOutput, mockScanner);
        when(mockScanner.nextInt()).thenReturn(1);
        game.setGameHasFinished(true);
        game.startUp();

        assertTrue(testOutput.getDisplayValues().contains("I hope you enjoy yourself"));
    }

    @Test void smartestFriendAlwaysGetsAnswerRight(){
        TestOutput testOutput = new TestOutput();
        Scanner mockScanner = mock(Scanner.class);
        Game game = new Game(testOutput, mockScanner);
        game.getDisplay().showAQuestion();

        Friend testFriend = new Friend();
        testFriend.setIntelligence(4);
        int choice = game.friendsChoice(testFriend);

        assertEquals(choice, game.getDisplay().correctAnswerNumber);
    }
}
