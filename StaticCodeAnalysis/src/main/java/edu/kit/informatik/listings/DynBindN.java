//package edu.kit.informatik.listings;
//
//public class BaseMatrix {
//    public String getSerializedStringForBase() {}
//}
//public class FastMatrix extends BaseMatrix { public String getSerializedStringForFast() {} }
//
//public class Main {
//    public String getSerializedString(Matrix matrix) {
//        String data = null;
//        if (matrix instanceof FastMatrix fastMatrix) {
//            data = fastMatrix.getSerializedStringForFast();
//        } else if (matrix instanceof BaseMatrix baseMatrix) {
//            data = baseMatrix.getSerializedStringForBase();
//        }
//        return data;
//    }
//}