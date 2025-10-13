public class CheckingAccount extends BankAccount {
    // Additional field for interest rate
    private double interestRate;
    private static final double OVERDRAFT_FEE = 30.0;
    
    // Constructor
    public CheckingAccount() {
        super();
        this.interestRate = 0.0;
    }
    
    // Setter for interest rate
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    
    // Getter for interest rate
    public double getInterestRate() {
        return interestRate;
    }
    
    // Process Withdrawal method - allows overdraft with $30 fee
    public void processWithdrawal(double amount) {
        if (amount > 0) {
            double currentBalance = getBalance();
            
            // Check if withdrawal will cause overdraft
            if (amount > currentBalance) {
                // Allow overdraft but charge fee
                withdrawal(amount);
                withdrawal(OVERDRAFT_FEE);
                System.out.println("*** OVERDRAFT FEE of $" + String.format("%.2f", OVERDRAFT_FEE) + " has been assessed ***");
                System.out.println("New Balance: $" + String.format("%.2f", getBalance()));
            } else {
                // Normal withdrawal
                withdrawal(amount);
            }
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }
    
    // Display Account method - shows all information including interest rate
    public void displayAccount() {
        System.out.println("\n=== Checking Account Summary ===");
        System.out.println("Account ID: " + getAccountID());
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Balance: $" + String.format("%.2f", getBalance()));
        System.out.println("Interest Rate: " + String.format("%.2f", interestRate) + "%");
        System.out.println("================================\n");
    }
}