package edu.kit.informatik.listings;

class FinalP implements Comparable<Account> {
    final int id;

    public FinalP(int id) {
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