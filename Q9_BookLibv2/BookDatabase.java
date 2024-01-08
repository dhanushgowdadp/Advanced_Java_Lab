package Q9_BookLibv2;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Book {
    private static int nextId = 1;

    private int bookId;
    private String title;
    private String author;
    private String publisher;
    private double price;

    public Book(String title, String author, String publisher, double price) {
        this.bookId = nextId++;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
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

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + "\nTitle: " + title + "\nAuthor: " + author + "\nPublisher: " +
                publisher + "\nPrice: $" + price;
    }
}

public class BookDatabase {
    private Map<Integer, Book> bookDatabase;

    public BookDatabase() {
        bookDatabase = new HashMap<>();
    }

    public void addBook(String title, String author, String publisher, double price) {
        Book book = new Book(title, author, publisher, price);
        bookDatabase.put(book.getBookId(), book);
        System.out.println("Book added successfully!\n");
    }

    public void findBooksByTitle(String keyword) {
        System.out.println("Books matching the title or part of the title: " + keyword);
        for (Book book : bookDatabase.values()) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
                System.out.println("----------------------------");
            }
        }
    }

    public void identifyBooksByPublisher(String publisher) {
        System.out.println("Books from the publisher: " + publisher);
        for (Book book : bookDatabase.values()) {
            if (book.getPublisher().equalsIgnoreCase(publisher)) {
                System.out.println(book);
                System.out.println("----------------------------");
            }
        }
    }

    public void updatePublisher(String title, String newPublisher) {
        for (Book book : bookDatabase.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setPublisher(newPublisher);
                System.out.println("Publisher updated successfully for book with title: " + title + "\n");
                return;
            }
        }
        System.out.println("Book with title " + title + " not found.\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDatabase bookDatabase = new BookDatabase();

        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. Find Books by Title");
            System.out.println("3. Identify Books by Publisher");
            System.out.println("4. Update Publisher");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter publisher: ");
                    String publisher = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    bookDatabase.addBook(title, author, publisher, price);
                    break;
                case 2:
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Enter keyword to search in the title: ");
                    String keyword = scanner.nextLine();
                    bookDatabase.findBooksByTitle(keyword);
                    break;
                case 3:
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Enter publisher to identify books: ");
                    String searchPublisher = scanner.nextLine();
                    bookDatabase.identifyBooksByPublisher(searchPublisher);
                    break;
                case 4:
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Enter title of the book to update publisher: ");
                    String updateTitle = scanner.nextLine();
                    System.out.print("Enter new publisher: ");
                    String newPublisher = scanner.nextLine();
                    bookDatabase.updatePublisher(updateTitle, newPublisher);
                    break;
                case 5:
                    System.out.println("Exiting program. Bye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}