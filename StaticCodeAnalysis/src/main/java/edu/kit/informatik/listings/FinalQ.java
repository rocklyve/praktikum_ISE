package edu.kit.informatik.listings;

class Event {
    private static int BASE_FEE = 1000;
    private List<String> participants;

    public Event() {
        participants = new ArrayList<>();
    }

    public void addParticipant(String name) {
        participants.add(name);
    }

    public int calculateProfit(int ticketPrice, int venueCost) {
        int fixedCost = BASE_FEE + venueCost;
        return participants.size() * ticketPrice - fixedCost;
    }
}