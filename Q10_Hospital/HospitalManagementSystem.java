package Q10_Hospital;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class HospitalManagementSystem extends JFrame {
    private JTextField patientNameField, medicineTypeField, treatmentTypeField, medicineTypeFilterField;
    private JButton addButton, listButton;
    private JTextArea resultTextArea;

    public HospitalManagementSystem() {
        // Initialize Swing components
        patientNameField = new JTextField(20);
        medicineTypeField = new JTextField(20);
        treatmentTypeField = new JTextField(20);
        medicineTypeFilterField = new JTextField(20);
        addButton = new JButton("Add Patient");
        listButton = new JButton("List Patients");
        resultTextArea = new JTextArea(10, 40);

        // Set up the layout
        setLayout(new FlowLayout());
        add(new JLabel("Patient Name:"));
        add(patientNameField);
        add(new JLabel("Medicine Type:"));
        add(medicineTypeField);
        add(new JLabel("Treatment Type:"));
        add(treatmentTypeField);
        add(addButton);
        add(new JLabel());
        add(new JLabel("Filter by Medicine Type:"));
        add(medicineTypeFilterField);
        add(listButton);
        add(new JScrollPane(resultTextArea));

        // Set up button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listPatients();
            }
        });

        // Set up frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setTitle("Medical Application");
        setVisible(true);
    }

    private void addPatient() {
        String patientName = patientNameField.getText();
        String medicineType = medicineTypeField.getText();
        String treatmentType = treatmentTypeField.getText();

        try {
            // Establish JDBC connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Hospital",
                    "root",
                    "password"
            );

            // Insert patient data into the Patient table
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Patient (patient_name, medicine_type, treatment_type) VALUES (?, ?, ?)"
            );
            preparedStatement.setString(1, patientName);
            preparedStatement.setString(2, medicineType);
            preparedStatement.setString(3, treatmentType);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Patient added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add patient.");
            }

            // Close resources
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void listPatients() {
        String filterMedicineType = medicineTypeFilterField.getText();

        try {
            // Establish JDBC connection
        	Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Hospital",
                    "root",
                    "password"
            );

            // Retrieve patient details based on medicine type
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Patient WHERE medicine_type = ?"
            );
            preparedStatement.setString(1, filterMedicineType);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Display results in the text area
            resultTextArea.setText("");
            while (resultSet.next()) {
                int patientId = resultSet.getInt("patient_id");
                String patientName = resultSet.getString("patient_name");
                String medicineType = resultSet.getString("medicine_type");
                String treatmentType = resultSet.getString("treatment_type");

                resultTextArea.append("Patient ID: " + patientId +
                        "\nPatient Name: " + patientName +
                        "\nMedicine Type: " + medicineType +
                        "\nTreatment Type: " + treatmentType +
                        "\n------------------------\n");
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HospitalManagementSystem();
            }
        });
    }
}
