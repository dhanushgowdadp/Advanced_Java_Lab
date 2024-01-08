package Q8_CustomerJdbc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SwingJDBCApplication {
    

    private JFrame mainFrame;
    private JTextField custNameField, custStateField, custCreditLimitField, repNameField, repStateField, repCommissionField, repRateField;
    private JButton insertCustomerButton, insertRepButton, displayButton;
    private JTextArea displayArea;

    public SwingJDBCApplication() {
        initialize();
    }

    private void initialize() {
        mainFrame = new JFrame("Swing JDBC Application");
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        custNameField = new JTextField(20);
        custStateField = new JTextField(20);
        custCreditLimitField = new JTextField(20);

        repNameField = new JTextField(20);
        repStateField = new JTextField(20);
        repCommissionField = new JTextField(20);
        repRateField = new JTextField(20);

        insertCustomerButton = new JButton("Insert Customer");
        insertRepButton = new JButton("Insert Representative");
        displayButton = new JButton("Display Customers/Reps");

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        // Add action listeners
        insertCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertCustomer();
            }
        });

        insertRepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertRepresentative();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayData();
            }
        });

        // Create layout
        mainFrame.setLayout(new GridLayout(0, 2));
        mainFrame.add(new JLabel("Customer Name:"));
        mainFrame.add(custNameField);
        mainFrame.add(new JLabel("Customer State:"));
        mainFrame.add(custStateField);
        mainFrame.add(new JLabel("Credit Limit:"));
        mainFrame.add(custCreditLimitField);
        mainFrame.add(insertCustomerButton);
        mainFrame.add(new JLabel());
        mainFrame.add(new JLabel("Representative Name:"));
        mainFrame.add(repNameField);
        mainFrame.add(new JLabel("Representative State:"));
        mainFrame.add(repStateField);
        mainFrame.add(new JLabel("Commission:"));
        mainFrame.add(repCommissionField);
        mainFrame.add(new JLabel("Rate:"));
        mainFrame.add(repRateField);
        mainFrame.add(insertRepButton);

        mainFrame.add(displayButton);

        mainFrame.add(new JScrollPane(displayArea));

        mainFrame.setVisible(true);
    }

    private void insertCustomer() {
        try {
            String custName = custNameField.getText();
            String custState = custStateField.getText();
            double creditLimit = Double.parseDouble(custCreditLimitField.getText());

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CustomerDetails", "root", "password");
            String query = "INSERT INTO Customer (CustName, State, Credit_Limit) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, custName);
                preparedStatement.setString(2, custState);
                preparedStatement.setDouble(3, creditLimit);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(mainFrame, "Customer inserted successfully!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "Error inserting customer data: " + ex.getMessage());
        }
    }

    private void insertRepresentative() {
        try {
            String repName = repNameField.getText();
            String repState = repStateField.getText();
            double commission = Double.parseDouble(repCommissionField.getText());
            double rate = Double.parseDouble(repRateField.getText());

            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/CustomerDetails","root","password");
            String query = "INSERT INTO Representative (RepName, State, Commission, Rate) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection1.prepareStatement(query)) {
                preparedStatement.setString(1, repName);
                preparedStatement.setString(2, repState);
                preparedStatement.setDouble(3, commission);
                preparedStatement.setDouble(4, rate);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(mainFrame, "Representative inserted successfully!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "Error inserting representative data: " + ex.getMessage());
        }
    }

    private void displayData() {
        try {
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/CustomerDetails","root","password");
            String query = "SELECT * FROM Customer WHERE State = ? UNION SELECT * FROM Representative WHERE State = ?";
            try (PreparedStatement preparedStatement = connection1.prepareStatement(query)) {
                String state = JOptionPane.showInputDialog(mainFrame, "Enter state to display data:");
                preparedStatement.setString(1, state);
                preparedStatement.setString(2, state);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<String> data = new ArrayList<>();
                while (resultSet.next()) {
                    data.add(resultSet.getString("CustName") + " - " + resultSet.getString("State"));
                }
                if (data.isEmpty()) {
                    JOptionPane.showMessageDialog(mainFrame, "No data found for the given state.");
                } else {
                    displayArea.setText("");
                    for (String entry : data) {
                        displayArea.append(entry + "\n");
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, "Error displaying data: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingJDBCApplication();
            }
        });
    }
}