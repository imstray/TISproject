import java.util.List;

public class QnAHandler {
    final String filepath = "QnA's.csv";

    public QnAHandler(){
        getQuestionsAndAnswers();
    }

    public List<String[]> getQuestionsAndAnswers() {
        ReadDelimitedFile read = new ReadDelimitedFile();
        return read.getFileData(filepath);
    }
}
