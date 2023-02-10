package edu.kit.informatik;

import java.util.Date;
import java.util.List;

public class CodeDuplicationBadExample {
    void caluclation(List<Date> input, int low, int high) {
        if (high - low < 1) {
            return;
        }
        int mid = (low + high) / 2;

        String string = "4";
        int value = Integer.parseInt(string);
        String string2 = "asdf";
        int value2 = Integer.parseInt(string2);
    }

    void caluclation2(List<Date> input, int low, int high) {
        if (high - low < 1) {
            return;
        }
        int mid = (low + high) / 2;

        String string = "4";
        int value = Integer.parseInt(string);
        String string2 = "asdf";
        int value2 = Integer.parseInt(string2);
    }
}
