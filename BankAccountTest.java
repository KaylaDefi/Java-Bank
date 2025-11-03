/**
 * Test class for improved BankAccount and CheckingAccount
 * Updated to use new constructors and exception handling
 */
public class BankAccountTest {
    public static void main(String[] args) {
        System.out.println("===== Testing BankAccount Class =====\n");
        
        // Create a basic bank account using improved constructor
        BankAccount account1 = new BankAccount("John", "Doe", 12345);
        
        // Test deposits and withdrawals with exception handling
        account1.deposit(1000.00);
        account1.deposit(500.00);
        
        try {
            account1.withdrawal(200.00);
        } catch (InsufficientFundsException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        // Display account summary
        account1.accountSummary();
        
        System.out.println("\n===== Testing CheckingAccount Class =====\n");
        
        // Create a checking account using improved constructor
        CheckingAccount checking1 = new CheckingAccount("Jane", "Smith", 67890, 1.5);
        
        // Test normal deposits and withdrawals
        checking1.deposit(800.00);
        checking1.deposit(200.00);
        checking1.processWithdrawal(300.00);
        
        // Display account
        checking1.displayAccount();
        
        System.out.println("===== Testing Overdraft Feature =====\n");
        
        // Test overdraft scenario
        CheckingAccount checking2 = new CheckingAccount("Bob", "Johnson", 11111, 2.0);
        
        checking2.deposit(100.00);
        System.out.println("\nAttempting to withdraw $150 from account with $100 balance:");
        checking2.processWithdrawal(150.00);
        
        // Display final account status
        checking2.displayAccount();
        
        System.out.println("===== Testing New Features =====\n");
        
        // Test interest calculation
        CheckingAccount checking3 = new CheckingAccount("Alice", "Williams", 99999, 5000.0, 3.5);
        System.out.println("Created account: " + checking3);
        System.out.println("Annual interest would be: $" + String.format("%.2f", checking3.calculateInterest()));
        
        System.out.println("\nApplying interest...");
        checking3.applyInterest();
        checking3.displayAccount();
        
        System.out.println("===== Testing Error Handling =====\n");
        
        // Test insufficient funds protection in base account
        BankAccount account2 = new BankAccount("Test", "User", 55555, 50.0);
        System.out.println("Created account with $50.00 balance");
        System.out.println("Attempting to withdraw $100.00...");
        
        try {
            account2.withdrawal(100.00);
        } catch (InsufficientFundsException e) {
            System.out.println("✓ Correctly prevented: " + e.getMessage());
        }
        
        System.out.println("\n===== All Tests Complete =====");
    }
}