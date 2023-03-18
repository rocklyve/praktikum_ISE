package pmdModel;

import java.util.List;

/**
 * A record that represents the results of a PMD test run.
 * The PMD test results consist of the PMD version and format version used, a timestamp of when the test was run,
 * lists of suppressed violations, configuration errors, processing errors, and a list of PMD test result files.
 * @param formatVersion a string representing the PMD report format version
 * @param pmdVersion a string representing the version of PMD used for the test run
 * @param timestamp a string representing the timestamp when the test was run
 * @param suppressedViolations a list of strings representing the suppressed violations during the test run
 * @param configurationErrors a list of {@link ConfigurationError} objects representing any configuration errors that occurred during the test run
 * @param processingErrors a list of strings representing any processing errors that occurred during the test run
 * @param files a list of {@link PMDTestResultFile} objects representing the PMD test result files
 */
public record PMDTestResult(
        String formatVersion,
        String pmdVersion,
        String timestamp,
        List<String> suppressedViolations,
        List<ConfigurationError> configurationErrors,
        List<String> processingErrors,
        List<PMDTestResultFile> files
) {}

