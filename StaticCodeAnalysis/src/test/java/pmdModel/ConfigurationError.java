package pmdModel;

/**
 * A record that represents a configuration error that occurred during a PMD test run.
 * A configuration error contains information about the rule and ruleset that caused the error, as well as a message
 * describing the error.
 * @param rule a string representing the name of the rule that caused the configuration error
 * @param ruleset a string representing the name of the ruleset the rule belongs to
 * @param message a string describing the configuration error that occurred
 */
public record ConfigurationError(
        String rule,
        String ruleset,
        String message
) {}