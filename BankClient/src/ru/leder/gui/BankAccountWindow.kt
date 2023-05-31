package ru.leder.gui

import com.intellij.uiDesigner.core.GridConstraints
import ru.leder.net.dto.BankAccountDto
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.ActionEvent
import javax.swing.*

class BankAccountWindow(dto: BankAccountDto) : JFrame() {
    private val panel = JPanel()

    private val numberLabel = JLabel("Number")
    private val numberTextField = JTextField(dto.number)

    private val balanceLabel = JLabel("Balance")
    private val balanceTextField = JTextField(dto.balance.toString())

    private val productTypeLabel = JLabel("Type")
    private val productTypeTextField = JTextField(dto.type)

    private val currencyLabel = JLabel("Currency")
    private val currencyTextField = JTextField(dto.currency)

    private val durationLabel = JLabel("Duration")
    private val durationTextField = JTextField(dto.duration.toString())

    private val okButton = JButton("Ok")

    init {
        defaultCloseOperation = DISPOSE_ON_CLOSE


        isResizable = false
        size = Dimension(600, 100)
        minimumSize = size
        maximumSize = size
        panel.layout = GridLayout(6,2)

        panel.apply {
            add(numberLabel, GridConstraints(
                    0, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null))

            add(numberTextField, GridConstraints(
                    0, 1, 1, 2,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null))

            add(balanceLabel, GridConstraints(
                1, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_FIXED,
                null, null, null))

            add(balanceTextField, GridConstraints(
                1, 1, 1, 2,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_FIXED,
                null, null, null))

            add(productTypeLabel, GridConstraints(
                2, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null))

            add(productTypeTextField, GridConstraints(
                2, 1, 1, 2,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_FIXED,
                null, null, null))

            add(currencyLabel, GridConstraints(
                3, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null))

            add(currencyTextField, GridConstraints(
                3, 1, 1, 2,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_FIXED,
                null, null, null))

            add(durationLabel, GridConstraints(
                4, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null))

            add(durationTextField, GridConstraints(
                4, 1, 1, 2,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_FIXED,
                null, null, null))

            okButton.addActionListener { e: ActionEvent -> okButtonClickHandler(e) }
            add(okButton, GridConstraints(
                5, 1, 1, 2,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null))
        }

        panel.components.forEach {
            if (it is JTextField) it.isEditable = false
        }

        contentPane.add(panel)
        pack()
    }

    private fun okButtonClickHandler(e: ActionEvent) {
        this.dispose()
    }
}