public final class Book {
    public String title;
    long isbn;

    protected Book(String title, long isbn) {
        if (!isIsbnValid(isbn)) {
            throw new InvalidIsbnException(isbn);
        }
        this.title = title;
        this.isbn = isbn;
    }
    public boolean isIsbnValid(long isbn) { // isIsbnValid is only an internal helper method
        // ...
    }
}
