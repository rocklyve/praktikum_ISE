import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import pmdModel.PMDTestResult;
import pmdModel.PMDTestResultFile;
import pmdModel.PMDTestViolation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
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
        issues = objectMapper.readValue(new File(pmdReportFilePath), PMDTestResult.class);
        int countViolations = 0;
        for (PMDTestResultFile file: issues.files) {
            countViolations += file.violations.size();
        }
        System.out.println("CountViolations: " + countViolations);
    }

//    @DisplayName("Test NonFinalAttributesShouldBeFinal")
//    @Test
//    void testNonFinalAttributesShouldBeFinal() {
//        List<String> relevantRules = List.of("UnusedPrivateField", "UncommentedEmptyConstructor"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test SystemDependentLineBreak")
//    @Test
//    void testSystemDependentLineBreak() {
//        List<String> relevantRules = List.of("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test RawType")
//    @Test
//    void testRawType() {
//        List<String> relevantRules = List.of("UnusedPrivateField"));
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }

    @DisplayName("Test ConcreteClassInsteadOfInterface")
    @Test
    void testConcreteClassInsteadOfInterface() {
//        List<String> relevantRules = List.of("LooseCoupling"));
        List<String> relevantRules = List.of("LooseCoupling");
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

//    @DisplayName("Test AssertInsteadOfIfLoop")
//    @Test
//    void testAssertInsteadOfIfLoop() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ObjectInsteadOfConcreteClass")
//    @Test
//    void testObjectInsteadOfConcreteClass() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test PublicEnumInsideClassAndNotInSeparateFile")
//    @Test
//    void testPublicEnumInsideClassAndNotInSeparateFile() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test VisibilityAsLowAsPossible")
//    @Test
//    void testVisibilityAsLowAsPossible() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test Code Duplication")
//    @Test
//    void testCodeDuplication() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test CodeDuplicationRepetitionsFixableByInheritance")
//    @Test
//    void testCodeDuplicationRepetitionsFixableByInheritance() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test InheritanceInsteadOfEnums")
//    @Test
//    void testInheritanceInsteadOfEnums() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test OperationsInsteadOfDomain")
//    @Test
//    void testOperationsInsteadOfDomain() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test HardcodedLogic")
//    @Test
//    void testHardcodedLogic() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test StringReferences")
//    @Test
//    void testStringReferences() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }

    @DisplayName("Test ExceptionsForControlFlow")
    @Test
    void testExceptionsForControlFlow() {
        List<String> relevantRules = List.of("EmptyCatchBlock");
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

//    @DisplayName("Test TryCatchBlock")
//    @Test
//    void testTryCatchBlock() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test UnspecifiedErrorMessage")
//    @Test
//    void testUnspecifiedErrorMessage() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//
//    @DisplayName("Test WrongLoopType")
//    @Test
//    void testWrongLoopType() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//
//    @DisplayName("Test UnnecessaryComplexity")
//    @Test
//    void testUnnecessaryComplexity() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ClumsySolution")
//    @Test
//    void testClumsySolution() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ParsingIntegerValues")
//    @Test
//    void testParsingIntegerValues() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }

    @DisplayName("Test UtilityClass")
    @Test
    void testUtilityClass() {
        List<String> relevantRules = List.of("UseUtilityClass");
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

//    @DisplayName("Test UnsafeCast")
//    @Test
//    void testUnsafeCast() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }

    @DisplayName("Test EmptyConstructor")
    @Test
    void testEmptyConstructor() {
        List<String> relevantRules = List.of("UncommentedEmptyConstructor", "UnnecessaryConstructor");
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

//    @DisplayName("Test MeaninglessConstant")
//    @Test
//    void testMeaninglessConstant() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test Scanner")
//    @Test
//    void testScanner() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }

    @DisplayName("Test UnusedElement")
    @Test
    void testUnusedElement() {
        List<String> relevantRules = new ArrayList<String>(
                Arrays.asList("UnusedPrivateField", "UnusedPrivateMethod", "UnusedLocalVariable")
        );
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

//    @DisplayName("Test MissingThrowsInMethodSignature")
//    @Test
//    void testMissingThrowsInMethodSignature() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test PublicEnumInClass")
//    @Test
//    void testPublicEnumInClass() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ParsingIntegerValues")
//    @Test
//    void testClassOfConstants() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test TrivialJavaDoc")
//    @Test
//    void testTrivialJavaDoc() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test BadNaming")
//    @Test
//    void testBadNaming() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test DataEncapsulationViolation")
//    @Test
//    void testDataEncapsulationViolation() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test SeparationOfLogicAndInteraction")
//    @Test
//    void testSeparationOfLogicAndInteraction() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test TooComplexCode")
//    @Test
//    void testTooComplexCode() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test StaticMethods")
//    @Test
//    void testStaticMethods() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test StaticAttributeOfInstanceAttribute")
//    @Test
//    void testStaticAttributeOfInstanceAttribute() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test FinalModifier")
//    @Test
//    void testFinalModifier() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test AssertVsIf")
//    @Test
//    void testAssertVsIf() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ParsingIntegerValues")
//    @Test
//    void testJavaAPI() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ToStringVsEquals")
//    @Test
//    void testToStringVsEquals() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test DoNotUseObject")
//    @Test
//    void testDoNotUseObject() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test ClassInsteadOfInterface")
//    @Test
//    void testClassInsteadOfInterface() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test EnumForClosedSet")
//    @Test
//    void testEnumForClosedSet() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test EmptyBlock")
//    @Test
//    void testEmptyBlock() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test PackageUsage")
//    @Test
//    void testPackageUsage() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }
//
//    @DisplayName("Test DynamicBinding")
//    @Test
//    void testDynamicBinding() {
//        List<String> relevantRules = List.of("UnusedPrivateField");
//        checkOccurringIssues(findOccurringIssues(relevantRules));
//    }

    private static void checkOccurringIssues(List<PMDTestResultFile> occurringIssues) {
        if (occurringIssues.isEmpty()) {
            return;
        }
        String mergedMessage = newLine;
        for (PMDTestResultFile file : occurringIssues) {
            for (PMDTestViolation violation : file.violations) {
                String fileName = file.filename.split(pmdReportInputFilePath)[1];
                mergedMessage +=
                        "Issue: " + violation.rule + " with message: " + violation.description +
                                " File: " + fileName + ", Line: " + violation.beginline + " " + newLine;
            }
        }

        String finalMergedMessage = "Found " + occurringIssues.size()+ " issues: full text: " + mergedMessage;
        // assert fail outside of
        Assert.fail(finalMergedMessage);
    }

    private List<PMDTestResultFile> findOccurringIssues(List<String> relevantRules) {
        // remove irrelevant violations
        return issues.files.stream()
                .filter(file -> file.violations.stream()
                        .anyMatch(violation -> relevantRules.contains(violation.rule)))
                .map(file -> {
                    PMDTestResultFile newFile = new PMDTestResultFile();
                    newFile.filename = file.filename;
                    newFile.violations = file.violations.stream()
                            .filter(violation -> relevantRules.contains(violation.rule))
                            .collect(Collectors.toList());
                    return newFile;
                })
                .filter(file -> !file.violations.isEmpty())
                .collect(Collectors.toList());
    }

    @AfterAll
    public static void tearDownAfterClass() {
        System.out.println("over");
    }
}
