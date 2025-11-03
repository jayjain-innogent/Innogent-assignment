package bank;

public class VehicleLoan implements Loan {

    @Override
    public double calculateInterest(double principal, double rate, double time) {
        return (principal * rate * time) / 100;
    }

    @Override
    public String loanType() {
        return "Vehicle Loan";
    }

    @Override
    public boolean isEligible(int creditScore) {
        return creditScore >= 600;
    }
}
