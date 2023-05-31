/*
 * Created by JFormDesigner on Thu Mar 30 08:48:43 MSK 2023
 */
package ru.leder.gui

import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import com.intellij.uiDesigner.core.Spacer
import ru.leder.net.dto.LoginDto
import java.awt.Color
import java.awt.Font
import java.awt.Insets
import java.awt.event.ActionEvent
import javax.swing.*
import javax.swing.border.CompoundBorder
import javax.swing.border.EmptyBorder
import javax.swing.border.TitledBorder

/**
 * @author User
 */
class MainWindow1(val sendRequest: (String) -> Unit) : JFrame() {
    fun loginDtoReceiver(dto: LoginDto) {
        textField6?.text = dto.name

        if (panel1 == null) {
            return
        }

        for (component in panel1!!.components) {
            component.isEnabled = false
        }
    }
    private fun SignUpButtonHandler(e: ActionEvent) {

    }
    private fun LogInButtonHandler(e: ActionEvent) {
        val login = textField1?.text ?: ""
        val password = textField2?.text ?: ""

        sendRequest("LOGIN $login|$password")
    }

    private fun TransferButtonHandler(e: ActionEvent) {
        println("Transfer button is pressed")
    }

    private fun initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Alexandr
        panel1 = JPanel()
        label1 = JLabel()
        textField1 = JTextField()
        label2 = JLabel()
        textField2 = JTextField()
        button1 = JButton()
        button2 = JButton()
        panel4 = JPanel()
        label8 = JLabel()
        label9 = JLabel()
        textField6 = JTextField()
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
        label6 = JLabel()
        comboBox2 = JComboBox<Any?>()
        button3 = JButton()
        val vSpacer2 = Spacer()
        panel3 = JPanel()
        scrollPane1 = JScrollPane()
        textArea1 = JTextArea()
        val vSpacer1 = Spacer()

        //======== this ========
        val contentPane = contentPane
        contentPane.layout = GridLayoutManager(6, 1, Insets(0, 0, 0, 0), 10, 5)

        //======== panel1 ========
        run {
            panel1!!.border = TitledBorder("Login / Sign Up")
            panel1!!.border = CompoundBorder(
                TitledBorder(
                    EmptyBorder(0, 0, 0, 0),
                    "JF\u006frmDes\u0069gner \u0045valua\u0074ion",
                    TitledBorder.CENTER,
                    TitledBorder.BOTTOM,
                    Font("D\u0069alog", Font.BOLD, 12),
                    Color.red
                ), panel1!!.border
            )
            panel1!!.addPropertyChangeListener { e -> if ("\u0062order" == e.propertyName) throw RuntimeException() }
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
                textField2, GridConstraints(
                    1, 1, 1, 2,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- button1 ----
            button1!!.text = "Sign Up"
            button1!!.addActionListener { e: ActionEvent -> SignUpButtonHandler(e) }
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
            button2!!.addActionListener { e: ActionEvent -> LogInButtonHandler(e) }
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
                    1, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )

            //---- textField6 ----
            textField6!!.isEditable = false
            panel4!!.add(
                textField6, GridConstraints(
                    1, 1, 1, 1,
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
            panel4!!.add(
                comboBox3, GridConstraints(
                    2, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
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
                    0, 0, 1, 1,
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
                    1, 0, 1, 1,
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
                    2, 0, 1, 1,
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

            //---- label6 ----
            label6!!.text = "Recipient Bank Account Number: "
            panel2!!.add(
                label6, GridConstraints(
                    3, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null
                )
            )
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
            button3!!.addActionListener { e: ActionEvent -> TransferButtonHandler(e) }
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
                    4, 0, 1, 1,
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
            panel3!!.layout = GridLayoutManager(2, 3, Insets(0, 0, 0, 0), 10, 5)

            //======== scrollPane1 ========
            run {


                //---- textArea1 ----
                textArea1!!.isEditable = false
                textArea1!!.font = Font("JetBrains Mono Medium", Font.PLAIN, 16)
                scrollPane1!!.setViewportView(textArea1)
            }
            panel3!!.add(
                scrollPane1, GridConstraints(
                    0, 0, 2, 3,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
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

        defaultCloseOperation = EXIT_ON_CLOSE
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Alexandr
    private var panel1: JPanel? = null
    private var label1: JLabel? = null
    private var textField1: JTextField? = null
    private var label2: JLabel? = null
    private var textField2: JTextField? = null
    private var button1: JButton? = null
    private var button2: JButton? = null
    private var panel4: JPanel? = null
    private var label8: JLabel? = null
    private var label9: JLabel? = null
    private var textField6: JTextField? = null
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
    private var label6: JLabel? = null
    private var comboBox2: JComboBox<*>? = null
    private var button3: JButton? = null
    private var panel3: JPanel? = null
    private var scrollPane1: JScrollPane? = null
    private var textArea1: JTextArea? =
        null // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    init {
        initComponents()
    }
}