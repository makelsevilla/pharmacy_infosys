package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class MedicineManager {
    private static final Scanner scanner = new Scanner(System.in);
    private final List<Medicine> medicines = new ArrayList<>();
    private int medicineIdCounter = 1;

    public MedicineManager() {
        medicines.add(new Medicine(1, "Paracetamol", "Painkiller", 1.99, 50, new Date(2025 - 1900, 5, 1), "ABC Pharma"));
        medicines.add(new Medicine(2, "Aspirin", "Painkiller", 2.49, 5, new Date(2024 - 1900, 2, 15), "XYZ Pharma"));
        medicines.add(new Medicine(3, "Amoxicillin", "Antibiotic", 12.99, 20, new Date(2024 - 1900, 10, 30), "MediCare"));
        medicines.add(new Medicine(4, "Cetirizine", "Antihistamine", 4.99, 100, new Date(2026 - 1900, 1, 20), "HealthCorp"));
        medicines.add(new Medicine(5, "Ibuprofen", "Painkiller", 3.49, 8, new Date(2024 - 1900, 7, 15), "Wellness Inc."));
    }

    public void manageMedicines() {
        while (true) {
            System.out.println("\nMedicine Management:");
            System.out.println("1. Add Medicine");
            System.out.println("2. Edit Medicine");
            System.out.println("3. Delete Medicine");
            System.out.println("4. Search Medicine");
            System.out.println("5. Check Expired Medicines");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    addMedicine();
                    break;
                case 2:
                    editMedicine();
                    break;
                case 3:
                    deleteMedicine();
                    break;
                case 4:
                    searchMedicine();
                    break;
                case 5:
                    checkExpiredMedicines();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addMedicine() {
        System.out.print("Enter medicine name: ");
        String name = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter stock quantity: ");
        int stockQuantity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter expiry date (yyyy-mm-dd): ");
        String dateInput = scanner.nextLine();
        String[] date = dateInput.split("-");
        Date expiryDate = new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])).getTime();

        System.out.print("Enter manufacturer: ");
        String manufacturer = scanner.nextLine();

        medicines.add(new Medicine(medicineIdCounter++, name, category, price, stockQuantity, expiryDate, manufacturer));
        System.out.println("Medicine added successfully.");
    }

    private void editMedicine() {
        System.out.print("Enter medicine ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Medicine medicine = findMedicineById(id);
        if (medicine == null) {
            System.out.println("Medicine not found.");
            return;
        }

        System.out.print("Enter new name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            // Update name logic
        }
        // Add other fields similarly
        System.out.println("Medicine updated successfully.");
    }

    private void deleteMedicine() {
        System.out.print("Enter medicine ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        medicines.removeIf(medicine -> medicine.getId() == id);
        System.out.println("Medicine deleted successfully.");
    }

    private void searchMedicine() {
        System.out.print("Enter medicine name to search: ");
        String name = scanner.nextLine();

        List<Medicine> results = medicines.stream()
                .filter(medicine -> medicine.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No medicines found.");
        } else {
            results.forEach(Medicine::display);
        }
    }

    private void checkExpiredMedicines() {
        List<Medicine> expired = medicines.stream()
                .filter(Medicine::isExpired)
                .collect(Collectors.toList());

        if (expired.isEmpty()) {
            System.out.println("No expired medicines.");
        } else {
            System.out.println("Expired medicines:");
            expired.forEach(Medicine::display);
        }
    }

    public void processPrescription() {
        // prescription processing logic
    }

    public void displayInventory() {
        if (medicines.isEmpty()) {
            System.out.println("No medicines in inventory.");
        } else {
            System.out.println("Current inventory:");
            medicines.forEach(Medicine::display);
        }
        System.out.println();
    }

    private Medicine findMedicineById(int id) {
        return medicines.stream().filter(medicine -> medicine.getId() == id).findFirst().orElse(null);
    }
}

