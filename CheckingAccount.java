/**
 * Improved CheckingAccount class with enhanced design
 * Key improvements:
 * - Better constructor chaining
 * - Cleaner overdraft logic
 * - Extracted constants
 * - Interest calculation features
 * - Better method naming and documentation
 */
public class CheckingAccount extends BankAccount {
    // Additional field for interest rate
    private double interestRate;
    
    // Constants
    private static final double DEFAULT_INTEREST_RATE = 0.0;
    private static final double OVERDRAFT_FEE = 30.0;
    private static final double MAX_INTEREST_RATE = 100.0;
    
    // Constructor with accountID only
    public CheckingAccount(int accountID) {
        super(accountID);
        this.interestRate = DEFAULT_INTEREST_RATE;
    }
    
    // Constructor with basic info
    public CheckingAccount(String firstName, String lastName, int accountID) {
        super(firstName, lastName, accountID);
        this.interestRate = DEFAULT_INTEREST_RATE;
    }
    
    // Full Constructor with interest rate
    public CheckingAccount(String firstName, String lastName, int accountID, double interestRate) {
        super(firstName, lastName, accountID);
        setInterestRate(interestRate);
    }
    
    // Constructor with initial balance and interest rate
    public CheckingAccount(String firstName, String lastName, int accountID, 
                          double initialBalance, double interestRate) {
        super(firstName, lastName, accountID, initialBalance);
        setInterestRate(interestRate);
    }
    
    /**
     * Set interest rate with validation
     * @param interestRate the annual interest rate (e.g., 1.5 for 1.5%)
     */
    public void setInterestRate(double interestRate) {
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
        if (interestRate > MAX_INTEREST_RATE) {
            throw new IllegalArgumentException("Interest rate cannot exceed " + MAX_INTEREST_RATE + "%");
        }
        this.interestRate = interestRate;
    }
    
    public double getInterestRate() {
        return interestRate;
    }
    
    /**
     * Calculate interest earned based on current balance
     * @return the interest amount
     */
    public double calculateInterest() {
        return getBalance() * (interestRate / 100.0);
    }
    
    /**
     * Apply interest to the account
     * Adds calculated interest to the balance
     */
    public void applyInterest() {
        double interest = calculateInterest();
        if (interest > 0) {
            super.deposit(interest);
            System.out.println("Interest applied: $" + String.format("%.2f", interest));
        }
    }
    
    /**
     * Process withdrawal with overdraft protection
     * Allows overdraft but charges a fee
     * @param amount the amount to withdraw
     */
    public void processWithdrawal(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        double currentBalance = getBalance();
        
        // Check if withdrawal will cause overdraft
        if (amount > currentBalance) {
            handleOverdraft(amount, currentBalance);
        } else {
            // Normal withdrawal without overdraft
            try {
                withdrawal(amount);
            } catch (InsufficientFundsException e) {
                // This shouldn't happen since we checked the balance
                System.err.println("Unexpected error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Handle overdraft scenario
     * Processes withdrawal and applies fee
     */
    private void handleOverdraft(double amount, double currentBalance) {
        System.out.println("⚠️  OVERDRAFT WARNING: Withdrawal exceeds balance");
        System.out.println("    Requested: $" + String.format("%.2f", amount));
        System.out.println("    Available: $" + String.format("%.2f", currentBalance));
        System.out.println("    Overdraft: $" + String.format("%.2f", amount - currentBalance));
        
        // Use protected method to allow overdraft
        forceWithdrawal(amount);
        forceWithdrawal(OVERDRAFT_FEE);
        
        System.out.println("*** OVERDRAFT FEE of $" + String.format("%.2f", OVERDRAFT_FEE) + " has been assessed ***");
        System.out.println("    New Balance: $" + String.format("%.2f", getBalance()));
    }
    
    /**
     * Check if account is overdrawn
     */
    public boolean isOverdrawn() {
        return getBalance() < 0;
    }
    
    /**
     * Get the overdraft amount (0 if not overdrawn)
     */
    public double getOverdraftAmount() {
        return isOverdrawn() ? Math.abs(getBalance()) : 0.0;
    }
    
    /**
     * Display comprehensive account information
     */
    public void displayAccount() {
        System.out.println(formatAccountDetails());
    }
    
    /**
     * Format checking account details as a string
     */
    private String formatAccountDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== Checking Account Summary ===\n");
        sb.append("Account ID: ").append(getAccountID()).append("\n");
        sb.append("Name: ").append(getFullName()).append("\n");
        sb.append("Balance: $").append(String.format("%.2f", getBalance()));
        
        if (isOverdrawn()) {
            sb.append(" ⚠️ OVERDRAWN");
        }
        sb.append("\n");
        
        sb.append("Interest Rate: ").append(String.format("%.2f", interestRate)).append("%\n");
        
        if (getBalance() > 0) {
            sb.append("Potential Interest: $").append(String.format("%.2f", calculateInterest())).append("\n");
        }
        
        sb.append("================================\n");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return String.format("CheckingAccount[ID=%d, Name=%s, Balance=$%.2f, Rate=%.2f%%]", 
                           getAccountID(), getFullName(), getBalance(), interestRate);
    }
}