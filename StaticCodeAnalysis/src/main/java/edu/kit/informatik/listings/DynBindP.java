package edu.kit.informatik.listings;


public class BaseMatrix {
    public String getSerializedString() {}
}
public class FastMatrix extends BaseMatrix {
    @Override
    public String getSerializedString() {}
}

public class Main {
    public static void main(String[] args) {
        BaseMatrix matrix = new FastMatrix();
        // Use Dynamic Binding :)
        var data = matrix.getSerializedString();
    }
}