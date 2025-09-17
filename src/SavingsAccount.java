public class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountHolder, String bankCode, String accountNumber){
        super(accountHolder, bankCode, accountNumber);
        setAccountType("Savings Account");
    }

    @Override
    public void withdraw(double amount){
        double newBalance = getBalance() - amount;
        if(newBalance < 0){
            System.out.println("Withdrawal not permitted. Balance exceeded.");
        } else {
            setBalance(newBalance);
        }
    }
}
