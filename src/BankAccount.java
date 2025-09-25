import javax.swing.*;

public class BankAccount {

    private final String accountHolder;
    private final String bankCode;
    private final String accountNumber;
    private double overdraftLimit;
    private final double managementFees;
    private String accountType;
    private double balance;

    public BankAccount(String accountHolder, String bankCode, String accountNumber){
        this.accountHolder = accountHolder;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.managementFees = 2.50;
    }

    public void deposit(double amount){
        balance += amount;
    }

    public boolean withdraw(double amount){
        if((balance - amount) < overdraftLimit * -1){
            JOptionPane.showMessageDialog(
                    null,
                    "Overdraft limit exceeded. Withdrawal not permitted.",
                    "Withdrawal Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }

    /*public void printAccountStatement(){
        if(accountType != null && !accountType.isEmpty()){
            System.out.println("Account Type: " + accountType);
        }
        System.out.printf("""
                        Account Number: %s
                        Bank Code: %s
                        Account Holder: %s
                        Current Balance: %.2f
                        Current Overdraft Limit: %.2f
                        Management Fees: %.2f
                        """,
                accountNumber, bankCode, accountHolder, balance, overdraftLimit, managementFees);
    }*/

    public String getAccountStatement() {
        StringBuilder sb = new StringBuilder();

        if (accountType != null && !accountType.isEmpty()) {
            sb.append("Account Type: ").append(accountType).append("\n");
        }

        sb.append(String.format("""
            Account Number: %s
            Bank Code: %s
            Account Holder: %s
            Current Balance: %.2f
            Current Overdraft Limit: %.2f
            Management Fees: %.2f
            """,
            accountNumber, bankCode, accountHolder, balance, overdraftLimit, managementFees));

        return sb.toString();
    }



    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getManagementFees() {
        return managementFees;
    }

    public String toString() {
        return accountType + " (" + accountNumber + ")";
    }
}
