public final class Book {
    private String title;
    private long isbn;

    public Book(String title, long isbn) {
        if (!isIsbnValid(isbn)) {
            throw new InvalidIsbnException(isbn);
        }
        this.title = title;
        this.isbn = isbn;
    }

    public String getTitle() { return this.title; }
    public long getIsbn() { return this.isbn; }

    private boolean isIsbnValid(long isbn) { ... }
}
