package edu.kit.informatik.listings;

import java.util.ArrayList;
import java.util.List;

class Event {
    private static final int BASE_FEE = 1000;                       // constants should be final
    private final List<String> participants;                        // fields should be final if possible

    public Event() {
        participants = new ArrayList<>();
    }

    public void addParticipant(String name) {                       // not required for parameters
        participants.add(name);                                     // (list is final, but not the content)
    }

    public int calculateProfit(int ticketPrice, int venueCost) {    // not required for parameters
        int fixedCost = BASE_FEE + venueCost;                       // not required for local variables
        return participants.size() * ticketPrice - fixedCost;
    }
}