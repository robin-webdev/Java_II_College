public class Question {
    public String question;
    public String[] options;
    public int answer;

    public Question(String question, String[] options, int answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public void displayQuestion() {
        System.out.println(question);
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s %n", i + 1, options[i]);
        }
    }

    public boolean isCorrect(int userGuess) {
        return userGuess == answer + 1;
    }


}
