package Q4_StudentSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class Student {
    private static int nextId = 1;

    private int id;
    private String name;
    private String usn;
    private int age;
    private String address;
    private double sgpaSem1;
    private double sgpaSem2;
    private double sgpaSem3;
    private double sgpaSem4;
    private String category;

    public Student(String name, String usn, int age, String address, double sgpaSem1, double sgpaSem2, double sgpaSem3, double sgpaSem4, String category) {
        this.id = nextId++;
        this.name = name;
        this.usn = usn;
        this.age = age;
        this.address = address;
        this.sgpaSem1 = sgpaSem1;
        this.sgpaSem2 = sgpaSem2;
        this.sgpaSem3 = sgpaSem3;
        this.sgpaSem4 = sgpaSem4;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsn() {
        return usn;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public double getSgpaSem1() {
        return sgpaSem1;
    }

    public double getSgpaSem2() {
        return sgpaSem2;
    }

    public double getSgpaSem3() {
        return sgpaSem3;
    }

    public double getSgpaSem4() {
        return sgpaSem4;
    }

    public String getCategory() {
        return category;
    }

    public double calculateCgpa() {
        return (sgpaSem1 + sgpaSem2 + sgpaSem3 + sgpaSem4) / 4.0;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + "\nName: " + name + "\nUSN: " + usn + "\nAge: " + age + "\nAddress: " + address +
                "\nSGPA (Sem1-Sem4): " + sgpaSem1 + ", " + sgpaSem2 + ", " + sgpaSem3 + ", " + sgpaSem4 +
                "\nCategory: " + category + "\nCGPA: " + calculateCgpa();
    }
}

public class StudentInfoApp {
    private JFrame mainFrame;
    private JTextField nameField, usnField, ageField, addressField, sgpaSem1Field, sgpaSem2Field, sgpaSem3Field, sgpaSem4Field, categoryField;
    private JButton computeButton, doneButton, displayButton;
    private JTextArea displayArea;
    private Map<Integer, Student> studentsMap;

    public StudentInfoApp() {
        studentsMap = new HashMap<>();
        initialize();
    }

    private void initialize() {
        mainFrame = new JFrame("Student Information System");
        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        nameField = new JTextField(20);
        usnField = new JTextField(20);
        ageField = new JTextField(20);
        addressField = new JTextField(20);
        sgpaSem1Field = new JTextField(20);
        sgpaSem2Field = new JTextField(20);
        sgpaSem3Field = new JTextField(20);
        sgpaSem4Field = new JTextField(20);
        categoryField = new JTextField(20);

        computeButton = new JButton("Compute");
        doneButton = new JButton("Done");
        displayButton = new JButton("Display");

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        // Add action listeners
        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computeButtonClicked();
            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doneButtonClicked();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayButtonClicked();
            }
        });

        // Create layout
        mainFrame.setLayout(new GridLayout(0, 2));
        mainFrame.add(new JLabel("Name:"));
        mainFrame.add(nameField);
        mainFrame.add(new JLabel("USN:"));
        mainFrame.add(usnField);
        mainFrame.add(new JLabel("Age:"));
        mainFrame.add(ageField);
        mainFrame.add(new JLabel("Address:"));
        mainFrame.add(addressField);
        mainFrame.add(new JLabel("SGPA (Sem1):"));
        mainFrame.add(sgpaSem1Field);
        mainFrame.add(new JLabel("SGPA (Sem2):"));
        mainFrame.add(sgpaSem2Field);
        mainFrame.add(new JLabel("SGPA (Sem3):"));
        mainFrame.add(sgpaSem3Field);
        mainFrame.add(new JLabel("SGPA (Sem4):"));
        mainFrame.add(sgpaSem4Field);
        mainFrame.add(new JLabel("Category:"));
        mainFrame.add(categoryField);

        mainFrame.add(computeButton);
        mainFrame.add(doneButton);
        mainFrame.add(displayButton);

        mainFrame.add(new JScrollPane(displayArea));

        mainFrame.setVisible(true);
    }

    private void computeButtonClicked() {
        try {
            String name = nameField.getText();
            String usn = usnField.getText();
            int age = Integer.parseInt(ageField.getText());
            String address = addressField.getText();
            double sgpaSem1 = Double.parseDouble(sgpaSem1Field.getText());
            double sgpaSem2 = Double.parseDouble(sgpaSem2Field.getText());
            double sgpaSem3 = Double.parseDouble(sgpaSem3Field.getText());
            double sgpaSem4 = Double.parseDouble(sgpaSem4Field.getText());
            String category = categoryField.getText();

            // Validate age and sgpa
            if (age <= 18 || age > 30 || sgpaSem1 < 0 || sgpaSem1 > 10 ||
                sgpaSem2 < 0 || sgpaSem2 > 10 || sgpaSem3 < 0 || sgpaSem3 > 10 ||
                sgpaSem4 < 0 || sgpaSem4 > 10) {
                JOptionPane.showMessageDialog(mainFrame, "Invalid entries. Please check age and SGPA values.");
                return;
            }

            // Create Student object
            Student student = new Student(name, usn, age, address, sgpaSem1, sgpaSem2, sgpaSem3, sgpaSem4, category);

            // Display CGPA
            JOptionPane.showMessageDialog(mainFrame, "CGPA: " + student.calculateCgpa());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Invalid entries. Please check the entered values.");
        }
    }

    private void doneButtonClicked() {
        computeButtonClicked(); // Perform validation before adding to the map

        // Add student to the map
        String usn = usnField.getText();
        Student student = new Student(
                nameField.getText(),
                usn,
                Integer.parseInt(ageField.getText()),
                addressField.getText(),
                Double.parseDouble(sgpaSem1Field.getText()),
                Double.parseDouble(sgpaSem2Field.getText()),
                Double.parseDouble(sgpaSem3Field.getText()),
                Double.parseDouble(sgpaSem4Field.getText()),
                categoryField.getText()
        );

        studentsMap.put(student.getId(), student);

        // Clear fields
        clearFields();
    }

    private void displayButtonClicked() {
        // Display student details in the text area
        displayArea.setText("");
        for (Map.Entry<Integer, Student> entry : studentsMap.entrySet()) {
            displayArea.append(entry.getValue().toString() + "\n\n");
        }
    }

    private void clearFields() {
        nameField.setText("");
        usnField.setText("");
        ageField.setText("");
        addressField.setText("");
        sgpaSem1Field.setText("");
        sgpaSem2Field.setText("");
        sgpaSem3Field.setText("");
        sgpaSem4Field.setText("");
        categoryField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentInfoApp();
            }
        });
    }
}
