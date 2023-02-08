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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;

public class PMDTests {
    public static final String newLine = System.lineSeparator();
    private static final String PMD_REPORT_INPUT_FILE_PATH = "src/main/java/edu/kit/informatik/";
    private static final String PMD_RULE_SET_FILE_PATH_J_2_EE = "rulesets/java/j2ee.xml";
    private static final String PMD_RULE_SET_FILE_PATH_SUNSECURE = "rulesets/java/sunsecure.xml";
    private static final String PMD_RULE_SET_FILE_PATH_DESIGN = "rulesets/java/design.xml";
    private static final String PMD_RULE_SET_FILE_PATH_FINALIZERS = "rulesets/java/finalizers.xml";
    private static final String PMD_RULE_SET_FILE_PATH_CONTROVERSIAL = "rulesets/java/controversial.xml";
    private static final String PMD_RULE_SET_FILE_PATH_UNNECESSARY = "rulesets/java/unnecessary.xml";
    private static final String PMD_RULE_SET_FILE_PATH_LOGGING = "rulesets/java/logging-jakarta-commons.xml";
    private static final String PMD_RULE_SET_FILE_PATH_QUICKSTART = "rulesets/java/quickstart.xml";
    private static final String PMD_RULE_SET_FILE_PATH_JAVABEANS = "rulesets/java/javabeans.xml";
    private static final String PMD_RULE_SET_FILE_PATH_OPTIMIZATIONS = "rulesets/java/optimizations.xml";
    private static final String PMD_RULE_SET_FILE_PATH_STRINGS = "rulesets/java/strings.xml";
    private static final String PMD_RULE_SET_FILE_PATH_STRICT_EXCEPTION = "rulesets/java/strictexception.xml";
    private static final String PMD_RULE_SET_FILE_PATH_IMPORTS = "rulesets/java/imports.xml";
    private static final String PMD_RULE_SET_FILE_PATH_JUNIT = "rulesets/java/junit.xml";
    private static final String PMD_RULE_SET_FILE_PATH_CODE_SIZE = "rulesets/java/codesize.xml";
    private static final String PMD_RULE_SET_FILE_PATH_BRACES = "rulesets/java/braces.xml";
    private static final String PMD_RULE_SET_FILE_PATH_COUPLING = "rulesets/java/coupling.xml";
    private static final String PMD_RULE_SET_FILE_PATH_NAMING = "rulesets/java/naming.xml";
    private static final String PMD_RULE_SET_FILE_PATH_CLONE = "rulesets/java/clone.xml";
    private static final String PMD_RULE_SET_FILE_PATH_LOGGING_JAVA = "rulesets/java/logging-java.xml";
    private static final String PMD_RULE_SET_FILE_PATH_TYPE_RESOLUTION = "rulesets/java/typeresolution.xml";
    private static final String PMD_RULE_SET_FILE_PATH_METRICS = "rulesets/java/metrics.xml";
    private static final String PMD_RULE_SET_FILE_PATH_UNUSED_CODE = "rulesets/java/unusedcode.xml";
    private static final String PMD_RULE_SET_FILE_PATH_COMMENTS = "rulesets/java/comments.xml";
    private static final String PMD_RULE_SET_FILE_PATH_BASIC = "rulesets/java/basic.xml";
    private static final String PMD_RULE_SET_FILE_PATH_EMPTY = "rulesets/java/empty.xml";
    private static final String PMD_RULE_SET_FILE_PATH_CATEGORY_CODE_STYLE = "category/java/codestyle.xml";
    private static final String PMD_RULE_SET_FILE_PATH_CATEGORY_BEST_PRACTICES = "category/java/bestpractices.xml";
    private static final String PMD_RULE_SET_FILE_PATH_CATEGORY_DOCUMENTATION = "category/java/documentation.xml";
    private static final String PMD_RULE_SET_FILE_PATH_CATEGORY_ERROR_PRONE = "category/java/errorprone.xml";
    private static final String PMD_RULE_SET_FILE_PATH_CATEGORY_MULTI_THREADING = "category/java/multithreading.xml";
    private static final String PMD_RULE_SET_FILE_PATH_CATEGORY_PERFORMANCE = "category/java/performance.xml";
    private static final String PMD_RULE_SET_FILE_PATH_CATEGORY_SECURITY = "category/java/security.xml";
//    private static final String PMD_RULE_SET_FILE_PATH_JAVA_FULL = "rulesets/java/maven-pmd-plugin-default.xml";
    private static final String PMD_REPORT_FILE_PATH = "src/resources/pmd-report.json";
    private static final String PMD_REPORT_FILE_FORMAT = "json";

    static PMDTestResult issues;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        PMDConfiguration configuration = new PMDConfiguration();
        configuration.setInputPaths(PMD_REPORT_INPUT_FILE_PATH);

        configuration.setRuleSets(List.of(
                PMD_RULE_SET_FILE_PATH_QUICKSTART,
                PMD_REPORT_INPUT_FILE_PATH,
                PMD_RULE_SET_FILE_PATH_J_2_EE,
                PMD_RULE_SET_FILE_PATH_SUNSECURE,
                PMD_RULE_SET_FILE_PATH_DESIGN,
                PMD_RULE_SET_FILE_PATH_FINALIZERS,
                PMD_RULE_SET_FILE_PATH_CONTROVERSIAL,
                PMD_RULE_SET_FILE_PATH_UNNECESSARY,
                PMD_RULE_SET_FILE_PATH_LOGGING,
                PMD_RULE_SET_FILE_PATH_QUICKSTART,
                PMD_RULE_SET_FILE_PATH_JAVABEANS,
                PMD_RULE_SET_FILE_PATH_OPTIMIZATIONS,
                PMD_RULE_SET_FILE_PATH_STRINGS,
                PMD_RULE_SET_FILE_PATH_STRICT_EXCEPTION,
                PMD_RULE_SET_FILE_PATH_IMPORTS,
                PMD_RULE_SET_FILE_PATH_JUNIT,
                PMD_RULE_SET_FILE_PATH_CODE_SIZE,
                PMD_RULE_SET_FILE_PATH_BRACES,
// this rule currently creates configuration errors we are not able to handle right now
//                PMD_RULE_SET_FILE_PATH_COUPLING,
                PMD_RULE_SET_FILE_PATH_NAMING,
                PMD_RULE_SET_FILE_PATH_CLONE,
                PMD_RULE_SET_FILE_PATH_LOGGING_JAVA,
                PMD_RULE_SET_FILE_PATH_TYPE_RESOLUTION,
                PMD_RULE_SET_FILE_PATH_METRICS,
                PMD_RULE_SET_FILE_PATH_UNUSED_CODE,
// TODO: too much issues, comment out when finished with other rules
//                PMD_RULE_SET_FILE_PATH_COMMENTS,
                PMD_RULE_SET_FILE_PATH_BASIC,
                PMD_RULE_SET_FILE_PATH_EMPTY,
                PMD_RULE_SET_FILE_PATH_CATEGORY_CODE_STYLE,
                PMD_RULE_SET_FILE_PATH_CATEGORY_BEST_PRACTICES,
                PMD_RULE_SET_FILE_PATH_CATEGORY_DOCUMENTATION,
                PMD_RULE_SET_FILE_PATH_CATEGORY_ERROR_PRONE,
                PMD_RULE_SET_FILE_PATH_CATEGORY_MULTI_THREADING,
                PMD_RULE_SET_FILE_PATH_CATEGORY_PERFORMANCE,
                PMD_RULE_SET_FILE_PATH_CATEGORY_SECURITY
        ));

        configuration.setReportFormat(PMD_REPORT_FILE_FORMAT);
        configuration.setReportFile(PMD_REPORT_FILE_PATH);

        PMD.runPmd(configuration);

        ObjectMapper objectMapper = new ObjectMapper();
        issues = objectMapper.readValue(new File(PMD_REPORT_FILE_PATH), PMDTestResult.class);
        issues.files.stream().map(file -> filterDuplicatedViolations(file)).collect(Collectors.toList());
    }

    @DisplayName("Test NonFinalAttributesShouldBeFinal")
    @Test
    void testNonFinalAttributesShouldBeFinal() {
        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("SuspiciousConstantFieldName"));
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

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
                String fileName = file.filename.split(PMD_REPORT_INPUT_FILE_PATH)[1];
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