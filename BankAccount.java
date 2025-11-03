/**
 * Improved BankAccount class with enhanced design patterns
 * Key improvements:
 * - Parameterized constructors for easier object creation
 * - Input validation to prevent invalid states
 * - Exception handling instead of silent failures
 * - Immutable accountID for security
 * - Better encapsulation with validation in setters
 */
public class BankAccount {
    // Fields - accountID is final (immutable after creation)
    private String firstName;
    private String lastName;
    private final int accountID;
    private double balance;
    
    // Constants
    protected static final double MIN_BALANCE = 0.0;
    
    // Default Constructor - kept for backward compatibility
    public BankAccount(int accountID) {
        this("", "", accountID);
    }
    
    // Parameterized Constructor - preferred way to create accounts
    public BankAccount(String firstName, String lastName, int accountID) {
        if (accountID <= 0) {
            throw new IllegalArgumentException("Account ID must be positive");
        }
        this.accountID = accountID;
        this.balance = 0.0;
        setFirstName(firstName);
        setLastName(lastName);
    }
    
    // Full Constructor - initialize with starting balance
    public BankAccount(String firstName, String lastName, int accountID, double initialBalance) {
        this(firstName, lastName, accountID);
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
    }
    
    /**
     * Deposit money into the account
     * @param amount the amount to deposit
     * @throws IllegalArgumentException if amount is not positive
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive. Provided: " + amount);
        }
        balance += amount;
        System.out.println("Deposited: $" + String.format("%.2f", amount));
    }
    
    /**
     * Withdraw money from the account
     * Prevents overdraft in base account (CheckingAccount can override)
     * @param amount the amount to withdraw
     * @throws IllegalArgumentException if amount is not positive
     * @throws InsufficientFundsException if balance is insufficient
     */
    public void withdrawal(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive. Provided: " + amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(
                String.format("Insufficient funds. Attempted: $%.2f, Available: $%.2f", amount, balance)
            );
        }
        balance -= amount;
        System.out.println("Withdrew: $" + String.format("%.2f", amount));
    }
    
    /**
     * Internal withdrawal method for subclasses to allow overdraft
     * Protected so only subclasses can use it
     */
    protected void forceWithdrawal(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        balance -= amount;
        System.out.println("Withdrew: $" + String.format("%.2f", amount));
    }
    
    // Setters with validation
    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("First name cannot be null");
        }
        this.firstName = firstName.trim();
    }
    
    public void setLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("Last name cannot be null");
        }
        this.lastName = lastName.trim();
    }
    
    // Getters
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public int getAccountID() {
        return accountID;
    }
    
    public double getBalance() {
        return balance;
    }
    
    /**
     * Check if account has sufficient funds
     */
    public boolean hasSufficientFunds(double amount) {
        return balance >= amount;
    }
    
    /**
     * Display account summary
     */
    public void accountSummary() {
        System.out.println(formatAccountSummary());
    }
    
    /**
     * Format account information as a string
     * Useful for display and logging
     */
    protected String formatAccountSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== Account Summary ===\n");
        sb.append("Account ID: ").append(accountID).append("\n");
        sb.append("Name: ").append(getFullName()).append("\n");
        sb.append("Balance: $").append(String.format("%.2f", balance)).append("\n");
        sb.append("=======================\n");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return String.format("BankAccount[ID=%d, Name=%s, Balance=$%.2f]", 
                           accountID, getFullName(), balance);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BankAccount other = (BankAccount) obj;
        return accountID == other.accountID;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(accountID);
    }
}

/**
 * Custom exception for insufficient funds
 */
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}