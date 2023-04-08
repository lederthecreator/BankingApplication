/*
 * Created by JFormDesigner on Sat Apr 01 01:27:49 MSK 2023
 */
package ru.leder.gui

import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import java.awt.*
import java.awt.event.ActionEvent
import java.beans.PropertyChangeListener
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.border.CompoundBorder
import javax.swing.border.EmptyBorder
import javax.swing.border.TitledBorder

/**
 * @author User
 */
class TransactionWindow : JFrame() {
    private fun okButtonHandler(e: ActionEvent) {
        // TODO add your code here
    }

    private fun initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Alexandr
        dialogPane = JPanel()
        contentPanel = JPanel()
        panel1 = JPanel()
        label1 = JLabel()
        panel2 = JPanel()
        label2 = JLabel()
        panel3 = JPanel()
        label3 = JLabel()
        panel4 = JPanel()
        label4 = JLabel()
        buttonBar = JPanel()
        okButton = JButton()

        //======== this ========
        defaultCloseOperation = DISPOSE_ON_CLOSE
        minimumSize = Dimension(320, 430)
        val contentPane = contentPane
        contentPane.layout = BorderLayout()

        //======== dialogPane ========
        run {
            dialogPane!!.border = EmptyBorder(12, 12, 12, 12)
            dialogPane!!.border = CompoundBorder(
                TitledBorder(
                    EmptyBorder(0, 0, 0, 0),
                    "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",
                    TitledBorder.CENTER,
                    TitledBorder.BOTTOM,
                    Font(
                        "Dialo\u0067", Font.BOLD, 12
                    ),
                    Color.red
                ), dialogPane!!.border
            )
            dialogPane!!.addPropertyChangeListener(PropertyChangeListener { e -> if (("borde\u0072" == e.propertyName)) throw RuntimeException() })
            dialogPane!!.layout = BorderLayout()

            //======== contentPanel ========
            run {
                contentPanel!!.layout = GridLayoutManager(4, 1, Insets(0, 0, 0, 0), 10, 10)

                //======== panel1 ========
                run {
                    panel1!!.border = TitledBorder("Time")
                    panel1!!.layout = GridLayoutManager(1, 1, Insets(0, 0, 0, 0), -1, -1)

                    //---- label1 ----
                    label1!!.text = "text"
                    panel1!!.add(
                        label1, GridConstraints(
                            0, 0, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null
                        )
                    )
                }
                contentPanel!!.add(
                    panel1, GridConstraints(
                        0, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null
                    )
                )

                //======== panel2 ========
                run {
                    panel2!!.border = TitledBorder("Recipient")
                    panel2!!.layout = GridLayoutManager(1, 1, Insets(0, 0, 0, 0), -1, -1)

                    //---- label2 ----
                    label2!!.text = "text"
                    panel2!!.add(
                        label2, GridConstraints(
                            0, 0, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null
                        )
                    )
                }
                contentPanel!!.add(
                    panel2, GridConstraints(
                        1, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null
                    )
                )

                //======== panel3 ========
                run {
                    panel3!!.border = TitledBorder("Recipient bank account")
                    panel3!!.layout = GridLayoutManager(1, 1, Insets(0, 0, 0, 0), -1, -1)

                    //---- label3 ----
                    label3!!.text = "text"
                    panel3!!.add(
                        label3, GridConstraints(
                            0, 0, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null
                        )
                    )
                }
                contentPanel!!.add(
                    panel3, GridConstraints(
                        2, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null
                    )
                )

                //======== panel4 ========
                run {
                    panel4!!.border = TitledBorder("Amount")
                    panel4!!.layout = GridLayoutManager(1, 1, Insets(0, 0, 0, 0), -1, -1)

                    //---- label4 ----
                    label4!!.text = "text"
                    panel4!!.add(
                        label4, GridConstraints(
                            0, 0, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null
                        )
                    )
                }
                contentPanel!!.add(
                    panel4, GridConstraints(
                        3, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
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
                (buttonBar!!.layout as GridBagLayout).columnWidths = intArrayOf(0, 80)
                (buttonBar!!.layout as GridBagLayout).columnWeights = doubleArrayOf(1.0, 0.0)

                //---- okButton ----
                okButton!!.text = "OK"
                okButton!!.isFocusPainted = false
                okButton!!.addActionListener({ e: ActionEvent -> okButtonHandler(e) })
                buttonBar!!.add(
                    okButton, GridBagConstraints(
                        1, 0, 1, 1, 0.0, 0.0,
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

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Alexandr
    private var dialogPane: JPanel? = null
    private var contentPanel: JPanel? = null
    private var panel1: JPanel? = null
    private var label1: JLabel? = null
    private var panel2: JPanel? = null
    private var label2: JLabel? = null
    private var panel3: JPanel? = null
    private var label3: JLabel? = null
    private var panel4: JPanel? = null
    private var label4: JLabel? = null
    private var buttonBar: JPanel? = null
    private var okButton: JButton? =
        null // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    init {
        initComponents()
    }
}