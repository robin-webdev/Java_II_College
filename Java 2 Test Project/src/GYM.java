import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;

public class GYM {
    public String name;
    public String location;
    public int regNumber;
    public ArrayList<Member> members;

    public GYM(String name, String location, int regNumber) {
        this.name = name;
        this.location = location;
        this.regNumber = regNumber;
        this.members = new ArrayList<Member>();

        File membersData = new File("members.txt");

        try {
            Scanner reader = new Scanner(membersData);
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(",");
                members.add(new Member(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3], Integer.parseInt(parts[4])));
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveMembers() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("members.txt"));
            for (Member m : members) {
                writer.println(m.name + "," + m.age + "," + m.id + "," + m.plan + "," + m.months);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error Occurred");
            System.out.println(e.getMessage());
        }
    }

    public int safeInt(Scanner input) {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    public void printMenu() {
        System.out.println("**********************************************");
        System.out.println("Welcome to " + name);
        System.out.println("1. View All Members");
        System.out.println("2. Add Members");
        System.out.println("3. Remove Members");
        System.out.println("4. Get Members with Plan");
        System.out.println("5. Edit Members");
        System.out.println("6. Get Members Sorted by Months");
        System.out.println("7. Quit");
        System.out.println("**********************************************");
    }

    public void action() {
        Scanner input = new Scanner(System.in);
        int choice;
        while (true) {
            printMenu();
            System.out.print("What would you like to do? ");
            choice = safeInt(input);
            switch (choice) {
                case 1:
                    getAllMembers();
                    break;
                case 2:
                    addMembers();
                    break;
                case 3:
                    removeMembers();
                    break;
                case 4:
                    getMembersByPlan();
                    break;
                case 5:
                    editMembers();
                    break;
                case 6:
                    getSortedMembersByMonths();
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void editMenu() {
        System.out.println("_____________________");
        System.out.println("1. Name");
        System.out.println("2. Age");
        System.out.println("3. Plan");
        System.out.println("4. Months");
        System.out.println("5. Quit");
        System.out.println("_____________________");
        System.out.print("What would you like to edit? ");
    }

    public void editMembers() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Member ID to edit: ");
        int memberId = safeInt(input);

        Member member = getMemberById(memberId);
        int index = members.indexOf(member);
        if (member == null) return;

        int choice;
        while (true) {
            editMenu();
            choice = safeInt(input);
            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    member.setName(input.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new age: ");
                    member.setAge(safeInt(input));
                    break;
                case 3:
                    System.out.print("Enter new plan: ");
                    member.setPlan(input.nextLine());
                    break;
                case 4:
                    System.out.print("Enter new months: ");
                    member.setMonths(safeInt(input));
                    break;
                case 5:
                    members.set(index, member);
                    saveMembers();
                    System.out.println("Member updated successfully!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void getSortedMembersByMonths() {
        if (members.isEmpty()) {
            System.out.println("No members found");
            return;
        }
        ArrayList<Member> sorted = new ArrayList<Member>(members);
        sorted.sort((a, b) -> a.months - b.months);
        for (Member member : sorted) {
            System.out.println(member);
        }
    }

    public void getMembersByPlan() {
        Scanner input = new Scanner(System.in);
        System.out.println("1. Standard");
        System.out.println("2. Premium");
        System.out.println("3. Exclusive");

        System.out.print("Which plan would you like to choose? ");
        int choice = safeInt(input);
        String plan;
        switch (choice) {
            case 1:
                plan = "Standard";
                break;
            case 2:
                plan = "Premium";
                break;
            case 3:
                plan = "Exclusive";
                break;
            default:
                System.out.println("Invalid choice");
                getMembersByPlan();
                return;
        }
        boolean memberFound = false;

        for (Member member : members) {
            if (member.plan.equals(plan)) {
                memberFound = true;
                System.out.println(member);
            }
        }
        if (!memberFound) {
            System.out.println("No members found with this plan");
        }
    }

    public void getAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members found");
        }
        for (Member member : members) {
            System.out.println(member);
        }
    }

    public boolean isIdAlreadyExists(int id) {
        for (Member member : members) {
            return member.id == id;
        }
        return false;
    }

    public void addMembers() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter member name: ");
        String name = input.nextLine();
        System.out.print("Enter member age: ");
        int age = safeInt(input);

        int id;

        while (true) {
            System.out.print("Enter member Id: ");
            id = safeInt(input);
            if (!isIdAlreadyExists(id)) {
                break;
            }
            System.out.println("Member with this id already exists");
        }
        System.out.print("Enter member plan: ");
        String plan = input.nextLine();
        System.out.print("Enter member duration period(months): ");
        int months = safeInt(input);

        Member member = new Member(name, age, id, plan, months);
        members.add(member);
        saveMembers();
        System.out.println("Member added successfully!");
    }

    public void removeMembers() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter member id: ");
        int id = safeInt(input);

        Member member = getMemberById(id);

        members.remove(member);
        saveMembers();
        System.out.println("Member removed successfully!");
    }

    public Member getMemberById(int id) {
        boolean memberFound = false;
        for (Member member : members) {
            if (member.id == id) {
                return member;
            }
        }
        System.out.println("Member not found!");
        return null;
    }
}