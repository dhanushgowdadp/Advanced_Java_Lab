package Q6_Employee;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class EmployeeDataEntry extends JFrame implements ActionListener {

    JLabel nameLabel, ageLabel, payscaleLabel, familyLabel, addressLabel, genderLabel;
    JTextField nameField, ageField, familyField, addressField;
    JComboBox<String> payscaleCombo;
    JRadioButton maleRadio, femaleRadio;
    JButton submitButton;

    public EmployeeDataEntry() {
        setTitle("Employee Data Entry");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        // Create labels and fields
        nameLabel = new JLabel("Employee Name:");
        ageLabel = new JLabel("Employee Age:");
        payscaleLabel = new JLabel("Employee Payscale:");
        familyLabel = new JLabel("Number of Family Members:");
        addressLabel = new JLabel("Address:");
        genderLabel = new JLabel("Gender:");

        nameField = new JTextField();
        ageField = new JTextField();
        payscaleCombo = new JComboBox<>(new String[]{"Level 1", "Level 2", "Level 3"});
        familyField = new JTextField();
        addressField = new JTextField();

        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(payscaleLabel);
        add(payscaleCombo);
        add(familyLabel);
        add(familyField);
        add(addressLabel);
        add(addressField);
        add(genderLabel);
        add(new JLabel());
        add(maleRadio);
        add(femaleRadio);
        add(new JLabel()); // Placeholder for layout
        add(submitButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            int age = 0;
            try {
                age = Integer.parseInt(ageField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid age format. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (age < 22 || age > 60) {
                String newAge = JOptionPane.showInputDialog(this, "Age must be between 22 and 60. Please enter a valid age:");
                try {
                    age = Integer.parseInt(newAge);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid age format. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String name = nameField.getText();
            String payscale = (String) payscaleCombo.getSelectedItem();
            int familyMembers = Integer.parseInt(familyField.getText());
            String address = addressField.getText();
            String gender = maleRadio.isSelected() ? "Male" : "Female";

            String employeeData = "Employee Data:\n" +
                    "Name: " + name + "\n" +
                    "Age: " + age + "\n" +
                    "Payscale: " + payscale + "\n" +
                    "Family Members: " + familyMembers + "\n" +
                    "Address: " + address + "\n" +
                    "Gender: " + gender;

            JOptionPane.showMessageDialog(this, employeeData, "Employee Data", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new EmployeeDataEntry();
    }
}
