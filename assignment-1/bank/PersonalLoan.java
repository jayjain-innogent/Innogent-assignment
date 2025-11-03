package bank;

public class PersonalLoan implements Loan {

    @Override
    public double calculateInterest(double principal, double rate, double time) {
        return (principal * rate * time) / 100;
    }

    @Override
    public String loanType() {
        return "Personal Loan";
    }

    @Override
    public boolean isEligible(int creditScore) {
        return creditScore >= 650;
    }
}
