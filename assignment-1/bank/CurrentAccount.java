package bank;

// CurrentAccount implements Account
// OOPs: Inheritance, Polymorphism
// SOLID: OCP (extended from Account), LSP (can substitute Account)

public class CurrentAccount extends Account {

    public CurrentAccount(String accountNumber, double balance, String name) {
        super(accountNumber, balance, name);
    }

    @Override
    public String accountType() {
        return "Current Account";
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= (balance + overdraftLimit())) {
            balance -= amount;
            return true;
        }
        throw new IllegalArgumentException("Insufficient balance (even with overdraft)");
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

    // Dynamic overdraft limit based on balance
    public double overdraftLimit() {
        return 2.5 * balance;
    }
}
