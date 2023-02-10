package pmdModel;

public record PMDTestViolation (
     int beginline,
     int begincolumn,
     int endline,
     int endcolumn,
     String description,
     String rule,
     String ruleset,
     int priority,
     String externalInfoUrl
){}