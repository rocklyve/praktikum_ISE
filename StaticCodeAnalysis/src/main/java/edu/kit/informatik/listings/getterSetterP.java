package edu.kit.informatik.listings;

import java.util.List;

public class getterSetterP {
    int[] array;
    List<String> list;

    public void setSomeArray(int[] array) {
        this.array = new int[array.length];
        System.arraycopy(array, 0, this.array, 0, array.length);
    }

    public int[] getSomeArray() {
        int[] copy = new int[this.array.length];
        System.arraycopy(this.array, 0, copy, 0, copy.length);
        return copy;
    }

    public void addSomeElement(String element) {
        this.list.add(element);
    }
}