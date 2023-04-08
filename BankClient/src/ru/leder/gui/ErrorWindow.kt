/*
 * Created by JFormDesigner on Sat Apr 01 01:19:13 MSK 2023
 */
package ru.leder.gui

import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import java.awt.*
import java.awt.event.ActionEvent
import java.beans.PropertyChangeListener
import javax.swing.*
import javax.swing.border.CompoundBorder
import javax.swing.border.EmptyBorder
import javax.swing.border.LineBorder
import javax.swing.border.TitledBorder

/**
 * @author User
 */
class ErrorWindow : JFrame() {
    private fun okButtonHandler(e: ActionEvent) {
        // TODO add your code here
    }

    private fun initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Alexandr
        dialogPane = JPanel()
        contentPanel = JPanel()
        scrollPane1 = JScrollPane()
        textArea1 = JTextArea()
        buttonBar = JPanel()
        okButton = JButton()

        //======== this ========
        defaultCloseOperation = DISPOSE_ON_CLOSE
        title = "Error!"
        minimumSize = Dimension(350, 300)
        val contentPane = contentPane
        contentPane.layout = BorderLayout()

        //======== dialogPane ========
        run {
            dialogPane!!.border = EmptyBorder(12, 12, 12, 12)
            dialogPane!!.border = CompoundBorder(
                TitledBorder(
                    EmptyBorder(
                        0, 0, 0, 0
                    ),
                    "JF\u006frmDes\u0069gner \u0045valua\u0074ion",
                    TitledBorder.CENTER,
                    TitledBorder.BOTTOM,
                    Font("D\u0069alog", Font.BOLD, 12),
                    Color.red
                ),
                dialogPane!!.border
            )
            dialogPane!!.addPropertyChangeListener(PropertyChangeListener { e -> if (("\u0062order" == e.propertyName)) throw RuntimeException() })
            dialogPane!!.layout = BorderLayout()

            //======== contentPanel ========
            run {
                contentPanel!!.layout = GridLayoutManager(1, 1, Insets(0, 0, 0, 0), 10, 5)

                //======== scrollPane1 ========
                run {


                    //---- textArea1 ----
                    textArea1!!.isEditable = false
                    textArea1!!.font = Font("JetBrains Mono", textArea1!!.font.style, textArea1!!.font.size)
                    textArea1!!.border = LineBorder.createBlackLineBorder()
                    textArea1!!.cursor = Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR)
                    textArea1!!.margin = Insets(5, 5, 5, 5)
                    textArea1!!.minimumSize = Dimension(350, 300)
                    textArea1!!.lineWrap = true
                    scrollPane1!!.setViewportView(textArea1)
                }
                contentPanel!!.add(
                    scrollPane1, GridConstraints(
                        0, 0, 1, 1,
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
    private var scrollPane1: JScrollPane? = null
    var textArea1: JTextArea? = null
    private var buttonBar: JPanel? = null
    private var okButton: JButton? =
        null // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    init {
        initComponents()
    }
}