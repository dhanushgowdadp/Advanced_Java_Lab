package Q2_Telephone;
import java.util.*;

class Caller {
    private String phoneNumber;
    private String callerName;

    public Caller(String phoneNumber, String callerName) {
        this.phoneNumber = phoneNumber;
        this.callerName = callerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCallerName() {
        return callerName;
    }

    @Override
    public String toString() {
        return "Caller: " + callerName + ", Phone Number: " + phoneNumber;
    }
}

public class TelephoneSimu {
    private Map<Integer, Caller> missedCalls;
    private int callCounter;

    public TelephoneSimu() {
        missedCalls = new HashMap<>();
        callCounter = 1;
    }

    public void recordMissedCall(String phoneNumber, String callerName) {
        if (callerName == null || callerName.isEmpty()) {
            callerName = "Private Caller";
        }

        missedCalls.put(callCounter++, new Caller(phoneNumber, callerName));
    }

    public void displayMissedCalls() {
        System.out.println("Missed Calls:");

        for (Map.Entry<Integer, Caller> entry : missedCalls.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }
    
    public void displayCallDetails(int callNumber) {
    	for (Map.Entry<Integer, Caller> entry : missedCalls.entrySet()) {
    		if(entry.getKey()==callNumber) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
    		}
        }
    }

    public void deleteMissedCall(int callNumber) {
        missedCalls.remove(callNumber);
    }

    public void displayPhoneNumbersStartingWithA() {
        System.out.println("Phone Numbers starting with 'A':");

        for (Caller caller : missedCalls.values()) {
            if (caller.getCallerName().toUpperCase().startsWith("A")) {
                System.out.println(caller.getPhoneNumber());
            }
        }
    }

    public static void main(String[] args) {
        TelephoneSimu telephone = new TelephoneSimu();

        telephone.recordMissedCall("1234567890", "Alice");
        telephone.recordMissedCall("9876543210", "Bob");
        telephone.recordMissedCall("5555555555", null);
        telephone.recordMissedCall("9998887777", "Private Caller");

        telephone.displayMissedCalls();

        Scanner sc = new Scanner(System.in);
        System.out.println("\nOptions:");
        System.out.println("1. Display Call Details");
        System.out.println("2. Delete Call");
        System.out.println("3. Display Phone Numbers starting with 'A'");
        System.out.println("4. Exit");

        int choice;
        do {
            System.out.print("\nEnter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter call number to display details: ");
                    int callNumber = sc.nextInt();
                    telephone.displayCallDetails(callNumber);
                    break;
                case 2:
                    System.out.print("Enter call number to delete: ");
                    int deleteNumber = sc.nextInt();
                    telephone.deleteMissedCall(deleteNumber);
                    System.out.println("Call deleted.");
                    break;
                case 3:
                    telephone.displayPhoneNumbersStartingWithA();
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
    }
}