public record Pair(Date first, Date second) {
    @Override public boolean equals(Object o) { // Exception: equals requires Object
        ...
    }
}

public void scheduleEvents(Date startTime, Date endTime) {
    Pair timeSpan = new Pair(startTime, endTime);
   
    Date now = new Date(System.currentTimeMillis());
    if(timeSpan.first().before(event) && timeSpan.second().before(event)) {
        System.out.println("Event within timespan");
    }
}