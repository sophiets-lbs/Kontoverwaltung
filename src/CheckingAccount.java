public class CheckingAccount extends BankAccount {

    public CheckingAccount(String accountHolder, String bankCode, String accountNumber){
        super(accountHolder, bankCode, accountNumber);
        setAccountType("Checking Account");
    }
}
