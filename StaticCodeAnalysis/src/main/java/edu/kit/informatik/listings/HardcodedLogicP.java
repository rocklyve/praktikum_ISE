package edu.kit.informatik.listings;

public class HardcodedLogicP {
    public static int clipPercentGood(final int percent) {
        return clip(0, 100, percent);
    }

    public static int clip(final int min, final int max, final int value) {
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        }
        return value;
    }
}