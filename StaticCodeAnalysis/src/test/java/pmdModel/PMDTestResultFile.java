package pmdModel;

import java.util.Set;

/**
 * A record that represents a PMD test result file.
 * A PMD test result file has a filename and a set of violations that were detected in the file.
 * @param filename a string representing the name of the file
 * @param violations a set of {@link PMDTestViolation} objects representing the violations detected in the file
 */
public record PMDTestResultFile(String filename, Set<PMDTestViolation> violations) {}