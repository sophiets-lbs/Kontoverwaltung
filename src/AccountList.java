import java.util.ArrayList;

public class AccountList {
    private ArrayList<BankAccount> accountList;

    public AccountList(){
        accountList = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        accountList.add(account);
    }

    public BankAccount getAccount(int index) {
        return accountList.get(index);
    }

    public int getNumberOfAccounts() {
        return accountList.size();
    }

    public ArrayList<BankAccount> getAllAccounts() {
        return accountList;
    }

    public void deleteAccount(BankAccount account){
        accountList.remove(account);
    }

    public BankAccount getLastAccount(){
        return accountList.getLast();
    }
}
