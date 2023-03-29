

import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.text.NumberFormat
import javax.swing.*

class BankingApplication(private val users: List<User>) : JFrame() {
    private val userComboBox = JComboBox(users.toTypedArray())
    private val userPanel = UserPanel(users.first())
    private val openAccountButton = JButton("Open Account")
    private val depositButton = JButton("Deposit")
    private val creditCardButton = JButton("Credit Card")
    private val transferButton = JButton("Transfer")
    private val closeButton = JButton("Close Credit Card")

    init {
        title = "Banking App"
        defaultCloseOperation = EXIT_ON_CLOSE
        layout = GridBagLayout()

        add(userComboBox, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 0
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })

        add(userPanel, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 1
            gridy = 0
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })

        add(openAccountButton, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 1
        })

        add(depositButton, GridBagConstraints().apply {
            insets = Insets(10, 0, 0, 10)
            gridx = 1
            gridy = 1
        })

        add(creditCardButton, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 2
        })

        add(transferButton, GridBagConstraints().apply {
            insets = Insets(10, 0, 0, 10)
            gridx = 1
            gridy = 2
        })

        add(closeButton, GridBagConstraints().apply {
            insets = Insets(10, 10, 10, 10)
            gridx = 0
            gridy = 3
            gridwidth = 2
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })

        pack()
        setLocationRelativeTo(null)
        isVisible = true

        userComboBox.addActionListener {
            val selectedUser = userComboBox.selectedItem as User
            userPanel.setUser(selectedUser)
        }

        openAccountButton.addActionListener {
            val selectedUser = userComboBox.selectedItem as User
            selectedUser.addBankAccount(0.0)
            userPanel.setUser(selectedUser)
        }

        depositButton.addActionListener {
            val selectedUser = userComboBox.selectedItem as User
            val account = selectBankAccount(selectedUser)
            if (account != null) {
                val depositForm = DepositForm1()
                val result = JOptionPane.showConfirmDialog(
                    this,
                    depositForm,
                    "Deposit",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
                )
                if (result == JOptionPane.OK_OPTION) {
                    val amount = depositForm.getAmount()
                    account.deposit(amount)
                    userPanel.setUser(selectedUser)
                    JOptionPane.showMessageDialog(
                        this,
                        "Deposit successful.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                    )
                }
            }
        }

        creditCardButton.addActionListener {
            val selectedUser = userComboBox.selectedItem as User
            val account = selectBankAccount(selectedUser)
            if (account != null) {
                val creditCardForm = CreditCardForm1()
                val result = JOptionPane.showConfirmDialog(
                    this,
                    creditCardForm,
                    "Credit Card",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
                )
                if (result == JOptionPane.OK_OPTION) {
                    val limit = creditCardForm.getLimit()
                    val interestRate = creditCardForm.getInterestRate()
                    selectedUser.addCreditCard(account, limit, interestRate)
                    userPanel.setUser(selectedUser)
                    JOptionPane.showMessageDialog(
                        this,
                        "Credit card created successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                    )
                }
            }
        }
        transferButton.addActionListener {
            val selectedUser = userComboBox.selectedItem as User
            val sourceAccount = selectBankAccount(selectedUser)
            if (sourceAccount != null) {
                val targetUser = selectUser(selectedUser)
                if (targetUser != null) {
                    val targetAccount = selectBankAccount(targetUser)
                    if (targetAccount != null) {
                        val transferForm = TransferForm1()
                        val result = JOptionPane.showConfirmDialog(
                            this,
                            transferForm,
                            "Transfer",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                        )
                        if (result == JOptionPane.OK_OPTION) {
                            val amount = transferForm.getAmount()
                            if (sourceAccount.withdraw(amount)) {
                                targetAccount.deposit(amount)
                                userPanel.setUser(selectedUser)
                                JOptionPane.showMessageDialog(
                                    this,
                                    "Transfer successful.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE
                                )
                            } else {
                                JOptionPane.showMessageDialog(
                                    this,
                                    "Insufficient funds.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                                )
                            }
                        }
                    }
                }
            }
        }

        closeButton.addActionListener {
            val selectedUser = userComboBox.selectedItem as User
            val creditCard = selectCreditCard(selectedUser)
            if (creditCard != null) {
                selectedUser.closeCreditCard(creditCard)
                userPanel.setUser(selectedUser)
                JOptionPane.showMessageDialog(
                    this,
                    "Credit card closed successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                )
            }
        }
    }

    private fun selectUser(currentUser: User): User? {
        val usersExceptCurrent = users.filter { it != currentUser }.toTypedArray()
        return JOptionPane.showInputDialog(
            this,
            "Select user:",
            "Transfer",
            JOptionPane.PLAIN_MESSAGE,
            null,
            usersExceptCurrent,
            null
        ) as User?
    }

    private fun selectBankAccount(user: User): BankAccount? {
        val accounts = user.getBankAccounts().toTypedArray()
        return if (accounts.isNotEmpty()) {
            JOptionPane.showInputDialog(
                this,
                "Select account:",
                "Account",
                JOptionPane.PLAIN_MESSAGE,
                null,
                accounts,
                null
            ) as BankAccount?
        } else {
            JOptionPane.showMessageDialog(
                this,
                "No bank accounts found.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
            null
        }
    }

    private fun selectCreditCard(user: User): CreditCard? {
        val creditCards = user.getCreditCards().toTypedArray()
        return if (creditCards.isNotEmpty()) {
            JOptionPane.showInputDialog(
                this,
                "Select credit card:",
                "Credit Card",
                JOptionPane.PLAIN_MESSAGE,
                null,
                creditCards,
                null
            ) as CreditCard?
        } else {
            JOptionPane.showMessageDialog(
                this,
                "No credit cards found.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            )
            null
        }
    }
}


    class NewAccountForm : JDialog() {

    private val nameLabel = JLabel("Name:")
    private val nameField = JTextField()
    private val balanceLabel = JLabel("Initial Balance:")
    private val balanceField = JTextField()
    private val createButton = JButton("Create")
    private val cancelButton = JButton("Cancel")

    init {
        title = "New Account"
        layout = GridBagLayout()
        isModal = true

        add(nameLabel, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 0
            anchor = GridBagConstraints.LINE_END
        })

        add(nameField, GridBagConstraints().apply {
            insets = Insets(10, 0, 0, 10)
            gridx = 1
            gridy = 0
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })

        add(balanceLabel, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 1
            anchor = GridBagConstraints.LINE_END
        })

        add(balanceField, GridBagConstraints().apply {
            insets = Insets(10, 0, 0, 10)
            gridx = 1
            gridy = 1
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })

        add(createButton, GridBagConstraints().apply {
            insets = Insets(10, 10, 10, 10)
            gridx = 0
            gridy = 2
        })

        add(cancelButton, GridBagConstraints().apply {
            insets = Insets(10, 0, 10, 10)
            gridx = 1
            gridy = 2
            anchor = GridBagConstraints.LINE_END
        })

        pack()
        setLocationRelativeTo(null)

        createButton.addActionListener {
            val name = nameField.text
            val balance = balanceField.text.toDoubleOrNull()

            if (name.isNotEmpty() && balance != null) {
                // Create new account with name and balance
                JOptionPane.showMessageDialog(this, "Account created successfully.")
                dispose()
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields with valid values.")
            }
        }

        cancelButton.addActionListener {
            dispose()
        }
    }
}

class DepositForm1 : JDialog() {
    private val accountNumberLabel = JLabel("Account Number:")
    private val accountNumberField = JTextField()
    private val amountLabel = JLabel("Amount:")
    private val amountField = JTextField()
    private val depositButton = JButton("Deposit")
    private val cancelButton = JButton("Cancel")

    init {
        title = "Deposit"
        layout = GridBagLayout()
        isModal = true

        add(accountNumberLabel, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 0
            anchor = GridBagConstraints.LINE_END
        })

        add(accountNumberField, GridBagConstraints().apply {
            insets = Insets(10, 0, 0, 10)
            gridx = 1
            gridy = 0
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })

        add(amountLabel, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 1
            anchor = GridBagConstraints.LINE_END
        })

        add(amountField, GridBagConstraints().apply {
            insets = Insets(10, 0, 0, 10)
            gridx = 1
            gridy = 1
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })

        add(depositButton, GridBagConstraints().apply {
            insets = Insets(10, 10, 10, 10)
            gridx = 0
            gridy = 2
        })

        add(cancelButton, GridBagConstraints().apply {
            insets = Insets(10, 0, 10, 10)
            gridx = 1
            gridy = 2
            anchor = GridBagConstraints.LINE_END
        })

        pack()
        setLocationRelativeTo(null)

        depositButton.addActionListener {
            val accountNumber = accountNumberField.text
            val amount = amountField.text.toDoubleOrNull()

            if (accountNumber.isNotEmpty() && amount != null) {
                // Deposit amount to account with accountNumber
                JOptionPane.showMessageDialog(this, "Deposit successful.")
                dispose()
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields with valid values.")
            }
        }

        cancelButton.addActionListener {
            dispose()
        }
    }

    fun getAmount() : Double {
        return amountField.text.toDouble()
    }
}

class CreditCardForm1 : JDialog() {
    private val accountNumberLabel = JLabel("Account Number:")
    private val accountNumberField = JTextField()
    private val creditLimitLabel = JLabel("Credit Limit:")
    private val creditLimitField = JTextField()
    private val createButton = JButton("Create")
    private val cancelButton = JButton("Cancel")

    init {
        title = "Create Credit Card"
        layout = GridBagLayout()
        isModal = true

        add(accountNumberLabel, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 0
            anchor = GridBagConstraints.LINE_END
        })

        add(accountNumberField, GridBagConstraints().apply {
            insets = Insets(10, 0, 0, 10)
            gridx = 1
            gridy = 0
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })

        add(creditLimitLabel, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 1
            anchor = GridBagConstraints.LINE_END
        })

        add(creditLimitField, GridBagConstraints().apply {
            insets = Insets(10, 0, 0, 10)
            gridx = 1
            gridy = 1
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })

        add(createButton, GridBagConstraints().apply {
            insets = Insets(10, 10, 10, 10)
            gridx = 0
            gridy = 2
        })

        add(cancelButton, GridBagConstraints().apply {
            insets = Insets(10, 0, 10, 10)
            gridx = 1
            gridy = 2
            anchor = GridBagConstraints.LINE_END
        })

        pack()
        setLocationRelativeTo(null)

        createButton.addActionListener {
            val accountNumber = accountNumberField.text
            val creditLimit = creditLimitField.text.toDoubleOrNull()

            if (accountNumber.isNotEmpty() && creditLimit != null) {
                // Create credit card for account with accountNumber and credit limit
                JOptionPane.showMessageDialog(this, "Credit card created successfully.")
                dispose()
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields with valid values.")
            }
        }

        cancelButton.addActionListener {
            dispose()
        }
    }

    fun getLimit () : Double {
        return creditLimitField.text.toDouble()
    }

    fun getInterestRate() : Double {
        return 0.0
    }
}

class TransferForm1 : JDialog() {
    private val fromAccountLabel = JLabel("From Account:")
    private val fromAccountField = JTextField()
    private val toAccountLabel = JLabel("To Account:")
    private val toAccountField = JTextField()
    private val amountLabel = JLabel("Amount:")
    private val amountField = JTextField()
    private val transferButton = JButton("Transfer")
    private val cancelButton = JButton("Cancel")

    init {
        title = "Transfer"
        layout = GridBagLayout()
        isModal = true

        add(fromAccountLabel, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 0
            anchor = GridBagConstraints.LINE_END
        })

        add(fromAccountField, GridBagConstraints().apply {
            insets = Insets(10, 0, 0, 10)
            gridx = 1
            gridy = 0
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })

        add(toAccountLabel, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 1
            anchor = GridBagConstraints.LINE_END
        })

        add(toAccountField, GridBagConstraints().apply {
            insets = Insets(10, 0, 0, 10)
            gridx = 1
            gridy = 1
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })
        add(amountLabel, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 2
            anchor = GridBagConstraints.LINE_END
        })

        add(amountField, GridBagConstraints().apply {
            insets = Insets(10, 0, 0, 10)
            gridx = 1
            gridy = 2
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })

        add(transferButton, GridBagConstraints().apply {
            insets = Insets(10, 10, 10, 10)
            gridx = 0
            gridy = 3
        })

        add(cancelButton, GridBagConstraints().apply {
            insets = Insets(10, 0, 10, 10)
            gridx = 1
            gridy = 3
            anchor = GridBagConstraints.LINE_END
        })

        pack()
        setLocationRelativeTo(null)

        transferButton.addActionListener {
            val fromAccount = fromAccountField.text
            val toAccount = toAccountField.text
            val amount = amountField.text.toDoubleOrNull()

            if (fromAccount.isNotEmpty() && toAccount.isNotEmpty() && amount != null) {
                // Transfer amount from account with fromAccount to account with toAccount
                JOptionPane.showMessageDialog(this, "Transfer successful.")
                dispose()
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields with valid values.")
            }
        }

        cancelButton.addActionListener {
            dispose()
        }
    }

    fun getAmount() : Double {
        return amountField.text.toDouble()
    }
}

class CreditCardClosingForm : JDialog() {
    private val accountNumberLabel = JLabel("Account Number:")
    private val accountNumberField = JTextField()
    private val closeButton = JButton("Close")
    private val cancelButton = JButton("Cancel")

    init {
        title = "Close Credit Card"
        layout = GridBagLayout()
        isModal = true

        add(accountNumberLabel, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 0
            anchor = GridBagConstraints.LINE_END
        })

        add(accountNumberField, GridBagConstraints().apply {
            insets = Insets(10, 0, 0, 10)
            gridx = 1
            gridy = 0
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })

        add(closeButton, GridBagConstraints().apply {
            insets = Insets(10, 10, 10, 10)
            gridx = 0
            gridy = 1
        })

        add(cancelButton, GridBagConstraints().apply {
            insets = Insets(10, 0, 10, 10)
            gridx = 1
            gridy = 1
            anchor = GridBagConstraints.LINE_END
        })

        pack()
        setLocationRelativeTo(null)

        closeButton.addActionListener {
            val accountNumber = accountNumberField.text

            if (accountNumber.isNotEmpty()) {
                // Close credit card for account with accountNumber
                JOptionPane.showMessageDialog(this, "Credit card closed successfully.")
                dispose()
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields with valid values.")
            }
        }

        cancelButton.addActionListener {
            dispose()
        }
    }
}

class User(private val name: String, private val password: String) {
    private val bankAccounts = mutableListOf<BankAccount>()
    private val creditCards = mutableListOf<CreditCard>()

    fun getName(): String {
        return name
    }

    fun getPassword(): String {
        return password
    }

    fun addBankAccount(balance: Double): BankAccount {
        val account = BankAccount(balance)
        bankAccounts.add(account)
        return account
    }

    fun addCreditCard(account: BankAccount, limit: Double, interestRate: Double): CreditCard {
        val creditCard = CreditCard(account, limit, interestRate)
        creditCards.add(creditCard)
        return creditCard
    }

    fun closeCreditCard(creditCard: CreditCard) {
        creditCards.remove(creditCard)
    }

    fun getBankAccounts(): List<BankAccount> {
        return bankAccounts
    }

    fun getCreditCards(): List<CreditCard> {
        return creditCards
    }

    override fun toString(): String {
        return name
    }
}

class BankAccount(initialBalance: Double) {
    private var balance = initialBalance

    fun deposit(amount: Double) {
        balance += amount
    }

    fun withdraw(amount: Double): Boolean {
        if (balance >= amount) {
            balance -= amount
            return true
        }
        return false
    }

    fun getBalance(): Double {
        return balance
    }

    override fun toString(): String {
        return String.format("%.2f", balance)
    }
}

class CreditCard(private val account: BankAccount, private val limit: Double, private val interestRate: Double) {
    private var balance = 0.0

    fun getAccount(): BankAccount {
        return account
    }

    fun getLimit(): Double {
        return limit
    }

    fun getInterestRate(): Double {
        return interestRate
    }

    fun charge(amount: Double): Boolean {
        val availableCredit = limit - balance
        if (availableCredit >= amount) {
            balance += amount
            return true
        }
        return false
    }

    fun pay(amount: Double): Boolean {
        if (balance >= amount) {
            balance -= amount
            return true
        }
        return false
    }

    fun getBalance(): Double {
        return balance
    }

    override fun toString(): String {
        return String.format("%.2f", balance)
    }
}

class BankAccountForm(private val bankAccount: BankAccount, private val user: User) : JPanel() {
    private val nameLabel = JLabel("Account balance:")
    private val balanceLabel = JLabel(getFormattedBalance(bankAccount.getBalance()))
    private val amountField = JTextField(10)
    private val transferButton = JButton("Transfer")
    private val closeButton = JButton("Close Account")

    init {
        val layout = GroupLayout(this)
        setLayout(layout)

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameLabel)
            )
            .addGap(10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(balanceLabel)
            )
            .addContainerGap()
        )

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel)
                .addComponent(balanceLabel)
            )
            .addGap(10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(amountField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(transferButton)
                .addComponent(closeButton)
            )
            .addContainerGap()
        )

        transferButton.addActionListener {
            val amount = getAmount(amountField.text)
            if (amount != null) {
                val transferForm = TransferForm1()
                transferForm.isVisible = true
            } else {
                JOptionPane.showMessageDialog(null, "Invalid amount")
            }
        }

        closeButton.addActionListener {
            JOptionPane.showMessageDialog(null, "Account closed")
        }
    }

    private fun getFormattedBalance(balance: Double): String {
        val format = NumberFormat.getCurrencyInstance()
        return format.format(balance)
    }

    private fun getAmount(text: String): Double? {
        return try {
            text.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }
}

class UserPanel(private var user: User) : JPanel() {
    private val nameLabel = JLabel("Name: ${user.getName()}")
    private val balanceLabel = JLabel("Balance: ${getFormattedBalance(getTotalBalance(user))}")

    init {
        val layout = GroupLayout(this)
        setLayout(layout)

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameLabel)
                .addComponent(balanceLabel)
            )
            .addContainerGap()
        )

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(nameLabel)
            .addGap(10)
            .addComponent(balanceLabel)
            .addContainerGap()
        )
    }

    fun setUser(newUser: User){
        user = newUser
    }

    private fun getFormattedBalance(balance: Double): String {
        val format = NumberFormat.getCurrencyInstance()
        return format.format(balance)
    }

    private fun getTotalBalance(user: User): Double {
        var totalBalance = 0.0
        for (account in user.getBankAccounts()) {
            totalBalance += account.getBalance()
        }
        return totalBalance
    }
}

fun main (args: Array<String>){
    val user = User(
        "leder",
        "123"
    )

    BankingApplication(mutableListOf(user))
}



