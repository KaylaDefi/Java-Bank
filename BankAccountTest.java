public class BankAccountTest {
    public static void main(String[] args) {
        System.out.println("===== Testing BankAccount Class =====\n");
        
        // Create a basic bank account
        BankAccount account1 = new BankAccount();
        account1.setFirstName("John");
        account1.setLastName("Doe");
        account1.setAccountID(12345);
        
        // Test deposits and withdrawals
        account1.deposit(1000.00);
        account1.deposit(500.00);
        account1.withdrawal(200.00);
        
        // Display account summary
        account1.accountSummary();
        
        System.out.println("\n===== Testing CheckingAccount Class =====\n");
        
        // Create a checking account
        CheckingAccount checking1 = new CheckingAccount();
        checking1.setFirstName("Jane");
        checking1.setLastName("Smith");
        checking1.setAccountID(67890);
        checking1.setInterestRate(1.5);
        
        // Test normal deposits and withdrawals
        checking1.deposit(800.00);
        checking1.deposit(200.00);
        checking1.processWithdrawal(300.00);
        
        // Display account
        checking1.displayAccount();
        
        System.out.println("===== Testing Overdraft Feature =====\n");
        
        // Test overdraft scenario
        CheckingAccount checking2 = new CheckingAccount();
        checking2.setFirstName("Bob");
        checking2.setLastName("Johnson");
        checking2.setAccountID(11111);
        checking2.setInterestRate(2.0);
        
        checking2.deposit(100.00);
        System.out.println("\nAttempting to withdraw $150 from account with $100 balance:");
        checking2.processWithdrawal(150.00);
        
        // Display final account status
        checking2.displayAccount();
        
        System.out.println("===== All Tests Complete =====");
    }
}