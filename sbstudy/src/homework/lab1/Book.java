package homework.lab1;

public class Book {

    // Instance variables
    private String title;
    private String author;
    private String ISBN;
    private int listPrice;
    private int edition;
    private String publisher;

    /**
     *initializes a Book
     */
    public Book() {
        this.title = "";
        this.author = "";
        this.ISBN = "";
        this.listPrice = 0;
        this.edition = 0;
        this.publisher = "";
    }


    public Book(String title, String author, String ISBN, int listPrice, int edition, String publisher) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.listPrice = listPrice;
        this.edition = edition;
        this.publisher = publisher;
    }

    // Getters and Setters

    /** Gets the title of the book.*/
    public String getTitle() {
        return title;
    }

    /**Sets the title of the book.*/
    public void setTitle(String title) {
        this.title = title;
    }

    /** Gets the author of the book.*/
    public String getAuthor() {
        return author;
    }

    /** Sets the author of the book.*/
    public void setAuthor(String author) {
        this.author = author;
    }

    /** Gets the ISBN of the book.*/
    public String getISBN() {
        return ISBN;
    }

    /** Sets the ISBN of the book.*/
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /** Gets the list price of the book.*/
    public int getListPrice() {
        return listPrice;
    }

    /** Sets the list price of the book.*/
    public void setListPrice(int listPrice) {
        if (listPrice > 0) {
            this.listPrice = listPrice;
        }
    }

    /** Gets the edition of the book.*/
    public int getEdition() {
        return edition;
    }

    /** Sets the edition of the book.*/
    public void setEdition(int edition) {
        this.edition = edition;
    }

    /** Gets the publisher of the book.*/
    public String getPublisher() {
        return publisher;
    }

    /** Sets the publisher of the book.*/
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", listPrice=" + listPrice +
                ", edition=" + edition +
                ", publisher='" + publisher + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        // Check if both references point to the same object
        if (this == obj) {
            return true;
        }
        // Check if the other object is null or not a Book
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // Cast the object to Book type
        Book other = (Book) obj;
        // Compare all instance variables
        return listPrice == other.listPrice
                && edition == other.edition
                && title.equals(other.title)
                && author.equals(other.author)
                && ISBN.equals(other.ISBN)
                && publisher.equals(other.publisher);
    }
}
