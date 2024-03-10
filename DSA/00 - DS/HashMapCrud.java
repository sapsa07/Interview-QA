import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashMapCrud {
    public static void main(String[] args) {
        // Creating a HashMap with Integer keys and String values
        Map<Integer, String> hashMap = new HashMap<>();

        // Performing CRUD operations
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nHashMap CRUD Operations:");
            System.out.println("1. Display");
            System.out.println("2. Add/Edit");
            System.out.println("3. Delete");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayHashMap(hashMap);
                    break;
                case 2:
                    addOrUpdateHashMap(hashMap);
                    break;
                case 3:
                    deleteFromHashMap(hashMap);
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 4);

        scanner.close();
    }

    private static void displayHashMap(Map<Integer, String> hashMap) {
        System.out.println("HashMap Contents:");
        
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void addOrUpdateHashMap(Map<Integer, String> hashMap) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter key: ");
        int key = scanner.nextInt();

        System.out.print("Enter value: ");
        String value = scanner.next();

        hashMap.put(key, value);

        System.out.println("Data added/updated successfully.");

        scanner.close();
    }

    private static void deleteFromHashMap(Map<Integer, String> hashMap) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter key to delete: ");
        int key = scanner.nextInt();

        if (hashMap.containsKey(key)) {
            hashMap.remove(key);
            System.out.println("Data deleted successfully.");
        } else {
            System.out.println("Key not found in the HashMap.");
        }

        scanner.close();
    }
}
