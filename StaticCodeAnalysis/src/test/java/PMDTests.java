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
    private static final String pmdRuleSetFilePathJ2ee = "rulesets/java/j2ee.xml";
    private static final String pmdRuleSetFilePathSunsecure = "rulesets/java/sunsecure.xml";
    private static final String pmdRuleSetFilePathDesign = "rulesets/java/design.xml";
    private static final String pmdRuleSetFilePathFinalizers = "rulesets/java/finalizers.xml";
    private static final String pmdRuleSetFilePathControversial = "rulesets/java/controversial.xml";
    private static final String pmdRuleSetFilePathUnnecessary = "rulesets/java/unnecessary.xml";
    private static final String pmdRuleSetFilePathLogging = "rulesets/java/logging-jakarta-commons.xml";
    private static final String pmdRuleSetFilePathQuickstart = "rulesets/java/quickstart.xml";
    private static final String pmdRuleSetFilePathJavabeans = "rulesets/java/javabeans.xml";
    private static final String pmdRuleSetFilePathOptimizations = "rulesets/java/optimizations.xml";
    private static final String pmdRuleSetFilePathStrings = "rulesets/java/strings.xml";
    private static final String pmdRuleSetFilePathStrictException = "rulesets/java/strictexception.xml";
    private static final String pmdRuleSetFilePathImports = "rulesets/java/imports.xml";
    private static final String pmdRuleSetFilePathJunit = "rulesets/java/junit.xml";
    private static final String pmdRuleSetFilePathCodeSize = "rulesets/java/codesize.xml";
    private static final String pmdRuleSetFilePathBraces = "rulesets/java/braces.xml";
    private static final String pmdRuleSetFilePathCoupling = "rulesets/java/coupling.xml";
    private static final String pmdRuleSetFilePathNaming = "rulesets/java/naming.xml";
    private static final String pmdRuleSetFilePathClone = "rulesets/java/clone.xml";
    private static final String pmdRuleSetFilePathLoggingJava= "rulesets/java/logging-java.xml";
    private static final String pmdRuleSetFilePathTypeResolution = "rulesets/java/typeresolution.xml";
    private static final String pmdRuleSetFilePathMetrics = "rulesets/java/metrics.xml";
    private static final String pmdRuleSetFilePathUnusedCode = "rulesets/java/unusedcode.xml";
    private static final String pmdRuleSetFilePathComments = "rulesets/java/comments.xml";
    private static final String pmdRuleSetFilePathBasic = "rulesets/java/basic.xml";
    private static final String pmdRuleSetFilePathEmpty = "rulesets/java/empty.xml";
    private static final String pmdRuleSetFilePathCategoryCodeStyle = "category/java/codestyle.xml";
    private static final String pmdRuleSetFilePathCategoryBestPractices = "category/java/bestpractices.xml";
    private static final String pmdRuleSetFilePathCategoryDocumentation = "category/java/documentation.xml";
    private static final String pmdRuleSetFilePathCategoryErrorProne = "category/java/errorprone.xml";
    private static final String pmdRuleSetFilePathCategoryMultiThreading = "category/java/multithreading.xml";
    private static final String pmdRuleSetFilePathCategoryPerformance = "category/java/performance.xml";
    private static final String pmdRuleSetFilePathCategorySecurity = "category/java/security.xml";
//    private static final String pmdRuleSetFilePathJavaFull = "rulesets/java/maven-pmd-plugin-default.xml";
    private static final String pmdReportFilePath = "src/resources/pmd-report.json";
    private static final String pmdReportFileFormat = "json";

    static PMDTestResult issues;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        PMDConfiguration configuration = new PMDConfiguration();
        configuration.setInputPaths(pmdReportInputFilePath);

        configuration.setRuleSets(List.of(
                pmdRuleSetFilePathQuickstart,
                pmdReportInputFilePath,
                pmdRuleSetFilePathJ2ee,
                pmdRuleSetFilePathSunsecure,
                pmdRuleSetFilePathDesign,
                pmdRuleSetFilePathFinalizers,
                pmdRuleSetFilePathControversial,
                pmdRuleSetFilePathUnnecessary,
                pmdRuleSetFilePathLogging,
                pmdRuleSetFilePathQuickstart,
                pmdRuleSetFilePathJavabeans,
                pmdRuleSetFilePathOptimizations,
                pmdRuleSetFilePathStrings,
                pmdRuleSetFilePathStrictException,
                pmdRuleSetFilePathImports,
                pmdRuleSetFilePathJunit,
                pmdRuleSetFilePathCodeSize,
                pmdRuleSetFilePathBraces,
// this rule currently creates configuration errors we are not able to handle right now
//                pmdRuleSetFilePathCoupling,
                pmdRuleSetFilePathNaming,
                pmdRuleSetFilePathClone,
                pmdRuleSetFilePathLoggingJava,
                pmdRuleSetFilePathTypeResolution,
                pmdRuleSetFilePathMetrics,
                pmdRuleSetFilePathUnusedCode,
// TODO: too much issues, comment out when finished with other rules
//                pmdRuleSetFilePathComments,
                pmdRuleSetFilePathBasic,
                pmdRuleSetFilePathEmpty,
                pmdRuleSetFilePathCategoryCodeStyle,
                pmdRuleSetFilePathCategoryBestPractices,
                pmdRuleSetFilePathCategoryDocumentation,
                pmdRuleSetFilePathCategoryErrorProne,
                pmdRuleSetFilePathCategoryMultiThreading,
                pmdRuleSetFilePathCategoryPerformance,
                pmdRuleSetFilePathCategorySecurity
        ));

        configuration.setReportFormat(pmdReportFileFormat);
        configuration.setReportFile(pmdReportFilePath);

        PMD.runPmd(configuration);

        ObjectMapper objectMapper = new ObjectMapper();
        issues = objectMapper.readValue(new File(pmdReportFilePath), PMDTestResult.class);
        issues.files.stream().map(file -> filterDuplicatedViolations(file)).collect(Collectors.toList());
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

    private List<PMDTestResultFile> findOccurringIssues(ArrayList<String> relevantRules) {
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

    static PMDTestResultFile filterDuplicatedViolations(PMDTestResultFile file) {
        List<PMDTestViolation> filteredList = file.violations.stream().distinct().collect(Collectors.toList());
        file.violations = filteredList;
        return file;
    }

    @AfterAll
    public static void tearDownAfterClass() {
        System.out.println("over");
    }
}