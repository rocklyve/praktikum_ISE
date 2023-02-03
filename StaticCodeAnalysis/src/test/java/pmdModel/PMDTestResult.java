package pmdModel;

import java.util.ArrayList;
import java.util.List;

public class PMDTestResult {
    public String formatVersion;
    public String pmdVersion;
    public String timestamp;
    public List<String> suppressedViolations;
    public List<String> processingErrors;
    public List<String> configurationErrors;
    public List<PMDTestResultFile> files = new ArrayList<>();
}

