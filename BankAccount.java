public class BankAccount {
    // Fields
    private String firstName;
    private String lastName;
    private int accountID;
    private double balance;
    
    // Constructor - initialize balance to zero
    public BankAccount() {
        this.balance = 0.0;
    }
    
    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + String.format("%.2f", amount));
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }
    
    // Withdrawal method
    public void withdrawal(double amount) {
        if (amount > 0) {
            balance -= amount;
            System.out.println("Withdrew: $" + String.format("%.2f", amount));
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }
    
    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    
    // Getters
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public int getAccountID() {
        return accountID;
    }
    
    public double getBalance() {
        return balance;
    }
    
    // Account Summary method
    public void accountSummary() {
        System.out.println("\n=== Account Summary ===");
        System.out.println("Account ID: " + accountID);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Balance: $" + String.format("%.2f", balance));
        System.out.println("=======================\n");
    }
}