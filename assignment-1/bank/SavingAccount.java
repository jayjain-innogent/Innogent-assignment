package bank;

// SavingAccount implements Account
// OOPs: Inheritance, Polymorphism
// SOLID: OCP (extended from Account), LSP (can substitute Account)

public class SavingAccount extends Account {

    public SavingAccount(String accountNumber, double balance, String name) {
        super(accountNumber, balance, name);
    }

    @Override
    public String accountType() {
        return "Saving Account";
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        throw new IllegalArgumentException("Insufficient balance");
    }

    @Override
    public boolean deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        return true;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    // Method to return interest rate
    public double interestRate() {
        return 0.025; // 2.5% interest
    }

    // Method to calculate interest earned
    public double calculateInterest() {
        return balance * interestRate();
    }
}
