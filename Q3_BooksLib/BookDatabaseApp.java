package Q3_BooksLib;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Book {
    private static int nextId = 1;

    private int id;
    private String title;
    private String author;
    private String publisher;
    private double price;

    public Book(String title, String author, String publisher, double price) {
        this.id = nextId++;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Publisher: " + publisher + ", Price: $" + price;
    }
}

class BookDatabase {
    private List<Book> books;

    public BookDatabase() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooksSortedByPrice() {
        Collections.sort(books, (b1, b2) -> Double.compare(b1.getPrice(), b2.getPrice()));
        System.out.println("Books sorted by price (ascending):");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void displayBooksByAuthor(String author) {
        System.out.println("Books by author " + author + ":");
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(book);
            }
        }
    }

    public List<Book> getBooksAbovePrice(double specifiedPrice) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPrice() > specifiedPrice) {
                result.add(book);
            }
        }
        return result;
    }
}

class BookEntryFrame extends JFrame {
    private JTextField titleField, authorField, publisherField, priceField;
    private JButton addButton, sortButton, authorButton, priceButton;
    private BookDatabase bookDatabase;

    public BookEntryFrame() {
        setTitle("Book Database");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        publisherField = new JTextField(20);
        priceField = new JTextField(20);
        addButton = new JButton("Add Book");
        sortButton = new JButton("Sort by Price");
        authorButton = new JButton("List by Author");
        priceButton = new JButton("Filter by Price");

        // Create Book Database
        bookDatabase = new BookDatabase();

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookDatabase.displayBooksSortedByPrice();
            }
        });

        authorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String author = JOptionPane.showInputDialog("Enter author name:");
                bookDatabase.displayBooksByAuthor(author);
            }
        });

        priceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String priceStr = JOptionPane.showInputDialog("Enter minimum price:");
                double specifiedPrice = Double.parseDouble(priceStr);
                List<Book> filteredBooks = bookDatabase.getBooksAbovePrice(specifiedPrice);

                System.out.println("Books with price greater than $" + specifiedPrice + ":");
                for (Book book : filteredBooks) {
                    System.out.println(book);
                }
            }
        });

        // Create layout
        setLayout(new GridLayout(6,2));
        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Author:"));
        add(authorField);
        add(new JLabel("Publisher:"));
        add(publisherField);
        add(new JLabel("Price:"));
        add(priceField);
        add(addButton);
        add(sortButton);
        add(authorButton);
        add(priceButton);
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();
        double price = Double.parseDouble(priceField.getText());

        Book newBook = new Book(title, author, publisher, price);
        bookDatabase.addBook(newBook);

        JOptionPane.showMessageDialog(this, "Book added successfully!");

        // Clear fields
        titleField.setText("");
        authorField.setText("");
        publisherField.setText("");
        priceField.setText("");
    }
}

public class BookDatabaseApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BookEntryFrame().setVisible(true);
            }
        });
    }
}
