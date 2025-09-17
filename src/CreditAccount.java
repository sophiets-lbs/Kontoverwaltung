public class CreditAccount extends BankAccount {

    public CreditAccount(String accountHolder, String bankCode, String accountNumber, double balance){
        super(accountHolder, bankCode, accountNumber);
        setBalance(balance);
        setAccountType("Credit Account");
    }

    @Override
    public void withdraw(double amount){
        System.out.println("Cannot withdraw from Credit Account.");
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
