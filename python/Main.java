import java.util.Scanner;

class User {
    private String username;
    private String passcode;
    private double balance;
    private boolean loggedIn;

    public User(String username, String passcode, double balance) {
        this.username = username;
        this.passcode = passcode;
        this.balance = balance;
        this.loggedIn = false;
    }

    public void login() {
        if (this.loggedIn) {
            System.out.println("Already logged in as " + this.username);
            return;
        }
        String username = prompt("Username: ");
        String passcode = prompt("Passcode: ");
        if (username.equals(this.username) && passcode.equals(this.passcode)) {
            this.loggedIn = true;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    public void logout() {
        if (this.loggedIn) {
            this.loggedIn = false;
            System.out.println("Logged out.");
        } else {
            System.out.println("Not logged in.");
        }
    }

    public void viewBalance() {
        if (!this.loggedIn) {
            System.out.println("You must log in first.");
            return;
        }
        System.out.printf("Current balance: $%.2f%n", this.balance);
    }

    public void withdraw() {
        if (!this.loggedIn) {
            System.out.println("You must log in first.");
            return;
        }
        this.viewBalance();
        String amountStr = prompt("Amount to withdraw: ");
        try {
            double amount = Double.parseDouble(amountStr.trim());
            if (amount > 0.0) {
                if (amount <= this.balance) {
                    this.balance -= amount;
                    System.out.printf("Withdrew $%.2f. New balance: $%.2f%n", amount, this.balance);
                } else {
                    System.out.println("Insufficient funds.");
                }
            } else {
                System.out.println("Invalid amount.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
        }
    }

    public void changePasscode() {
        if (!this.loggedIn) {
            System.out.println("You must log in first.");
            return;
        }
        String current = prompt("Current passcode: ");
        if (!current.equals(this.passcode)) {
            System.out.println("Current passcode incorrect.");
            return;
        }
        String newPass = prompt("New passcode: ");
        String confirm = prompt("Confirm new passcode: ");
        if (!newPass.equals(confirm)) {
            System.out.println("Passcodes do not match.");
            return;
        }
        if (newPass.isEmpty()) {
            System.out.println("Passcode cannot be empty.");
            return;
        }
        this.passcode = newPass;
        System.out.println("Passcode changed successfully.");
    }
}

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static String prompt(String msg) {
        System.out.print(msg);
        System.out.flush();
        return scanner.nextLine().trim();
    }

    public static void main(String[] args) {
        User user = new User("user", "1234", 1000.0);

        while (true) {
            System.out.println();
            if (user.loggedIn) {
                System.out.println("Logged in as: " + user.username);
                System.out.println("1) View balance");
                System.out.println("2) Withdraw");
                System.out.println("3) Change passcode");
                System.out.println("4) Logout");
                System.out.println("5) Exit");
                String choice = prompt("Choose: ");
                switch (choice) {
                    case "1":
                        user.viewBalance();
                        break;
                    case "2":
                        user.withdraw();
                        break;
                    case "3":
                        user.changePasscode();
                        break;
                    case "4":
                        user.logout();
                        break;
                    case "5":
                        System.out.println("Goodbye.");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("1) Login");
                System.out.println("2) Exit");
                String choice = prompt("Choose: ");
                switch (choice) {
                    case "1":
                        user.login();
                        break;
                    case "2":
                        System.out.println("Goodbye.");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }
}