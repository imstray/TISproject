import java.util.*;

public class Game {
    public boolean hasAnsweredIncorrectly = false;
    boolean gameHasFinished = false;
    boolean hasPhonedFriend = false;
    boolean hasAskedAudience = false;
    boolean hasDone5050 = false;
    public int questionsAsked = 0;
    int moneyWon = 0;
    Scanner keyboard = new Scanner(System.in);
    ConsoleOutput output = new ConsoleOutput();
    Random random = new Random();

    public Display display = new Display();

    public void setInput(Scanner sc){
        this.keyboard = sc;
    }

    public void answerChecker(){
        if(!(display.correctAnswerNumber == keyboard.nextInt())){
            hasAnsweredIncorrectly = true;
        }else{
            display.answeredCorrectly();
            if(questionsAsked != 1){
                moneyWon *= 2;
            } else {
                moneyWon += 100;
            }
        }

    }

    public void firstRound(){
        display.firstQuestion();
        questionsAsked += 1;
        if(!hasDone5050 | !hasAskedAudience | !hasPhonedFriend){
            willYouUseLifeline();
        } else{
            output.outputString("Okay what are you going with?");
        }
        answerChecker();
    }

    public void secondRound(){
        display.threeQuestionsAsked();
        questionsAsked+=1;
        if(!hasDone5050 | !hasAskedAudience | !hasPhonedFriend){
            willYouUseLifeline();
        } else{
          output.outputString("Okay what are you going with?");
        }
        answerChecker();
    }

    public void finalRound(){
        display.finalQuestion();
        if(!hasDone5050 | !hasAskedAudience | !hasPhonedFriend){
            willYouUseLifeline();
        } else{
            output.outputString("Okay what are you going with?");
        }
        answerChecker();
        gameHasFinished = true;
    }

    public void askAudience(){
        int percentage = 100;
        int possibleAnswers = 4;
        int counter = 1;
        List<Integer> audienceScores = new ArrayList<>();
        while(percentage>=0 & possibleAnswers > 1){
            int randomPercentage = random.nextInt(percentage);
            audienceScores.add(randomPercentage);
            percentage-=randomPercentage;
            possibleAnswers--;
        }
        output.outputString("The audience votes are in");
        for(int i:audienceScores){
            output.outputString(i + "% believe it was " + counter);
            counter++;
        }
        if(possibleAnswers == 1){
            output.outputString(percentage +"% believe it was "+ counter);
        }

        hasAskedAudience = true;
    }

    public void fiftyfifty(){
        List<Integer> options = new ArrayList<>();
        for(int i = 1;i<5;i++){
            if(display.correctAnswerNumber!=i){
                options.add(i);
            }
        }
        int randomElement = options.get(random.nextInt(options.size()));
        options = Arrays.asList(display.correctAnswerNumber, randomElement);
        Collections.shuffle(options);
        Collections.sort(options);
        output.outputString("The 50/50 shows that its now between just 2 options");
        output.outputString("Its either " + options.get(0) );
        output.outputString("Or " + options.get(1));
        hasDone5050 = true;
    }

    public int friendsChoice(Friend f) {
        int friendRecommendation = 0;
        int answer = display.correctAnswerNumber;
        switch (f.intelligence) {
            case 1:
                friendRecommendation = random.nextInt(1, 4);
                break;

            case 2:
                if(answer==1) {
                    friendRecommendation = random.nextInt(answer, answer+2);
                } else if(answer==4) {
                    friendRecommendation = random.nextInt(answer-2,answer);
                } else{
                    friendRecommendation = random.nextInt(answer-1,answer+1);
                }

                break;

            case 3:
                friendRecommendation = switch (answer) {
                    case 1, 2, 3 -> random.nextInt(answer, answer + 1);
                    case 4 -> random.nextInt(answer - 1, answer);
                    default -> friendRecommendation;
                };

            }
        return friendRecommendation;
    }

    public void phoneAFriend(){
        display.phoneAFriend();
        Friend friend = new Friend();
        int recommendation = friendsChoice(friend);
        output.outputString("Your friend has made their decision");
        output.outputString("Your friend thinks it could be " + recommendation);
        hasPhonedFriend = true;
    }

    public void willYouUseLifeline(){
        boolean hasChosenALifeline=false;
        while(!hasChosenALifeline){
            display.wouldYouLikeToUseALifeline();
        switch (keyboard.nextInt()){
            case 1:
                hasChosenALifeline = true;
                break;
            case 2:
                if(!hasPhonedFriend){
                phoneAFriend();
                hasChosenALifeline = true;
                }else{
                    output.outputString("You have used your phone a friend");
                }
                break;
            case 3:
                if(!hasDone5050){
                fiftyfifty();
                hasChosenALifeline = true;
                } else{
                    output.outputString("You have used your 50/50");
                }
                break;
            case 4:
                if(!hasAskedAudience){
                    askAudience();
                    hasChosenALifeline = true;
                } else{
                    output.outputString("You have already asked the audience");
                }
                break;
        }
        output.outputString("Okay what will you pick?");
    }
    }
    public void playGame(){
        while(!hasAnsweredIncorrectly & !gameHasFinished){
            switch (questionsAsked) {
                case 0, 1, 2 -> firstRound();
                case 3, 4, 5 -> secondRound();
                case 6 -> finalRound();
            }
        }
    }

    public void startUp() {
        display.mainMenu();
        int choice = display.getTheChoice(1, 2);
        if (choice == 1) {
            playGame();

            if (hasAnsweredIncorrectly) {
                display.answeredIncorrectly();
            } else {
                output.outputString("Congrats you managed to win $" + moneyWon);
                display.winnerScreen();

            }
        }
        output.outputString("Thank you for your time.");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startUp();
    }
}
