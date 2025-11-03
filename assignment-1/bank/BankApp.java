package bank;

// OOPs: Object creation, method invocation, polymorphism

public class BankApp {
    public static void main(String[] args) {
        SavingAccount savingAcc1 = new SavingAccount("SA1001", 1000, "Amit");
        CurrentAccount currentAcc1 = new CurrentAccount("CA1001", 2000, "Badal");

        savingAcc1.deposit(500);
        System.out.println(savingAcc1.name + " - " + savingAcc1.accountType() +
                " Balance: Rs." + savingAcc1.getBalance());
        System.out.println("Interest Earned: Rs." + savingAcc1.calculateInterest());

        // Loan Examples
        Loan homeLoan = new HomeLoan();
        System.out.println("\nLoan Type: " + homeLoan.loanType());
        System.out.println("Interest (₹100000 @ 8% for 2 years): ₹" +
                homeLoan.calculateInterest(100000, 8, 2));
        System.out.println("Eligible? " + homeLoan.isEligible(720));
    }
}
