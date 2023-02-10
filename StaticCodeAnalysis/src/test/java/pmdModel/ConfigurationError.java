package pmdModel;

public record ConfigurationError(
        String rule,
        String ruleset,
        String message
) {}