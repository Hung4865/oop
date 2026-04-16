package bms.product;


public class Book extends Product {

    private String author;
    private String publisher;
    private int publicationYear;
    private String genre;
    private String language;

    public Book(String author, String publisher, int publicationYear, String genre, String language,
                String id, String name, double costPrice, double salePrice, int quantity, String unit, String origin) {
        super(id, name, costPrice, salePrice, quantity, unit, origin);
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.language = language;
    }

    // --- GETTERS & SETTERS ---
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    
    // getId(), getName(), getCostPrice(), getSalePrice(), getQuantity(), getUnit(), getOrigin()
}