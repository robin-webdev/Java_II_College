import config.Person;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
//        task1();
//        System.out.println(factorial(6));

//        System.out.println(fibonacci(7));
//        System.out.println(sum(45));
//        quizGame();
        arrayList();
//        var choice = getUserInput("Enter your choice : ", 1, 4);
//        System.out.printf("User choice is %d", choice);
    }


    public static void arrayList() {
        ArrayList<String> names = new ArrayList<>();
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        System.out.println(time);
        System.out.println(date);

        Person p1 = new Person();
        p1.name = "Robin";
        p1.display();
    }


    public static void quizGame() {
        String[] q1Options = {"Riga", "Jurmala", "Jelgava", "Cesis"};
        Question q1 = new Question("Q. What is the capital of Latvia?", q1Options, 0);
        String[] q2Options = {"100", "50", "64", "32"};
        Question q2 = new Question("Q. How many teeth does a grown human have?", q2Options, 3);
        String[] q3Options = {"Earth", "Saturn", "Jupitar", "Mars"};
        Question q3 = new Question("Q. What is the biggest planet in our solar system?", q3Options, 2);
        String[] q4Options = {"H2", "H2O", "N2", "CO2"};
        Question q4 = new Question("Q. What represents the correct scientific term of Water?", q4Options, 1);


        Question[] questions = {q1, q2, q3, q4};
        Quiz myQuiz = new Quiz(questions);
        myQuiz.runQuiz();
    }


    static int sum(int digit) {
        String num = "" + digit;
        System.out.println(Integer.parseInt(String.valueOf(num.charAt(num.length() - 1))));
        return Integer.parseInt(String.valueOf(num.charAt(num.length() - 1))) + sum(Integer.parseInt(num.substring(0, num.length() - 2)));

    }


    static int fibonacci(int num) {
        if (num <= 1) return 1;

        return fibonacci(num - 1) + fibonacci(num - 2);


    }


    public static int factorial(int number) {
        int fact = 1;
        if (number > 1) {
            fact = factorial(number - 1) * number;
        }
        return fact;
    }

    ;

    public static int nextSafeInt(Scanner sc, String str) {
        do {
            try {
                System.out.print(str);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception exc) {
                System.out.println("Invalid Input! Integer Expected.");
            }
        } while (true);

    }

    static void task1() {


        var list = new ArrayList<Product>();
        var sc = new Scanner(System.in);
        var menuItem = 0;
        do {
            printMenu();
            menuItem = nextSafeInt(sc, "Enter your choice : ");
            switch (menuItem) {
                case 1:
                    addNew(sc, list);
                    break;
                case 2:
                    showAll(list);
                    break;
                case 3:
                    System.out.println("See you soon!");
                    break;
                case 4:
                    minMax(list);
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        } while (menuItem != 3);

    }

    private static void printMenu() {
        System.out.println("1. Add New");
        System.out.println("2. Show All");
        System.out.println("3. Quit");
        System.out.println("4. Minimum and Maximum Prices");
    }

    private static void addNew(Scanner sc, ArrayList<Product> list) {
        var newItem = new Product();
        System.out.print("Please enter name : ");
        newItem.name = sc.nextLine();
        int price = nextSafeInt(sc, "Please Enter price : ");
        if (price <= 0) {
            newItem.price = 1;
        } else {
            newItem.price = price;
        }
        list.add(newItem);

    }

    private static void showAll(ArrayList<Product> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getInfo();
        }
    }


    private static void minMax(ArrayList<Product> list) {
        Product minPrice = list.getFirst();
        Product maxPrice = list.getFirst();
        for (int i = 0; i < list.size(); i++) {
            Product item = list.get(i);

            if (item.price <= minPrice.price) {
                minPrice = item;
            }
            if (item.price >= maxPrice.price) {
                maxPrice = item;
            }
        }

        System.out.println("Maximum - " + maxPrice.name + " " + maxPrice.price);
        System.out.println("Minimum - " + minPrice.name + " " + minPrice.price);
    }


}