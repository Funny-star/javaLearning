package homework.lab1;

public class BookDriver {
    public static void main(String[] args) {

        // Test the parameterized constructor
        Book book1 = new Book("Java Programming", "John Smith", "978-0-13-468599-1", 89, 3, "Pearson");
        System.out.println("=== Book 1 (created with parameterized constructor) ===");
        System.out.println(book1);
        System.out.println();

        // Test the default constructor and setters
        Book book2 = new Book();
        book2.setTitle("Data Structures");
        book2.setAuthor("Jane Doe");
        book2.setISBN("978-0-13-257627-7");
        book2.setListPrice(75);
        book2.setEdition(2);
        book2.setPublisher("Addison-Wesley");
        System.out.println("=== Book 2 (created with default constructor + setters) ===");
        System.out.println(book2);
        System.out.println();

        // Test getters
        System.out.println("=== Testing Getters on Book 1 ===");
        System.out.println("Title: " + book1.getTitle());
        System.out.println("Author: " + book1.getAuthor());
        System.out.println("ISBN: " + book1.getISBN());
        System.out.println("List Price: " + book1.getListPrice());
        System.out.println("Edition: " + book1.getEdition());
        System.out.println("Publisher: " + book1.getPublisher());
        System.out.println();

        // Test setter for listPrice with invalid value (should not update)
        System.out.println("=== Testing setListPrice with invalid value (-10) ===");
        book1.setListPrice(-10);
        System.out.println("List Price after setting -10: " + book1.getListPrice());
        System.out.println();

        // Test equals method - two different books
        System.out.println("=== Testing equals() ===");
        System.out.println("book1 equals book2: " + book1.equals(book2));

        // Test equals method - two identical books
        Book book3 = new Book("Java Programming", "John Smith", "978-0-13-468599-1", 89, 3, "Pearson");
        System.out.println("book1 equals book3: " + book1.equals(book3));

        // Test equals method - same reference
        System.out.println("book1 equals itself: " + book1.equals(book1));

        // Test equals method - null comparison
        System.out.println("book1 equals null: " + book1.equals(null));
    }
}
