import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Menu {
    private JButton addAccountButton;
    private JButton depositButton;
    private JComboBox selectAccountBox;
    private JButton withdrawButton;
    private JTextArea historyTextField;
    private JButton setOverdraftLimitButton;
    private JTextPane accountStatementTextPane;
    private JPanel menuPanel;
    private JPanel buttonPanel;
    private JPanel depositPanel;
    private JTextField actionTextField;
    private JButton deleteAccountButton;
    private JLabel accountStatementLabel;
    private JLabel headerLabel;
    private BankAccount selectedAccount;
    public History history;

    public Menu(){
        AccountList accountList = new AccountList();
        history = new History();
        historyTextField.setText(history.getListAsString());

        updateAccountComboBox(accountList);
        setSelectedAccount();
        setAccountStatementTextPane();

        addAccountButton.addActionListener(e -> {
            JDialog addAccountDialog = new JDialog((JFrame) null, "Add Account", true);
            AddAccountDialogue addAccountDialogue = new AddAccountDialogue(accountList);
            addAccountDialog.setContentPane(addAccountDialogue.getMainPanel());
            addAccountDialog.setSize(700, 350);
            addAccountDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            addAccountDialog.setVisible(true);
            updateAccountComboBox(accountList);
            if (accountList.getNumberOfAccounts() > 0) {
                history.addAccountAddition(accountList.getLastAccount());
                refreshHistory();
            }
        });

        selectAccountBox.addActionListener(e -> {
            Object selected = selectAccountBox.getSelectedItem();
            if (selected instanceof BankAccount) {
                selectedAccount = (BankAccount) selected;
                setAccountStatementTextPane();
            }
        });

        depositButton.addActionListener(e -> {
            double amount = getActionFieldInput();
            if(amount == -1) return;
            selectedAccount.deposit(amount);
            setAccountStatementTextPane();
            history.addDeposit(selectedAccount, amount);
            refreshHistory();
        });

        withdrawButton.addActionListener(e -> {
            double amount = getActionFieldInput();
            if(amount == -1) return;
            selectedAccount.withdraw(amount);

            boolean success = selectedAccount.withdraw(amount);
            if(!success) return;

            setAccountStatementTextPane();
            history.addWithdrawal(selectedAccount, amount);
            refreshHistory();
        });

        setOverdraftLimitButton.addActionListener(e -> {
            double amount = getActionFieldInput();
            if(amount == -1) return;
            selectedAccount.setOverdraftLimit(amount);
            setAccountStatementTextPane();
            history.addNewOverdraft(selectedAccount, amount);
            refreshHistory();
        });

        deleteAccountButton.addActionListener(e -> {
            accountList.deleteAccount(selectedAccount);
            updateAccountComboBox(accountList);
            setAccountStatementTextPane();
            setSelectedAccount();
            history.addAccountDeletion(selectedAccount);
            refreshHistory();
        });

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        accountStatementLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 25, 0));
        accountStatementTextPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        historyTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        accountStatementTextPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Big Bird Banking");
        frame.setContentPane(new Menu().menuPanel);
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setSelectedAccount(){
        Object selected = selectAccountBox.getSelectedItem();
        if(selected instanceof BankAccount){
            selectedAccount = (BankAccount) selected;
            accountStatementTextPane.setText(selectedAccount.getAccountStatement());
        }
    }

    public void setAccountStatementTextPane() {
        if(selectedAccount != null){
            accountStatementTextPane.setText(selectedAccount.getAccountStatement());
        } else {
            accountStatementTextPane.setText("No account selected.");
        }
    }

    public void updateAccountComboBox(AccountList accountList) {
        selectAccountBox.removeAllItems();

        if (accountList.getNumberOfAccounts() > 0) {
            for (BankAccount account : accountList.getAllAccounts()) {
                selectAccountBox.addItem(account);
            }
            selectAccountBox.setEnabled(true);
        } else {
            selectAccountBox.addItem("No accounts linked");
            selectAccountBox.setEnabled(false);
        }
        handleButtons(accountList.getNumberOfAccounts() > 0);
        setSelectedAccount();
        setAccountStatementTextPane();
    }

    public void handleButtons(boolean accountExists){
        if(accountExists){
            depositButton.setEnabled(true);
            setOverdraftLimitButton.setEnabled(true);
            withdrawButton.setEnabled(true);
            deleteAccountButton.setVisible(true);
            //deleteAccountButton.setEnabled(true);
            depositPanel.setVisible(true);
        } else {
            depositButton.setEnabled(false);
            setOverdraftLimitButton.setEnabled(false);
            withdrawButton.setEnabled(false);
           // deleteAccountButton.setEnabled(false);
            deleteAccountButton.setVisible(false);
            depositPanel.setVisible(false);
        }
    }

    public double getActionFieldInput(){
        String input = actionTextField.getText();

        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    menuPanel,
                    "Please enter a valid amount.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return -1;
        }
        try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    menuPanel,
                    "Input must be a valid number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return -1;
        }
    }

    public void refreshHistory() {
        String list = history.getListAsString();
        historyTextField.setText(list);
    }
}
