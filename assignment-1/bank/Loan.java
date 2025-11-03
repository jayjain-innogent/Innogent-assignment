package bank;

// Loan Interface
// OOPs: Abstraction, SOLID: ISP, SRP
// - Abstraction: defines "what" a loan should do, not "how"
// - SRP: class only deals with loan rules
// - ISP: separates loan responsibilities from accounts

public interface Loan {
    double calculateInterest(double principal, double rate, double time);
    String loanType();
    boolean isEligible(int creditScore);
}
