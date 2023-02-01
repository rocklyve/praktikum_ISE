import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import pmdModel.PMDTestResult;
import pmdModel.PMDTestResultFile;
import pmdModel.PMDTestViolation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;

public class PMDTests {
    public static final String newLine = System.lineSeparator();
    private static final String pmdReportInputFilePath = "src/main/java/edu/kit/informatik/";
    private static final String pmdRuleSetFilePath = "rulesets/java/quickstart.xml";
    private static final String pmdReportFilePath = "src/resources/pmd-report.json";
    private static final String pmdReportFileFormat = "json";
    static PMDTestResult issues;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        PMDConfiguration configuration = new PMDConfiguration();
        configuration.setInputPaths(pmdReportInputFilePath);
        configuration.addRuleSet(pmdRuleSetFilePath);
        configuration.setReportFormat(pmdReportFileFormat);
        configuration.setReportFile(pmdReportFilePath);

        PMD.runPmd(configuration);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = new String(Files.readAllBytes(Paths.get(pmdReportFilePath)));
        issues = objectMapper.readValue(jsonString, PMDTestResult.class);
    }

//    @DisplayName("Test NonFinalAttributesShouldBeFinal")
//    @Test
//    void testNonFinalAttributesShouldBeFinal() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField", "UncommentedEmptyConstructor"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test SystemDependentLineBreak")
//    @Test
//    void testSystemDependentLineBreak() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test RawType")
//    @Test
//    void testRawType() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }

    @DisplayName("Test ConcreteClassInsteadOfInterface")
    @Test
    void testConcreteClassInsteadOfInterface() {
        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("LooseCoupling"));
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

//    @DisplayName("Test AssertInsteadOfIfLoop")
//    @Test
//    void testAssertInsteadOfIfLoop() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ObjectInsteadOfConcreteClass")
//    @Test
//    void testObjectInsteadOfConcreteClass() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test PublicEnumInsideClassAndNotInSeparateFile")
//    @Test
//    void testPublicEnumInsideClassAndNotInSeparateFile() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test VisibilityAsLowAsPossible")
//    @Test
//    void testVisibilityAsLowAsPossible() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test Code Duplication")
//    @Test
//    void testCodeDuplication() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test CodeDuplicationRepetitionsFixableByInheritance")
//    @Test
//    void testCodeDuplicationRepetitionsFixableByInheritance() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test InheritanceInsteadOfEnums")
//    @Test
//    void testInheritanceInsteadOfEnums() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test OperationsInsteadOfDomain")
//    @Test
//    void testOperationsInsteadOfDomain() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test HardcodedLogic")
//    @Test
//    void testHardcodedLogic() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test StringReferences")
//    @Test
//    void testStringReferences() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }

    @DisplayName("Test ExceptionsForControlFlow")
    @Test
    void testExceptionsForControlFlow() {
        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("EmptyCatchBlock"));
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

//    @DisplayName("Test TryCatchBlock")
//    @Test
//    void testTryCatchBlock() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test UnspecifiedErrorMessage")
//    @Test
//    void testUnspecifiedErrorMessage() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//
//    @DisplayName("Test WrongLoopType")
//    @Test
//    void testWrongLoopType() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//
//    @DisplayName("Test UnnecessaryComplexity")
//    @Test
//    void testUnnecessaryComplexity() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ClumsySolution")
//    @Test
//    void testClumsySolution() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ParsingIntegerValues")
//    @Test
//    void testParsingIntegerValues() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }

    @DisplayName("Test UtilityClass")
    @Test
    void testUtilityClass() {
        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UseUtilityClass"));
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

//    @DisplayName("Test UnsafeCast")
//    @Test
//    void testUnsafeCast() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }

    @DisplayName("Test EmptyConstructor")
    @Test
    void testEmptyConstructor() {
        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UncommentedEmptyConstructor", "UnnecessaryConstructor"));
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

//    @DisplayName("Test MeaninglessConstant")
//    @Test
//    void testMeaninglessConstant() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test Scanner")
//    @Test
//    void testScanner() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }

    @DisplayName("Test UnusedElement")
    @Test
    void testUnusedElement() {
        ArrayList<String> relevantRules = new ArrayList<String>(
                Arrays.asList("UnusedPrivateField", "UnusedPrivateMethod", "UnusedLocalVariable")
        );
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

//    @DisplayName("Test MissingThrowsInMethodSignature")
//    @Test
//    void testMissingThrowsInMethodSignature() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test PublicEnumInClass")
//    @Test
//    void testPublicEnumInClass() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ParsingIntegerValues")
//    @Test
//    void testClassOfConstants() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test TrivialJavaDoc")
//    @Test
//    void testTrivialJavaDoc() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test BadNaming")
//    @Test
//    void testBadNaming() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test DataEncapsulationViolation")
//    @Test
//    void testDataEncapsulationViolation() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test SeparationOfLogicAndInteraction")
//    @Test
//    void testSeparationOfLogicAndInteraction() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test TooComplexCode")
//    @Test
//    void testTooComplexCode() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test StaticMethods")
//    @Test
//    void testStaticMethods() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test StaticAttributeOfInstanceAttribute")
//    @Test
//    void testStaticAttributeOfInstanceAttribute() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test FinalModifier")
//    @Test
//    void testFinalModifier() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test AssertVsIf")
//    @Test
//    void testAssertVsIf() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ParsingIntegerValues")
//    @Test
//    void testJavaAPI() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ToStringVsEquals")
//    @Test
//    void testToStringVsEquals() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test DoNotUseObject")
//    @Test
//    void testDoNotUseObject() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ClassInsteadOfInterface")
//    @Test
//    void testClassInsteadOfInterface() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test EnumForClosedSet")
//    @Test
//    void testEnumForClosedSet() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test EmptyBlock")
//    @Test
//    void testEmptyBlock() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test PackageUsage")
//    @Test
//    void testPackageUsage() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test DynamicBinding")
//    @Test
//    void testDynamicBinding() {
//        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }

    private static void checkOccurringIssues(List<PMDTestResultFile> occurringIssues) {
        if (occurringIssues.isEmpty()) {
            return;
        }
        String mergedMessage = newLine;
        for (PMDTestResultFile file : occurringIssues) {
            for (PMDTestViolation violation : file.violations) {
                mergedMessage +=
                        "Issue: " + violation.rule + " with message: " + violation.description +
                                " File: " + file.filename + ", Line: " + violation.beginline + " " + newLine;
                // System.lineSeparator() or formatted string usage %n with linebreaks
                // or \n
                // TODO: file path not absolute!
            }
        }

        String finalMergedMessage = "Found " + occurringIssues.size()+ " issues: full text: " + mergedMessage;
        // assert fail outside of
        Assert.fail(finalMergedMessage);
    }

    private List<PMDTestResultFile> findOccurringIssues(ArrayList<String> relevantRules) {
        List<PMDTestResultFile> filteredResultFiles = issues.files;
        // remove irrelevant violations
        for (PMDTestResultFile file: filteredResultFiles) {
            file.violations = file.violations.stream().filter(violation -> relevantRules.contains(violation.rule)).collect(Collectors.toList());
        }
        // remove files with empty violation lists
        return filteredResultFiles.stream().filter(file -> !file.violations.isEmpty()).collect(Collectors.toList());
    }

    @AfterAll
    public static void tearDownAfterClass() {
        System.out.println("over");
    }
}