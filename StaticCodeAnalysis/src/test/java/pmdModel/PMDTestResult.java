package pmdModel;

import java.util.ArrayList;

public class PMDTestResult {
    public String formatVersion;
    public String pmdVersion;
    public String timeStamp;
    public ArrayList<PMDTestResultFile> files = new ArrayList<PMDTestResultFile>();
}
