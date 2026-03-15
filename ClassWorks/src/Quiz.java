import java.util.Scanner;

public class Quiz {
    public Question[] questions;
    public int score;
    public Scanner scanner;

    public Quiz(Question[] questions) {
        this.questions = questions;
        this.score = 0;
        this.scanner = new Scanner(System.in);
    }

    public void runQuiz() {
        System.out.println("=========Welcome to the Quiz==============");
        for (Question question : questions) {
            question.displayQuestion();
            int userInput = getUserInput("Enter your choice (1-4) : ", 1, 4);
            if (question.isCorrect(userInput)) {
                System.out.println("Correct Answer! (7 Crore)");
                score++;
            } else {
                System.out.println("Wrong Answer!");

                System.out.printf("Correct answer is %s and your answer was %s. %n", question.options[question.answer], question.options[userInput - 1]);
            }
        }
    }

    public int getUserInput(String message, int lb, int hb) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                int number = sc.nextInt();
                if (number >= lb && number <= hb) return number;
                else {
                    System.out.printf("Enter a value between %d nd %d. %n", lb, hb);
                }
            } else {
                System.out.println();
                System.out.println("Enter a number only!");
            }
        }
    }
}
