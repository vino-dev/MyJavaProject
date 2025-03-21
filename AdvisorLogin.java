package insurance;

import java.util.*;
import insurance.*;

public class AdvisorLogin {
    private String name;
    private int userId;
    private String password;

    // Constructor
    public AdvisorLogin(String name, int userId, String password) {
        this.name = name;
        this.userId = userId;
        this.password = password;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Advisor [Name=" + name + ", UserID=" + userId + "]";
    }

    // Static storage for advisors
    private static LinkedHashMap<Integer, AdvisorLogin> advisors = new LinkedHashMap<>();

    static {
        // Preloaded advisor data
        advisors.put(3627333, new AdvisorLogin("Vinodhini", 3627333, "Jan@2025"));
        advisors.put(2345555, new AdvisorLogin("Mani", 2345555, "Jan@2020"));
        advisors.put(2345666, new AdvisorLogin("Arun Kumar", 2345666, "Jan@2015"));
    }

    // Advisor Portal Main Menu
    public static void advisorPortal() {
    	System.err.println("Welcome to the SRI INSURANCE company pvt ltd......");
        System.err.println("Welcome to the Advisor Login Portal!");
        
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1. Existing User Login");
                System.out.println("2. New User Registration");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = getValidIntegerInput(sc);

                switch (choice) {
                    case 1:
                        existingUser(sc);
                        break;
                    case 2:
                        newUser(sc);
                        break;
                    case 3:
                        System.out.println("Exiting Advisor Portal. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    // Existing User Login
    private static void existingUser(Scanner sc) {
        System.out.print("Enter your User ID: ");
        int userId = getValidIntegerInput(sc);
        System.out.print("Enter your Password: ");
        String password = sc.next();

        AdvisorLogin advisor = advisors.get(userId);
        if (advisor != null && advisor.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome, " + advisor.getName());
            displayMenu(advisor.getName());
        } else {
            System.out.println("Invalid User ID or Password. Please try again.");
        }
        
    }

    // New User Registration
    private static void newUser(Scanner sc) {
        System.out.print("Enter your IRDAI Number (User ID): ");
        int newId = getValidIntegerInput(sc);

        if (advisors.containsKey(newId)) {
            System.out.println("This User ID is already registered. Please log in.");
            return;
        }

        sc.nextLine(); // Clear buffer
        System.out.print("Enter your Name: ");
        String newName = sc.nextLine();
        System.out.print("Enter your Password: ");
        String newPassword = sc.nextLine();

        advisors.put(newId, new AdvisorLogin(newName, newId, newPassword));
        System.err.println("Welcome " + newName + "! You have been registered successfully.");
        System.err.println("Please log in to access your portal.");
    }

    // Display Advisor Options
    private static void displayMenu(String advisorName) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome, " + advisorName + "!");
            System.out.println("Choose an option:");
            System.out.println("1. Term Plan");
            System.out.println("2. Health Plan");
            System.out.println("3. Motor Plan");
            System.out.println("4. Non-Motor Plan");
            System.out.println("5. Payout Details");
           
            System.out.println("6. Go Back to Main Portal");

            int option = getValidIntegerInput(sc);
            sc.nextLine();
            switch (option) {
            case 1:
                System.out.println("You have selected Term Plan.");
                TermPlan t = new TermPlan();
                t.Termp(sc);
                Payment.main(null);
                break;
            case 2:
                System.out.println("You have selected Health Plan.");
                Health hh = new Health();
                hh.health(sc);
                Payment.main(null);
                break;
            case 3:
                System.out.println("You have selected Motor Plan.");
                VehicleQuotation.main(null);
                Payment.main(null);
                break;
            case 4:
                System.out.println("You have selected Non-Motor Plan.");
                InsuranceApp.main(null);
                Payment.main(null);
                break;
                case 5:
                    System.out.println("Viewing Payout Details...");
                    break;
                case 6:
                    System.out.println("Returning to Main Portal...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static int getValidIntegerInput(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            sc.next();
        }
        return sc.nextInt();
    }

    public static void main(String[] args) {
        advisorPortal();
    }
}
