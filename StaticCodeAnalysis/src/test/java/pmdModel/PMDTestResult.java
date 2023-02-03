package pmdModel;

import java.util.ArrayList;
import java.util.List;

public record PMDTestResult(
        String formatVersion,
        String pmdVersion,
        String timestamp,
        List<String> suppressedViolations,
        List<String> configurationErrors,
        List<PMDTestResultFile> files
) {}

