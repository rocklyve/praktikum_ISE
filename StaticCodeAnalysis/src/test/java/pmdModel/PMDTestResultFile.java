package pmdModel;

import java.util.List;

public record PMDTestResultFile(String filename, List<PMDTestViolation> violations) {}


