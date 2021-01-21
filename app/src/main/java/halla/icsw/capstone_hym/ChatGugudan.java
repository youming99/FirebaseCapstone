package halla.icsw.capstone_hym;

public class ChatGugudan {

    private String question;
    private String answer;

    public ChatGugudan() {}
    public ChatGugudan(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public void setQuestion(String userName) {
        this.question = userName;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
