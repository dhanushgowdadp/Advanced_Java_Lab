package Q7_LoginJDBC;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginForm extends JFrame implements ActionListener {

    JLabel loginIdLabel, nameLabel, passwordLabel;
    JTextField loginIdField, nameField;
    JPasswordField passwordField;
    JButton submitButton;

    public LoginForm() {
        setTitle("Login Form");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        // Create labels and fields
        loginIdLabel = new JLabel("Login ID:");
        nameLabel = new JLabel("Name:");
        passwordLabel = new JLabel("Password:");

        loginIdField = new JTextField();
        nameField = new JTextField();
        passwordField = new JPasswordField();

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        // Add components to the frame
        add(loginIdLabel);
        add(loginIdField);
        add(nameLabel);
        add(nameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Placeholder for layout
        add(submitButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String loginId = loginIdField.getText();
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                // Connect to your database here (replace with your database details)
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginDetails", "root", "password");

                // Prepare and execute SQL query to insert data
                PreparedStatement statement = connection.prepareStatement("INSERT INTO users (login_id, name, password) VALUES (?, ?, ?)");
                statement.setString(1, loginId);
                statement.setString(2, name);
                statement.setString(3, password);
                statement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Login details added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error adding login details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            //Connection connection1=DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginDetails","root","password");
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
