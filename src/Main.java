import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean menuActive = true;
        ArrayList<BankAccount> accounts = new ArrayList<>();

        String accountHolder;
        String accountNumber;
        String bankCode;
        BankAccount selectedAccount;

        System.out.println("Welcome to Big Bird Banking, your personalised banking assistant.");
        System.out.println("Connect your accounts, and we’ll take care of the rest.");

        while(menuActive){
            System.out.println("----------");
            System.out.println("Choose an action:");
            System.out.println("1 - Register new account");
            System.out.println("2 - Deposit");
            System.out.println("3 - Withdraw");
            System.out.println("4 - Print account statement");
            System.out.println("5 - Close account");
            System.out.println("6 - Set overdraft limit");
            System.out.println("0 - Exit");

            int input = scanner.nextInt();

            switch(input){
                case 1:
                    System.out.println("What kind of account do you wish to connect?");

                    System.out.println("1 - Checking Account");
                    System.out.println("2 - Savings Account");
                    System.out.println("3 - Credit Account");

                    int accountType = scanner.nextInt();

                    scanner.nextLine(); //consume leftover line

                    System.out.println("Please enter your data.");
                    System.out.print("Name:");
                    accountHolder = scanner.nextLine();
                    System.out.print("Bank Code:");
                    bankCode = scanner.nextLine();
                    System.out.print("Account Number:");
                    accountNumber = scanner.nextLine();

                    switch(accountType){
                        case 1:
                            CheckingAccount checkingAccount = new CheckingAccount(accountHolder, bankCode, accountNumber);
                            System.out.println("Do you wish to deposit an initial balance? y/n");
                            if(scanner.next().equalsIgnoreCase("y")){
                                System.out.println("How much would you like to deposit?");
                                checkingAccount.setBalance(scanner.nextDouble());
                            }
                            accounts.add(checkingAccount);
                            System.out.println("Checking account linked successfully!");
                            break;
                        case 2:
                            SavingsAccount savingsAccount = new SavingsAccount(accountHolder, bankCode, accountNumber);
                            System.out.println("Do you wish to deposit an initial balance? y/n");
                            if(scanner.next().equalsIgnoreCase("y")){
                                System.out.println("How much would you like to deposit?");
                                savingsAccount.setBalance(scanner.nextDouble());
                            }
                            accounts.add(savingsAccount);
                            System.out.println("Savings account linked successfully!");
                            break;
                        case 3:
                            System.out.println("How much do you owe?");
                            double initialCredit = scanner.nextDouble();
                            if(initialCredit > 0) initialCredit *= -1;
                            CreditAccount creditAccount = new CreditAccount(accountHolder, bankCode, accountNumber, initialCredit);
                            accounts.add(creditAccount);
                            System.out.println("Credit account linked successfully");
                    }
                    break;
                case 2:
                    selectedAccount = selectAccount(accounts);
                    if(selectedAccount != null) {
                        System.out.println("How much do you want to deposit?");
                        selectedAccount.deposit(scanner.nextDouble());
                        System.out.printf("Current balance: %.2f\n", selectedAccount.getBalance());
                    }
                    break;
                case 3:
                    selectedAccount = selectAccount(accounts);
                    if(selectedAccount != null) {
                        System.out.println("How much do you want to withdraw?");
                        selectedAccount.withdraw(scanner.nextDouble());
                        System.out.printf("Current balance: %.2f\n", selectedAccount.getBalance());
                    }
                    break;
                case 4:
                    selectedAccount = selectAccount(accounts);
                    if(selectedAccount != null) selectedAccount.printAccountStatement();
                    break;
                case 5:
                    selectedAccount = selectAccount(accounts);
                    if(selectedAccount != null){
                        System.out.println("Are you sure you want to close this account? y/n");
                        if(scanner.next().equalsIgnoreCase("y")){
                            accounts.remove(selectedAccount);
                            System.out.println("Account successfully closed.");
                        } else {
                            System.out.println("Action aborted.");
                        }
                    }
                    break;
                case 6:
                    selectedAccount = selectAccount(accounts);
                    if(selectedAccount != null){
                        if(selectedAccount.getAccountType().equals("Checking Account")){
                            System.out.println("Please enter how much overdraft you want to allow:");
                            selectedAccount.setOverdraftLimit(scanner.nextDouble());
                            System.out.println("New overdraft limit set: " + selectedAccount.getOverdraftLimit());
                        } else {
                            System.out.println("This type of account doesn't support overdraft.");
                        }
                    }
                    break;
                default:
                    System.out.println("Thank you for using Big Bird Bank.");
                    menuActive = false;
            }
        }

    }

    public static BankAccount selectAccount(ArrayList<BankAccount> accounts){
        Scanner scanner = new Scanner(System.in);
        BankAccount selectedAccount = null;
        if(accounts.size() > 1){
            System.out.println("Choose an account.");
            for(int i = 0; i < accounts.size(); i++){
                System.out.println((i+1) + ": " + accounts.get(i).getAccountNumber());
            }
            System.out.println("Enter number:");
            int index = scanner.nextInt() - 1;

            if(accounts.get(index) != null){
                selectedAccount = accounts.get(index);
            } else {
                System.out.println("Account not found.");
            }
        } else if(accounts.isEmpty()){
            System.out.println("No accounts registered. Please link an account.");
        } else {
            selectedAccount = accounts.getFirst();
        }
        return selectedAccount;
    }
}
