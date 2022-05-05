import java.util.*;

public class Display {

    private Output output;
    CSVreader reader;
    Scanner keyboard;
    String questionBeingAsked = "";
    public int correctAnswerNumber;
    String correctAnswerString = "";
    List<String> outputs = new ArrayList<>();

    public Display(Output output){
        this.output = output;
        this.keyboard = new Scanner(System.in);
        this.reader = new CSVreader();
    }

    public Display(Output output, Scanner scanner){
        this.output = output;
        this.keyboard = scanner;
        this.reader = new CSVreader();
    }

    private List<String> questionsThatHaveBeenAsked = new ArrayList<>();

    public void setOutput(Output output){
        this.output = output;
    }

    public void setKeyboard(Scanner scanner){
        this.keyboard = scanner;
    }

    public void setReader(CSVreader reader){
        this.reader = reader;
    }

    public List<String> getQuestionsThatHaveBeenAsked(){
        return questionsThatHaveBeenAsked;
    }

    public void mainMenu(){
        outputs.add("Hello and welcome!");
        outputs.add("This is 'Who Wants To Be A Somervillionare'");
        outputs.add("Would you like to play?");
        outputs.add("1. Yes");
        outputs.add("2. No");
        userChoice();
    }

    public void wouldYouLikeToUseALifeline(){
        outputs.add("You still have lifelines available");
        outputs.add("Would you like to answer yourself or use a lifeline?");
        outputs.add("1. Answer");
        outputs.add("2. Phone a Friend");
        outputs.add("3. 50/50");
        outputs.add("4. Ask the Audience");
        userChoice();
    }

    public void phoneAFriend(){
        outputs.add("You have chosen to phone a friend");
        outputs.add("You are calling daniel");
        createBorder(outputs);
    }
    public void answeredCorrectly(){
        outputs.add("Well done");
        outputs.add("You got the right answer");
        createBorder(outputs);
    }

    public void winnerScreen(){
        outputs.add("I hope you enjoy yourself");
        outputs.add("And remember to send me pictures");
        createBorder(outputs);
    }

    public void answeredIncorrectly(){
        outputs.add("Nooooooo");
        outputs.add("Its over");
        outputs.add("You will not be relaxing with derek");
        createBorder(outputs);
    }

    public void firstQuestion(){
        outputs.add("Okay, I hope you're ready to play");
        outputs.add("Here we go, your question is coming up");
        createBorder(outputs);
        showTheAnswerOptions(showAQuestion());
    }

    public void threeQuestionsAsked(){
        outputs.add("You're doing great");
        outputs.add("Let's see how much further you can get.");
        createBorder(outputs);
        showTheAnswerOptions(showAQuestion());
    }

    public void finalQuestion(){
        outputs.add("This is it");
        outputs.add("For a chance to win a private holiday with derek himself.");
        outputs.add("Answer the following question correctly");
        createBorder(outputs);
        showTheAnswerOptions(showAQuestion());
    }

    public void endMessage(boolean hasAnsweredIncorrectly, int moneyWon){
        if(hasAnsweredIncorrectly) {
            answeredIncorrectly();
        } else {
            outputs.add("Congratulations you managed to win a whopping " + moneyWon + " Somerville Bux");
            winnerScreen();
        }
        output.outputString("Thank you for your time. Hopefully you enjoyed.");
    }

    public String showAQuestion(){
        boolean aQuestionHasBeenAsked = false;

        while(!aQuestionHasBeenAsked){
        for(Map.Entry<String, List<String>> entry:reader.getTheQuestionsAndAnswers().entrySet()){
            String question = entry.getKey();
            List<String> answers = entry.getValue();

            if(randomBoolean()){

                if(!questionsThatHaveBeenAsked.contains(question)){
                    outputs.add(question);
                    createBorder(outputs);
                    questionsThatHaveBeenAsked.add(question);
                    aQuestionHasBeenAsked = true;
                    questionBeingAsked = question;

                    int indexOfCorrectAnswer= Integer.parseInt(answers.get(answers.size()-1));
                    correctAnswerString = answers.get(indexOfCorrectAnswer-1);
                    return question;

                }

            }

        }
        }
        return null;
    }

    public void showTheAnswerOptions(String questionAsked){
        Random random = new Random();
        for(Map.Entry<String, List<String>> entry:reader.theQuestionsAndAnswers.entrySet()){
            String question = entry.getKey();
            List<String> answers = entry.getValue();
            if(question.equals(questionAsked)){
                answers.remove(answers.size()-1);
                Collections.shuffle(answers,random);
                for(int i=0;i<4;i++){
                    output.outputString(i+1 + ". " + answers.get(i));
                    if(Objects.equals(answers.get(i), correctAnswerString)){
                        setCorrectAnswer(i+1);
                    }
                }
            }
        }
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswerNumber = correctAnswer;
    }

    public boolean randomBoolean(){
        Random random = new Random();
        return random.nextBoolean();
    }

    public int getTheChoice(int minValue, int maxValue){
        // handling for any out of bounds user inputs
        int choice = keyboard.nextInt();
        while(choice < minValue || choice > maxValue){
            outputs.add("Thats not an option");
            userChoice();
            choice = keyboard.nextInt();
        }
        return choice;
    }



    public void userChoice(){
        outputs.add("Which option will you choose: ");
        createBorder(outputs);
    }

    public void createBorder(List<String> strings){
        String border = "_";
        String borderToPrint = "";
        int longestString = 0;

        for (String s:strings){
            if (s.length() > longestString){
                longestString = s.length();
            }
        }
        borderToPrint = borderToPrint + border.repeat(longestString);
        output.outputString(borderToPrint);
        for (String s:strings) {
            output.outputString(s);
        }
        output.outputString(borderToPrint);
        outputs.clear();
    }


    public static void main(String[] args) {
////        Display d = new Display();
//        d.showTheAnswerOptions(d.showAQuestion());
//        System.out.println(d.correctAnswerNumber);

    }
}
