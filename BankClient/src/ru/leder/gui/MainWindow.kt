package ru.leder.gui

import java.awt.BorderLayout
import java.awt.GridLayout
import javax.swing.*

class MainWindow : JFrame() {
    /**
     * Create a JFrame
     * **/
    fun step1(){
        val frame = JFrame("Banking Application")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(600, 400)
        frame.isVisible = true
    }

    /**
     * Create the Login Panel
     * **/
    fun step2() {
        val frame = JFrame("Banking Application")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(600, 400)

        val loginPanel = JPanel()
        loginPanel.layout = GridLayout(3, 2)

        val usernameLabel = JLabel("Username:")
        val passwordLabel = JLabel("Password:")
        val usernameTextField = JTextField()
        val passwordTextField = JPasswordField()
        val loginButton = JButton("Login")
        val signUpButton = JButton("Sign Up")

        loginPanel.add(usernameLabel)
        loginPanel.add(usernameTextField)
        loginPanel.add(passwordLabel)
        loginPanel.add(passwordTextField)
        loginPanel.add(loginButton)
        loginPanel.add(signUpButton)

        frame.add(loginPanel)
        frame.isVisible = true
    }

    /**
     * Create the User Panel
     * **/
    fun step3 () {
        val frame = JFrame("Banking Application")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(600, 400)

        val userPanel = JPanel()
        userPanel.layout = BorderLayout()

        val welcomeLabel = JLabel("Welcome, [username]")
        val accountList = JList(arrayOf("Savings", "Checking", "Credit Card"))
        val viewDetailsButton = JButton("View Details")
        val transactionHistoryButton = JButton("Transaction History")

        userPanel.add(welcomeLabel, BorderLayout.NORTH)
        userPanel.add(accountList, BorderLayout.CENTER)

        val buttonPanel = JPanel()
        buttonPanel.add(viewDetailsButton)
        buttonPanel.add(transactionHistoryButton)
        userPanel.add(buttonPanel, BorderLayout.SOUTH)

        frame.add(userPanel)
        frame.isVisible = true
    }

    /**
     * Create the Transfer Funds Panel
     * **/
    fun step4 () {
        val frame = JFrame("Banking Application")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(600, 400)

        val transferPanel = JPanel()
        transferPanel.layout = GridLayout(4, 2)

        val fromLabel = JLabel("From:")
        val fromComboBox = JComboBox(arrayOf("Checking Account", "Savings Account", "Credit Card"))
        val toLabel = JLabel("To:")
        val toComboBox = JComboBox(arrayOf("Checking Account", "Savings Account", "Credit Card"))
        val amountLabel = JLabel("Amount:")
        val amountTextField = JTextField()
        val transferButton = JButton("Transfer")

        transferPanel.add(fromLabel)
        transferPanel.add(fromComboBox)
        transferPanel.add(toLabel)
        transferPanel.add(toComboBox)
        transferPanel.add(amountLabel)
        transferPanel.add(amountTextField)
        transferPanel.add(transferButton)

        frame.add(transferPanel)
        frame.isVisible = true
    }

    /**
     * Create the Open Account Panel
     * **/
    fun step5 () {
        val frame = JFrame("Banking Application")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(600, 400)

        val openAccountPanel = JPanel()
        openAccountPanel.layout = GridLayout(3, 2)

        val accountTypeLabel = JLabel("Account Type:")
        val accountTypeComboBox = JComboBox(arrayOf("Checking Account", "Savings Account", "Credit Card"))
        val initialDepositLabel = JLabel("Initial Deposit:")
        val initialDepositTextField = JTextField()
        val openAccountButton = JButton("Open Account")

        openAccountPanel.add(accountTypeLabel)
        openAccountPanel.add(accountTypeComboBox)
        openAccountPanel.add(initialDepositLabel)
        openAccountPanel.add(initialDepositTextField)
        openAccountPanel.add(openAccountButton)

        frame.add(openAccountPanel)
        frame.isVisible = true
    }
}

fun main (args: Array<String>) {
    val mw = MainWindow()
    mw.step1()
    mw.step2()
    mw.step3()
    mw.step4()
    mw.step5()
}