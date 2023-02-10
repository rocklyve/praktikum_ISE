package edu.kit.informatik.listings;

public class HardcodedLogicN {
    public static int clipPercentBad(final int percent) {
        if (percent < 0) {
            return 0;
        } else if (percent > 100) {
            return 100;
        }
        return percent;
    }
}