package ru.leder.gui

import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.GridLayout
import java.awt.Insets
import javax.swing.*

class BankingApp : JFrame() {

    private val loginButton = JButton("Login")
    private val newAccountButton = JButton("Open new account")
    private val depositButton = JButton("Deposit funds")
    private val transferButton = JButton("Transfer funds")
    private val closeCreditCardButton = JButton("Close credit card")

    init {
        title = "Banking Application"
        layout = GridLayout(5, 1)

        add(loginButton)
        add(newAccountButton)
        add(depositButton)
        add(transferButton)
        add(closeCreditCardButton)

        loginButton.addActionListener {
            val loginDialog = LoginDialog()
            // Perform authentication here
        }

        newAccountButton.addActionListener {
            val accountForm = AccountForm()
            // Create new account here
        }

        depositButton.addActionListener {
            val depositForm = DepositForm()
            // Deposit funds here
        }

        transferButton.addActionListener {
            val transferForm = TransferForm()
            // Transfer funds here
        }

        closeCreditCardButton.addActionListener {
            val creditCardForm = CreditCardForm()
            // Close credit card here
        }

        defaultCloseOperation = EXIT_ON_CLOSE
        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }
}

class LoginDialog : JDialog() {
    private val usernameField = JTextField()
    private val passwordField = JPasswordField()
    private val loginButton = JButton("Login")
    private val cancelButton = JButton("Cancel")

    init {
        title = "Login"
        layout = GridLayout(3, 2)

        add(JLabel("Username:"))
        add(usernameField)
        add(JLabel("Password:"))
        add(passwordField)
        add(cancelButton)
        add(loginButton)

        loginButton.addActionListener {
            val username = usernameField.text
            val password = String(passwordField.password)
            // Perform authentication here
            dispose()
        }

        cancelButton.addActionListener { dispose() }

        defaultCloseOperation = DISPOSE_ON_CLOSE
        isModal = true
        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }
}

class AccountForm : JDialog() {
    private val firstNameField = JTextField()
    private val lastNameField = JTextField()
    private val addressField = JTextField()
    private val accountTypeComboBox = JComboBox(arrayOf("Checking", "Savings"))
    private val initialDepositField = JTextField()
    private val createButton = JButton("Create")
    private val cancelButton = JButton("Cancel")

    init {
        title = "New Account"
        layout = GridLayout(6, 2)

        add(JLabel("First Name:"))
        add(firstNameField)
        add(JLabel("Last Name:"))
        add(lastNameField)
        add(JLabel("Address:"))
        add(addressField)
        add(JLabel("Account Type:"))
        add(accountTypeComboBox)
        add(JLabel("Initial Deposit:"))
        add(initialDepositField)
        add(cancelButton)
        add(createButton)

        createButton.addActionListener {
            val firstName = firstNameField.text
            val lastName = lastNameField.text
            val address = addressField.text
            val accountType = accountTypeComboBox.selectedItem.toString()
            val initialDeposit = initialDepositField.text.toDouble()
            // Create new account here
            dispose()
        }

        cancelButton.addActionListener { dispose() }

        defaultCloseOperation = DISPOSE_ON_CLOSE
        isModal = true
        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }
}

class DepositForm : JDialog() {
    private val accountNumberField = JTextField()
    private val amountField = JTextField()
    private val depositButton = JButton("Deposit")
    private val cancelButton = JButton("Cancel")

    init {
        title = "Deposit Funds"
        layout = GridLayout(3, 2)

        add(JLabel("Account Number:"))
        add(accountNumberField)
        add(JLabel("Amount:"))
        add(amountField)
        add(cancelButton)
        add(depositButton)

        depositButton.addActionListener {
            val accountNumber = accountNumberField.text.toInt()
            val amount = amountField.text.toDouble()
            // Deposit funds here
            dispose()
        }

        cancelButton.addActionListener { dispose() }

        defaultCloseOperation = DISPOSE_ON_CLOSE
        isModal = true
        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }
}

class TransferForm : JDialog() {

    private val fromAccountLabel = JLabel("From Account:")
    private val toAccountLabel = JLabel("To Account:")
    private val amountLabel = JLabel("Amount:")
    private val fromAccountField = JTextField()
    private val toAccountField = JTextField()
    private val amountField = JTextField()
    private val transferButton = JButton("Transfer")
    private val cancelButton = JButton("Cancel")

    init {
        title = "Transfer Funds"
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
            insets = Insets(20, 10, 10, 10)
            gridx = 0
            gridy = 3
            fill
            gridwidth = 2
        })

        add(cancelButton, GridBagConstraints().apply {
            insets = Insets(10, 10, 10, 10)
            gridx = 0
            gridy = 4
            fill = GridBagConstraints.HORIZONTAL
            gridwidth = 2
        })

        transferButton.addActionListener {
            val fromAccount = fromAccountField.text
            val toAccount = toAccountField.text
            val amount = amountField.text.toDoubleOrNull()

            if (fromAccount.isEmpty() || toAccount.isEmpty() || amount == null) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE)
                return@addActionListener
            }

            // Perform transfer here
            JOptionPane.showMessageDialog(this, "Transfer complete", "Success", JOptionPane.INFORMATION_MESSAGE)
            dispose()
        }

        cancelButton.addActionListener {
            dispose()
        }

        defaultCloseOperation = DISPOSE_ON_CLOSE
        isModal = true
        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }

    fun getAmount() : Int {
        return amountField.text.toInt()
    }
}

class CreditCardForm : JDialog() {
    private val accountLabel = JLabel("Account:")
    private val accountField = JTextField()
    private val closeAccountButton = JButton("Close Account")
    private val cancelButton = JButton("Cancel")

    init {
        title = "Close Credit Card"
        layout = GridBagLayout()
        isModal = true

        add(accountLabel, GridBagConstraints().apply {
            insets = Insets(10, 10, 0, 10)
            gridx = 0
            gridy = 0
            anchor = GridBagConstraints.LINE_END
        })

        add(accountField, GridBagConstraints().apply {
            insets = Insets(10, 0, 0, 10)
            gridx = 1
            gridy = 0
            fill = GridBagConstraints.HORIZONTAL
            weightx = 1.0
        })

        add(closeAccountButton, GridBagConstraints().apply {
            insets = Insets(20, 10, 10, 10)
            gridx = 0
            gridy = 1
            fill = GridBagConstraints.HORIZONTAL
            gridwidth = 2
        })

        add(cancelButton, GridBagConstraints().apply {
            insets = Insets(10, 10, 10, 10)
            gridx = 0
            gridy = 2
            fill = GridBagConstraints.HORIZONTAL
            gridwidth = 2
        })

        closeAccountButton.addActionListener {
            val account = accountField.text

            if (account.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an account number", "Error", JOptionPane.ERROR_MESSAGE)
                return@addActionListener
            }

            // Close account here
            JOptionPane.showMessageDialog(this, "Account closed", "Success", JOptionPane.INFORMATION_MESSAGE)
            dispose()
        }

        cancelButton.addActionListener {
            dispose()
        }

        defaultCloseOperation = DISPOSE_ON_CLOSE
        isModal = true
        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }
}

fun main (args: Array<String>)
{
    BankingApp()
}
