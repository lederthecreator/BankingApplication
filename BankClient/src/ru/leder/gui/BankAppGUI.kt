package ru.leder.gui

import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

// Create the main GUI class that extends JFrame
class BankAppGUI : JFrame(), ActionListener {

    // Define UI components
    private val accountList: JComboBox<String> = JComboBox<String>()
    private val accountType: JComboBox<String> = JComboBox<String>()
    private val amountField: JTextField = JTextField()
    private val recipientField: JTextField = JTextField()
    private val transferButton: JButton = JButton("Transfer Funds")
    private val closeButton: JButton = JButton("Close Account")

    // Define the main function that initializes the GUI
    fun init() {
        // Set window properties
        title = "Banking Application"
        setSize(400, 400)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        // Add UI components to the window
        val panel = JPanel()
        panel.layout = GridLayout(5, 2)

        panel.add(JLabel("Select Account:"))
        panel.add(accountList)

        panel.add(JLabel("Account Type:"))
        panel.add(accountType)

        panel.add(JLabel("Amount:"))
        panel.add(amountField)

        panel.add(JLabel("Recipient Account:"))
        panel.add(recipientField)

        panel.add(transferButton)
        panel.add(closeButton)

        add(panel)
        isVisible = true

        // Set action listeners for UI components
        transferButton.addActionListener(this)
        closeButton.addActionListener(this)

        // Initialize account list and type options
        accountList.addItem("Account 1")
        accountList.addItem("Account 2")
        accountType.addItem("Savings")
        accountType.addItem("Checking")
    }

    // Define action performed function
    override fun actionPerformed(e: ActionEvent?) {
        if (e?.source === transferButton) {
            transferFunds()
        } else if (e?.source === closeButton) {
            closeAccount()
        }
    }

    // Define function to transfer funds
    private fun transferFunds() {
        val account = accountList.selectedItem.toString()
        val type = accountType.selectedItem.toString()
        val amount = amountField.text.toDouble()
        val recipient = recipientField.text

        // Implement transfer logic here
    }

    // Define function to close account
    private fun closeAccount() {
        val account = accountList.selectedItem.toString()
        val type = accountType.selectedItem.toString()

        // Implement account closing logic here
    }
}

// Define the main function that creates and initializes the GUI
fun main(args: Array<String>) {
    val bankAppGUI = BankAppGUI()
    bankAppGUI.init()
}
