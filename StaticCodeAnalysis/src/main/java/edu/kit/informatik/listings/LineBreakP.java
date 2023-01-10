package edu.kit.informatik.listings;

public class LineBreakP {
    public static void main(String[] args) {
        System.out.println("This is a multi line" + System.lineSeparator() + "Text");

        System.out.println("This is a multi line");
        System.out.println("Text");
        // the upcoming line will not be valid while building...
//        System.out.println("""This is a multi line
//                Text""");
    }
}
