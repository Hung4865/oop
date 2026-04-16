package bms.product;

public class Textbook extends Book {
    private String subject;
    private int grade;
    private String eduLevel;

    public Textbook(String author, String publisher, int publicationYear, String genre, String language,
                    String subject, int grade, String eduLevel,
                    String id, String name, double costPrice, double salePrice, int quantity, String unit, String origin) {
        super(author, publisher, publicationYear, genre, language, id, name, costPrice, salePrice, quantity, unit, origin);
        this.subject = subject;
        this.grade = grade;
        this.eduLevel = eduLevel;
    }

    // --- GETTERS & SETTERS ---
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }

    public String getEduLevel() { return eduLevel; }
    public void setEduLevel(String eduLevel) { this.eduLevel = eduLevel; }
}