package pmdModel;

import java.util.ArrayList;

public class PMDTestResult {
    public String formatVersion;
    public String pmdVersion;
    public String timestamp;
    public ArrayList<String> suppressedViolations;
    public ArrayList<String> processingErrors;
    public ArrayList<String> configurationErrors;
    public ArrayList<PMDTestResultFile> files = new ArrayList<PMDTestResultFile>();
}
