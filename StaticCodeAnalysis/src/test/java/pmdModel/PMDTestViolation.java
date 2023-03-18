package pmdModel;

/**
 * A record that represents a PMD test violation.
 * A PMD test violation has information about the location of the violation in the code, a description of the violation,
 * the rule that was violated, the ruleset the rule belongs to, the priority of the rule, and an external info URL
 * for additional information about the rule.
 * @param beginline an integer representing the line number where the violation starts
 * @param begincolumn an integer representing the column number where the violation starts
 * @param endline an integer representing the line number where the violation ends
 * @param endcolumn an integer representing the column number where the violation ends
 * @param description a string representing the description of the violation
 * @param rule a string representing the name of the rule that was violated
 * @param ruleset a string representing the name of the ruleset the violated rule belongs to
 * @param priority an integer representing the priority of the violated rule
 * @param externalInfoUrl a string representing an external URL with additional information about the violated rule
 */
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
