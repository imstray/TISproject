import java.util.*;

public class CSVreader {
    QnAHandler QnAs = new QnAHandler();
    Map<String, List<String>> theQuestionsAndAnswers = new HashMap<>();

    public CSVreader(){
        setQnAMap();
    }

    public void setQnAMap(){
        for(String[] Qna : QnAs.getQuestionsAndAnswers()){
            if (!Objects.equals(Qna[0], "questions")){
            List<String> answers = new ArrayList<>();
            Collections.addAll(answers, Qna);
            answers.remove(0);
            theQuestionsAndAnswers.put(Qna[0], answers);
        }
        }
    }

    public Map<String, List<String>> getTheQuestionsAndAnswers() {
        return theQuestionsAndAnswers;
    }

    public static void main(String[] args) {
        QnAHandler QnAs = new QnAHandler();
        CSVreader read = new CSVreader();
        System.out.println(read.theQuestionsAndAnswers);
    }
}

