import javax.swing.*;

public class CreditAccount extends BankAccount {

    public CreditAccount(String accountHolder, String bankCode, String accountNumber){
        super(accountHolder, bankCode, accountNumber);
        setAccountType("Credit Account");
    }

    @Override
    public boolean withdraw(double amount){
        JOptionPane.showMessageDialog(
                null,
                "Cannot withdraw from Credit Account.",
                "Withdrawal Error",
                JOptionPane.ERROR_MESSAGE
        );
        return false;
    }

    @Override
    public void deposit(double amount) {
        double balance = getBalance();
        double remainingDebt = balance + amount;

        if(remainingDebt >= 0) {
            setBalance(0);
            System.out.println("Credit fully repaid.");
            if(amount > balance){
                System.out.println("Excess returned.");
            }
        } else {
            setBalance(remainingDebt);
            System.out.println("Deposit applied.");
        }
    }
}
