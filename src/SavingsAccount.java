import javax.swing.*;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountHolder, String bankCode, String accountNumber){
        super(accountHolder, bankCode, accountNumber);
        setAccountType("Savings Account");
    }

    @Override
    public boolean withdraw(double amount){
        double newBalance = getBalance() - amount;
        if(newBalance < 0){
            JOptionPane.showMessageDialog(
                    null,
                    "Withdrawal from savings account not permitted.",
                    "Withdrawal Error",
                    JOptionPane.ERROR_MESSAGE);
            return false; // indicate failure
        } else {
            setBalance(newBalance);
            return true; // indicate success
        }
    }

}
