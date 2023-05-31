/*
 * Created by JFormDesigner on Sat Apr 01 00:49:17 MSK 2023
 */
package ru.leder.gui

import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import com.intellij.uiDesigner.core.Spacer
import ru.leder.net.dto.SignUpDto
import java.awt.*
import java.awt.event.ActionEvent
import java.beans.PropertyChangeListener
import javax.swing.*
import javax.swing.border.CompoundBorder
import javax.swing.border.EmptyBorder
import javax.swing.border.TitledBorder

/**
 * @author User
 */
class SignUpWindow(val callbackReceiver: (SignUpDto) -> Unit) : JFrame() {
    private fun okButtonHandler(e: ActionEvent) {
        val login = textField2?.text
        val name = textField1?.text
        val password = String(passwordField1!!.password)

        if (login.isNullOrEmpty() || name.isNullOrEmpty() || password.isNullOrEmpty()) {
            val errorWindow = ErrorWindow("Одно из полей не заполнено")
            errorWindow.isVisible = true
        }

        val dto = SignUpDto(
            name = name!!,
            login = login!!,
            password = password
        )
        callbackReceiver(dto)

        this.dispose()
    }

    private fun cancelButtonHandler(e: ActionEvent) {
        // TODO add your code here
    }

    private fun initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Alexandr
        dialogPane = JPanel()
        contentPanel = JPanel()
        label1 = JLabel()
        textField1 = JTextField()
        val hSpacer2 = Spacer()
        label2 = JLabel()
        textField2 = JTextField()
        val hSpacer1 = Spacer()
        label3 = JLabel()
        passwordField1 = JPasswordField()
        buttonBar = JPanel()
        vSpacer1 = JPanel(null)
        okButton = JButton()
        cancelButton = JButton()

        //======== this ========
        defaultCloseOperation = DISPOSE_ON_CLOSE
        minimumSize = Dimension(500, 300)
        val contentPane = contentPane
        contentPane.layout = BorderLayout()

        //======== dialogPane ========
        run {
            dialogPane!!.border = TitledBorder("Sign up")
            dialogPane!!.border = CompoundBorder(
                TitledBorder(
                    EmptyBorder(0, 0, 0, 0),
                    "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e",
                    TitledBorder.CENTER,
                    TitledBorder.BOTTOM,
                    Font("D\u0069al\u006fg", Font.BOLD, 12),
                    Color.red
                ), dialogPane!!.border
            )
            dialogPane!!.addPropertyChangeListener(PropertyChangeListener { e -> if (("\u0062or\u0064er" == e.propertyName)) throw RuntimeException() })
            dialogPane!!.layout = BorderLayout()

            //======== contentPanel ========
            run {
                contentPanel!!.layout = GridLayoutManager(3, 7, Insets(0, 0, 0, 0), 10, 5)

                //---- label1 ----
                label1!!.text = "Name"
                contentPanel!!.add(
                    label1, GridConstraints(
                        0, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null
                    )
                )
                contentPanel!!.add(
                    textField1, GridConstraints(
                        0, 2, 1, 4,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null
                    )
                )
                contentPanel!!.add(
                    hSpacer2, GridConstraints(
                        1, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK,
                        null, null, null
                    )
                )

                //---- label2 ----
                label2!!.text = "Login"
                contentPanel!!.add(
                    label2, GridConstraints(
                        1, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null
                    )
                )
                contentPanel!!.add(
                    textField2, GridConstraints(
                        1, 2, 1, 4,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null
                    )
                )
                contentPanel!!.add(
                    hSpacer1, GridConstraints(
                        1, 6, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK,
                        null, null, null
                    )
                )

                //---- label3 ----
                label3!!.text = "Password"
                contentPanel!!.add(
                    label3, GridConstraints(
                        2, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null
                    )
                )
                contentPanel!!.add(
                    passwordField1, GridConstraints(
                        2, 2, 1, 4,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null
                    )
                )
            }
            dialogPane!!.add(contentPanel, BorderLayout.CENTER)

            //======== buttonBar ========
            run {
                buttonBar!!.border = EmptyBorder(12, 0, 0, 0)
                buttonBar!!.layout = GridBagLayout()
                (buttonBar!!.layout as GridBagLayout).columnWidths = intArrayOf(0, 85, 80)
                (buttonBar!!.layout as GridBagLayout).columnWeights = doubleArrayOf(1.0, 0.0, 0.0)
                buttonBar!!.add(
                    vSpacer1, GridBagConstraints(
                        0, 0, 1, 2, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        Insets(0, 0, 5, 5), 0, 0
                    )
                )

                //---- okButton ----
                okButton!!.text = "OK"
                okButton!!.addActionListener({ e: ActionEvent -> okButtonHandler(e) })
                buttonBar!!.add(
                    okButton, GridBagConstraints(
                        1, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        Insets(0, 0, 0, 5), 0, 0
                    )
                )

                //---- cancelButton ----
                cancelButton!!.text = "Cancel"
                cancelButton!!.addActionListener({ e: ActionEvent -> cancelButtonHandler(e) })
                buttonBar!!.add(
                    cancelButton, GridBagConstraints(
                        2, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        Insets(0, 0, 0, 0), 0, 0
                    )
                )
            }
            dialogPane!!.add(buttonBar, BorderLayout.SOUTH)
        }
        contentPane.add(dialogPane, BorderLayout.CENTER)
        pack()
        setLocationRelativeTo(owner)
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY
    //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Alexandr
    private var dialogPane: JPanel? = null
    private var contentPanel: JPanel? = null
    private var label1: JLabel? = null
    private var textField1: JTextField? = null
    private var label2: JLabel? = null
    private var textField2: JTextField? = null
    private var label3: JLabel? = null
    private var passwordField1: JPasswordField? = null
    private var buttonBar: JPanel? = null
    private var vSpacer1: JPanel? = null
    private var okButton: JButton? = null
    private var cancelButton: JButton? =
        null // JFormDesigner - End of variables declaration
    // GEN-END:variables  @formatter:on

    init {
        initComponents()
    }
}