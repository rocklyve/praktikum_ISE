package edu.kit.informatik.listings;
public final class publicprivateP {
    private String title;
    private long isbn;

    public publicprivateP(String title, long isbn) {
        if (!isIsbnValid(isbn)) {
            throw new ClassCastException();
        }
        this.title = title;
        this.isbn = isbn;
    }

    public String getTitle() { return this.title; }
    public long getIsbn() { return this.isbn; }

    private boolean isIsbnValid(long isbn) { return true; }
}
