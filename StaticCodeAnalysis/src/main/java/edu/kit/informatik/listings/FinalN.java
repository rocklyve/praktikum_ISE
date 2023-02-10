package edu.kit.informatik.listings;

class Account implements Comparable<Account> {
    int id;

    public Account(int id) {
        this.id = id;
    }

    public int getIdentifier() {
        return id;
    }

    @Override
    public int compareTo(Account other) {
        return Integer.compare(id, other.id);
    }
}
