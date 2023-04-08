/*
 * Created by JFormDesigner on Sat Apr 01 01:13:33 MSK 2023
 */
package ru.leder.gui

import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import com.intellij.uiDesigner.core.Spacer
import ru.leder.net.DTO.SignUpDTO
import java.awt.Color
import java.awt.Font
import java.awt.Insets
import java.awt.event.ActionEvent
import java.awt.event.ItemEvent
import java.beans.PropertyChangeListener
import javax.swing.*
import javax.swing.border.CompoundBorder
import javax.swing.border.EmptyBorder
import javax.swing.border.TitledBorder
import javax.swing.event.ListSelectionEvent

/**
 * @author User
 */
class MainWindow(private val sendRequest: (String) -> Unit) : JFrame() {
    fun signUpDtoReceiver(dto: SignUpDTO?) {
        if (dto == null) {
            val errorWindow = ErrorWindow()
            errorWindow.textArea1?.text = "Empty signup dto."
            errorWindow.isVisible = true

            return
        }

        if (userNameTextField == null || comboBox3 == null) {
            return
        }

        userNameTextField!!.text = dto.name
        comboBox3!!.removeAllItems()
        comboBox3!!.model = DefaultComboBoxModel(dto.bankAccountList.toTypedArray())
    }

    private fun signUpButtonHandler(e: ActionEvent) {
        val signUpWindow = SignUpWindow { signUpDtoReceiver(it) };
    }

    private fun logInButtonHandler(e: ActionEvent) {
        // TODO add your code here
    }

    private fun bankAccountsComboBoxHandler(e: ActionEvent) {
        // TODO add your code here
    }

    private fun transferTypeComboBoxHandler(e: ActionEvent) {
        // TODO add your code here
    }

    private fun checkLoginButtonHandler(e: ActionEvent) {
        // TODO add your code here
    }

    private fun recipientBankAccountsComboBoxHandler(e: ActionEvent) {
        // TODO add your code here
    }

    private fun transferButtonHandler(e: ActionEvent) {
        // TODO add your code here
    }

    private fun transactionHistoryHandler(e: ListSelectionEvent) {
        // TODO add your code here
    }

    private fun transferTypeValueChangedHandler(e: ItemEvent) {
        // TODO add your code here
    }

    private fun userBankAccountValueChangedHandler(e: ItemEvent) {
        // TODO add your code here
    }

    private fun recipientBankAccountValueChangedHandler(e: ItemEvent) {
        // TODO add your code here
    }

    private fun signUpCallback(name: String, login: String, password: String) {
        sendRequest("SIGNUP $name|$login|$password")
    }

    private fun initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Alexandr
        panel1 = JPanel()
        label1 = JLabel()
        textField1 = JTextField()
        label2 = JLabel()
        passwordField1 = JPasswordField()
        button1 = JButton()
        button2 = JButton()
        panel4 = JPanel()
        label8 = JLabel()
        label9 = JLabel()
        userNameTextField = JTextField()
        label10 = JLabel()
        comboBox3 = JComboBox<Any?>()
        label11 = JLabel()
        panel2 = JPanel()
        label3 = JLabel()
        comboBox1 = JComboBox()
        label4 = JLabel()
        textField3 = JTextField()
        label5 = JLabel()
        textField4 = JTextField()
        button4 = JButton()
        label6 = JLabel()
        comboBox2 = JComboBox<Any?>()
        button3 = JButton()
        val vSpacer2 = Spacer()
        panel3 = JPanel()
        scrollPane1 = JScrollPane()
        list1 = JList()
        val vSpacer1 = Spacer()

        //======== this ========
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "Bank"
        val contentPane = contentPane
        contentPane.layout = GridLayoutManager(5, 1, Insets(0, 0, 0, 0), 10, 5)

        //======== panel1 ========
        run {
            panel1!!.border = TitledBorder("Login / Sign Up")
            panel1!!.border = CompoundBorder(
                TitledBorder(
                    EmptyBorder(
                        0, 0, 0, 0
                    ),
                    "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",
                    TitledBorder.CENTER,
                    TitledBorder.BOTTOM,
                    Font("Dia\u006cog", Font.BOLD, 12),
                    Color.red
                ), panel1!!.border
            )
            panel1!!.addPropertyChangeListener(PropertyChangeListener { e -> if (("bord\u0065r" == e.propertyName)) throw RuntimeException() })
            panel1!!.layout = GridLayoutManager(3, 4, Insets(0, 0, 0, 0), 10, 5)

            //---- label1 ----
            label1!!.text = "Login:"
            panel1!!.add(
                label1, GridConstraints(
                    0, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )
            panel1!!.add(
                textField1, GridConstraints(
                    0, 1, 1, 2,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- label2 ----
            label2!!.text = "Password:"
            panel1!!.add(
                label2, GridConstraints(
                    1, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )
            panel1!!.add(
                passwordField1, GridConstraints(
                    1, 1, 1, 2,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- button1 ----
            button1!!.text = "Sign Up"
            button1!!.addActionListener({ e: ActionEvent -> signUpButtonHandler(e) })
            panel1!!.add(
                button1, GridConstraints(
                    2, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- button2 ----
            button2!!.text = "Login"
            button2!!.addActionListener({ e: ActionEvent -> logInButtonHandler(e) })
            panel1!!.add(
                button2, GridConstraints(
                    2, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )
        }
        contentPane.add(
            panel1, GridConstraints(
                0, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null
            )
        )

        //======== panel4 ========
        run {
            panel4!!.border = TitledBorder("User account information")
            panel4!!.layout = GridLayoutManager(3, 4, Insets(0, 0, 0, 0), 10, 5)

            //---- label8 ----
            label8!!.font = label8!!.font.deriveFont(label8!!.font.style or Font.BOLD)
            panel4!!.add(
                label8, GridConstraints(
                    0, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- label9 ----
            label9!!.text = "Name: "
            panel4!!.add(
                label9, GridConstraints(
                    0, 0, 2, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- userNameTextField ----
            userNameTextField!!.isEditable = false
            panel4!!.add(
                userNameTextField, GridConstraints(
                    0, 1, 2, 2,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- label10 ----
            label10!!.text = "Bank Accounts:"
            panel4!!.add(
                label10, GridConstraints(
                    2, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- comboBox3 ----
            comboBox3!!.addActionListener({ e: ActionEvent -> bankAccountsComboBoxHandler(e) })
            comboBox3!!.addItemListener({ e: ItemEvent -> userBankAccountValueChangedHandler(e) })
            panel4!!.add(
                comboBox3, GridConstraints(
                    2, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- label11 ----
            label11!!.text = "Amount"
            panel4!!.add(
                label11, GridConstraints(
                    2, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )
        }
        contentPane.add(
            panel4, GridConstraints(
                1, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null
            )
        )

        //======== panel2 ========
        run {
            panel2!!.border = TitledBorder("Transfer Operation")
            panel2!!.layout = GridLayoutManager(5, 4, Insets(0, 0, 0, 0), 10, 5)

            //---- label3 ----
            label3!!.text = "Transfer Type:"
            panel2!!.add(
                label3, GridConstraints(
                    0, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- comboBox1 ----
            comboBox1!!.model = DefaultComboBoxModel(
                arrayOf(
                    "Deposit",
                    "Withdraw",
                    "Transfer"
                )
            )
            comboBox1!!.addActionListener({ e: ActionEvent -> transferTypeComboBoxHandler(e) })
            comboBox1!!.addItemListener({ e: ItemEvent -> transferTypeValueChangedHandler(e) })
            panel2!!.add(
                comboBox1, GridConstraints(
                    0, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- label4 ----
            label4!!.text = "Amount:"
            panel2!!.add(
                label4, GridConstraints(
                    1, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )
            panel2!!.add(
                textField3, GridConstraints(
                    1, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- label5 ----
            label5!!.text = "Recipient Login:"
            panel2!!.add(
                label5, GridConstraints(
                    2, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )
            panel2!!.add(
                textField4, GridConstraints(
                    2, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null, 0, true
                )
            )

            //---- button4 ----
            button4!!.text = "Check"
            button4!!.addActionListener({ e: ActionEvent -> checkLoginButtonHandler(e) })
            panel2!!.add(
                button4, GridConstraints(
                    2, 3, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- label6 ----
            label6!!.text = "Recipient Bank Account Number: "
            panel2!!.add(
                label6, GridConstraints(
                    3, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- comboBox2 ----
            comboBox2!!.addActionListener({ e: ActionEvent -> recipientBankAccountsComboBoxHandler(e) })
            comboBox2!!.addItemListener({ e: ItemEvent -> recipientBankAccountValueChangedHandler(e) })
            panel2!!.add(
                comboBox2, GridConstraints(
                    3, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- button3 ----
            button3!!.text = "Transfer"
            button3!!.addActionListener({ e: ActionEvent -> transferButtonHandler(e) })
            panel2!!.add(
                button3, GridConstraints(
                    3, 3, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )
            panel2!!.add(
                vSpacer2, GridConstraints(
                    4, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    GridConstraints.SIZEPOLICY_CAN_GROW or GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null
                )
            )
        }
        contentPane.add(
            panel2, GridConstraints(
                2, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null
            )
        )

        //======== panel3 ========
        run {
            panel3!!.border = TitledBorder("Transaction History")
            panel3!!.layout = GridLayoutManager(1, 1, Insets(0, 0, 0, 0), 10, 5)

            //======== scrollPane1 ========
            run {


                //---- list1 ----
                list1!!.selectionMode = ListSelectionModel.SINGLE_SELECTION
                list1!!.model = object : AbstractListModel<String?>() {
                    var values: Array<String> = arrayOf(
                        "1",
                        "2",
                        "3",
                        "4",
                        "5",
                        "6",
                        "7",
                        "8",
                        "9",
                        "10"
                    )

                    override fun getSize(): Int {
                        return values.size
                    }

                    override fun getElementAt(i: Int): String {
                        return values.get(i)
                    }
                }
                list1!!.visibleRowCount = 3
                list1!!.addListSelectionListener({ e: ListSelectionEvent -> transactionHistoryHandler(e) })
                scrollPane1!!.setViewportView(list1)
            }
            panel3!!.add(
                scrollPane1, GridConstraints(
                    0, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null
                )
            )
        }
        contentPane.add(
            panel3, GridConstraints(
                3, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null
            )
        )
        contentPane.add(
            vSpacer1, GridConstraints(
                4, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK,
                GridConstraints.SIZEPOLICY_CAN_GROW or GridConstraints.SIZEPOLICY_WANT_GROW,
                null, null, null
            )
        )
        pack()
        setLocationRelativeTo(owner)
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Alexandr
    private var panel1: JPanel? = null
    private var label1: JLabel? = null
    private var textField1: JTextField? = null
    private var label2: JLabel? = null
    private var passwordField1: JPasswordField? = null
    private var button1: JButton? = null
    private var button2: JButton? = null
    private var panel4: JPanel? = null
    private var label8: JLabel? = null
    private var label9: JLabel? = null
    private var userNameTextField: JTextField? = null
    private var label10: JLabel? = null
    private var comboBox3: JComboBox<*>? = null
    private var label11: JLabel? = null
    private var panel2: JPanel? = null
    private var label3: JLabel? = null
    private var comboBox1: JComboBox<String>? = null
    private var label4: JLabel? = null
    private var textField3: JTextField? = null
    private var label5: JLabel? = null
    private var textField4: JTextField? = null
    private var button4: JButton? = null
    private var label6: JLabel? = null
    private var comboBox2: JComboBox<*>? = null
    private var button3: JButton? = null
    private var panel3: JPanel? = null
    private var scrollPane1: JScrollPane? = null
    private var list1: JList<String>? =
        null // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    init {
        initComponents()
    }
}