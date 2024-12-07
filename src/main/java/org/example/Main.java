package org.example;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static boolean exited = false; // tracks the exit state
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserManager userManager = new UserManager();
    private static final MedicineManager medicineManager = new MedicineManager();

    public static void main(String[] args) {
        while (!exited) {
            System.out.println("Welcome to Pharmacy Information System");

            // options
            System.out.println("1. Login");
            System.out.println("2. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // cleans the leftover newline by nextInt method

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    exited = true;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

        System.out.println("Goodbye and thank you.");
    }

    private static void login() {
        User authUser = null;

        while (authUser == null) {
            System.out.print("\nEnter Username: ");
            String username = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            authUser = userManager.login(username, password);
            if (authUser == null) {
                System.out.println("Invalid credentials, try again.");
            }
        }

        // when user has successfully logged in
        System.out.println("\nLogin successful! Role: " + authUser.getRole());
        switch (authUser.getRole()) {
            case "Pharmacist":
                pharmacistMenu();
                break;
            case "Assistant":
                assistantMenu();
                break;
            default:
                System.out.println("Unknown role.");
        }
    }

    private static void pharmacistMenu() {
        while (true) {
            System.out.println("1. Manage Medicines");
            System.out.println("2. Process Prescriptions");
            System.out.println("3. View Inventory");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    medicineManager.manageMedicines();
                    break;
                case 2:
                    medicineManager.processPrescription();
                    break;
                case 3:
                    medicineManager.displayInventory();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void assistantMenu() {
        while (true) {
            System.out.println("1. Process Prescriptions");
            System.out.println("2. View Inventory");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    medicineManager.processPrescription();
                    break;
                case 2:
                    medicineManager.displayInventory();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void logout() {
        System.out.println("Logged out. Thank you.");
    }

}

