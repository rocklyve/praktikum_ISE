package pmdModel;

import java.util.ArrayList;
import java.util.List;

public record PMDTestResult(
        String formatVersion,
        String pmdVersion,
        String timestamp,
        List<String> suppressedViolations,
        List<ConfigurationError> configurationErrors,
        List<String> processingErrors,
        List<PMDTestResultFile> files
) {}

