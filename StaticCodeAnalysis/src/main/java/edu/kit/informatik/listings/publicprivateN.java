package edu.kit.informatik.listings;

public final class publicprivateN {
    public String title;
    long isbn;

    protected publicprivateN(String title, long isbn) {
        if (!isIsbnValid(isbn)) {
            throw new ClassCastException();
        }
        this.title = title;
        this.isbn = isbn;
    }
    public boolean isIsbnValid(long isbn) { // isIsbnValid is only an internal helper method
        // ...
        return true;
    }
}
