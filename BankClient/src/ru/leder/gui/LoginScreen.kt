import javax.swing.*

class LoginScreen(title: String) : JFrame() {

    init {
        this.title = title

        // Define components
        val nameLabel = JLabel("Username:")
        val nameText = JTextField(20)
        val passLabel = JLabel("Password:")
        val passText = JPasswordField(20)
        val loginButton = JButton("Login")

        // Create layout
        val layout = GroupLayout(contentPane)
        contentPane.layout = layout

        layout.autoCreateGaps = true
        layout.autoCreateContainerGaps = true

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameLabel)
                .addComponent(passLabel))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameText)
                .addComponent(passText)
                .addComponent(loginButton)))

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel)
                .addComponent(nameText))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(passLabel)
                .addComponent(passText))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(loginButton)))

        // Add action listener to login button
        loginButton.addActionListener {
            // Check if username and password are correct
            if (nameText.text == "username" && passText.text == "password") {
                JOptionPane.showMessageDialog(this, "Welcome!")
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.")
            }
        }

        // Show login screen
        pack()
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isVisible = true
    }

}

fun main() {
    LoginScreen("Login Screen")
}