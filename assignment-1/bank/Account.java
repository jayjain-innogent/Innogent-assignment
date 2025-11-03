package bank;

// Abstract Account
// OOPs: Abstraction, Encapsulation
// SOLID: SRP (manages only account operations), ISP (separated from Loan)

public abstract class Account {
    protected String accountNumber; // unique identifier
    protected double balance;        // protected account balance
    protected String name;           // account holder name

    public Account(String accountNumber, double balance, String name) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.name = name;
    }

    public abstract String accountType();
    public abstract boolean withdraw(double amount);
    public abstract boolean deposit(double amount);
    public abstract double getBalance();
}
