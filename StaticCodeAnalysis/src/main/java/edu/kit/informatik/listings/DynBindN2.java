package edu.kit.informatik.listings;

public class BaseMatrix {
    public String getSerializedStringForBase() {}
}
public class FastMatrix extends BaseMatrix { public String getSerializedStringForFast() {} }

public class Main {
    public String getSerializedString(Matrix matrix) {
        String data = null;
        if (matrix.getClass() == FastMatrix.class) {
            data = ((FastMatrix) matrix).getSerializedStringForFast();
        } else if (BaseMatrix.class.isAssignableFrom(matrix.getClass())) {
            data = ((BaseMatrix) matrix).getSerializedStringForBase();
        }
        return data;
    }
}