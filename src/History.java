import java.util.ArrayList;

public class History {
    private ArrayList<String> historyList;

    public History(){
        historyList = new ArrayList<>();
    }

    public void addAccountAddition(BankAccount account){
        historyList.add("Added new " + account.getAccountType() + " account " + account.getAccountNumber());
    }

    public void addAccountDeletion(BankAccount account){
        historyList.add("Deleted " + account.getAccountType() + " account " + account.getAccountNumber());
    }

    public void addDeposit(BankAccount account, double amount){
        historyList.add("Deposited €" + amount + " on account " + account.getAccountNumber());
    }

    public void addWithdrawal(BankAccount account, double amount){
        historyList.add("Withdrew €" + amount + " on account " + account.getAccountNumber());
    }

    public void addNewOverdraft(BankAccount account, double amount){
        historyList.add("Changed overdraft to €" + amount + " on account " + account.getAccountNumber());
    }

    public ArrayList<String> getHistoryList() {
        return historyList;
    }

    public String getListAsString(){
        StringBuilder all = new StringBuilder();
        for (String s : historyList) {
            all.append(s).append("\n");
        }
        return all.toString();
    }
}
