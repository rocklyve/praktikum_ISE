package pmdModel;

import java.util.Set;

public record PMDTestResultFile(String filename, Set<PMDTestViolation> violations) {}