public record Pair(Object first, Object second) {
    @Override public boolean equals(Object o) {
        ...
    }
}

public void scheduleEvents(Object startTime, Object endTime) {
    Pair timeSpan = new Pair(startTime, endTime);
   
    Date now = new Date(System.currentTimeMillis());
    if(((Date) timeSpan.first()).before(event) && ((Date) timeSpan.second()).before(event)) {
        System.out.println("Event within timespan");
    }
}