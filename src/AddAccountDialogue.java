import javax.swing.*;

public class AddAccountDialogue {
    private JTextField nameTextField;
    private JTextField accountNumberTextField;
    private JRadioButton checkingAccountRadioButton;
    private JRadioButton savingsAccountRadioButton;
    private JRadioButton creditAccountRadioButton;
    private JLabel nameLabel;
    private JLabel accountNumberLabel;
    private JLabel accountTypeLabel;
    private JPanel accountTypeRadios;
    private JFormattedTextField initialDepositTextField;
    private JCheckBox initialDepositCheckBox;
    private JButton addAccountButton;
    private JFormattedTextField creditAmountTextField;
    private JLabel creditAmountLabel;
    private JPanel addAccountPanel;
    private JTextField bankCodeTextField;
    private JButton cancelButton;
    private JSeparator separator1;
    private JSeparator separator2;
    private JLabel addAccountHeader;
    private boolean inputValid = true;

    public JPanel getMainPanel(){
        return addAccountPanel;
    }

    public AddAccountDialogue(AccountList accountList){

        addAccountPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        addAccountHeader.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        initialDepositTextField.setEnabled(false);
        initialDepositCheckBox.addActionListener(e -> {
            initialDepositTextField.setEnabled(initialDepositCheckBox.isSelected());
        });

        ButtonGroup accountTypeGroup = new ButtonGroup();
        JRadioButton[] radioButtons = {checkingAccountRadioButton, savingsAccountRadioButton, creditAccountRadioButton};
        accountTypeGroup.add(checkingAccountRadioButton);
        accountTypeGroup.add(savingsAccountRadioButton);
        accountTypeGroup.add(creditAccountRadioButton);

        for(JRadioButton button : radioButtons){
            button.addActionListener(e -> {
                boolean isDeposit = checkingAccountRadioButton.isSelected() || savingsAccountRadioButton.isSelected();
                initialDepositCheckBox.setVisible(isDeposit);
                initialDepositTextField.setVisible(isDeposit);

                boolean isCredit = creditAccountRadioButton.isSelected();
                creditAmountTextField.setVisible(isCredit);
                creditAmountLabel.setVisible(isCredit);
            });
        }
        
        cancelButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(addAccountPanel).dispose();
        });

        addAccountButton.addActionListener(e -> {
            String name = nameTextField.getText();
            String bankCode = bankCodeTextField.getText();
            String accountNumber = accountNumberTextField.getText();

            if(name.isEmpty() || bankCode.isEmpty() || accountNumber.isEmpty()){
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter all required data!",
                        "Data Missing",
                        JOptionPane.ERROR_MESSAGE
                );
            } else if(!checkingAccountRadioButton.isSelected() && !savingsAccountRadioButton.isSelected() && !creditAccountRadioButton.isSelected()){
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter all required data!",
                        "Data Missing",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                BankAccount newAccount;
                if(checkingAccountRadioButton.isSelected()){
                    newAccount = new CheckingAccount(name, bankCode, accountNumber);
                    if(initialDepositCheckBox.isSelected() && getInput(initialDepositTextField) != -1){
                        newAccount.setBalance(getInput(initialDepositTextField));
                    }
                } else if(savingsAccountRadioButton.isSelected()){
                    newAccount = new SavingsAccount(name, bankCode, accountNumber);
                    if(initialDepositCheckBox.isSelected() && getInput(initialDepositTextField) != -1){
                        newAccount.setBalance(getInput(initialDepositTextField));
                    }
                } else {
                    newAccount = new CreditAccount(name, bankCode, accountNumber);
                    if(getInput(creditAmountTextField) != -1){
                        newAccount.setBalance(getInput(creditAmountTextField));
                    }
                }
                if (inputValid) {
                    accountList.addAccount(newAccount);
                    SwingUtilities.getWindowAncestor(addAccountPanel).dispose();
                }
            }
        });
    }

    public double getInput(JTextField textField){
        String input = textField.getText();

        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    addAccountPanel,
                    "Please enter a valid amount.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
            inputValid = false;
            return -1;
        }
        try {
            inputValid = true;
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    addAccountPanel,
                    "Input must be a valid number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
            inputValid = false;
            return -1;
        }
    }
}
