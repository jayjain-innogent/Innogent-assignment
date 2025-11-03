package bank;

// HomeLoan implements Loan
// OOPs: Inheritance, Polymorphism
// SOLID: OCP (can add more loan types without modifying Loan)

public class HomeLoan implements Loan {

    @Override
    public double calculateInterest(double principal, double rate, double time) {
        return (principal * rate * time) / 100;
    }

    @Override
    public String loanType() {
        return "Home Loan";
    }

    @Override
    public boolean isEligible(int creditScore) {
        return creditScore >= 700;
    }
}
