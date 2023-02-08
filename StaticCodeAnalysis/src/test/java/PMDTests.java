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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;

public class PMDTests {
    public static final String newLine = System.lineSeparator();
    private static final Map<String, String> PMD_RULE_SET_FILE_PATHS = Map.ofEntries(
            Map.entry("j2ee", Path.of("rulesets", "java", "j2ee.xml").toString()),
            Map.entry("sunsecure", Path.of("rulesets", "java", "sunsecure.xml").toString()),
            Map.entry("design", Path.of("rulesets", "java","design.xml").toString()),
            Map.entry("finalizers", Path.of("rulesets", "java","finalizers.xml").toString()),
            Map.entry("controversial", Path.of("rulesets", "java","controversial.xml").toString()),
            Map.entry("unnecessary", Path.of("rulesets", "java","unnecessary.xml").toString()),
            Map.entry("logging-jakarta-commons", Path.of("rulesets", "java","logging-jakarta-commons.xml").toString()),
            Map.entry("quickstart", Path.of("rulesets", "java","quickstart.xml").toString()),
            Map.entry("javabeans", Path.of("rulesets", "java","javabeans.xml").toString()),
            Map.entry("optimizations", Path.of("rulesets", "java","optimizations.xml").toString()),
            Map.entry("strings", Path.of("rulesets", "java","strings.xml").toString()),
            Map.entry("strictexception", Path.of("rulesets", "java","strictexception.xml").toString()),
            Map.entry("imports", Path.of("rulesets", "java","imports.xml").toString()),
            Map.entry("junit", Path.of("rulesets", "java","junit.xml").toString()),
            Map.entry("codesize", Path.of("rulesets", "java","codesize.xml").toString()),
            Map.entry("braces", Path.of("rulesets", "java","braces.xml").toString()),
//            Map.entry("coupling", Path.of("rulesets", "java","coupling.xml").toString()),
            Map.entry("naming", Path.of("rulesets", "java","naming.xml").toString()),
            Map.entry("clone", Path.of("rulesets", "java","clone.xml").toString()),
            Map.entry("logging-java", Path.of("rulesets", "java","logging-java.xml").toString()),
            Map.entry("typeresolution", Path.of("rulesets", "java","typeresolution.xml").toString()),
            Map.entry("metrics", Path.of("rulesets", "java","metrics.xml").toString()),
            Map.entry("unusedcode", Path.of("rulesets", "java","unusedcode.xml").toString()),
//            Map.entry("comments", Path.of("rulesets", "java","comments.xml").toString()),
            Map.entry("basic", Path.of("rulesets", "java","basic.xml").toString()),
            Map.entry("empty", Path.of("rulesets", "java","empty.xml").toString()),
            Map.entry("codestyle", Path.of("category", "java","codestyle.xml").toString()),
            Map.entry("bestpractices", Path.of("category", "java","bestpractices.xml").toString()),
            Map.entry("documentation", Path.of("category", "java","documentation.xml").toString()),
            Map.entry("errorprone", Path.of("category", "java","errorprone.xml").toString()),
            Map.entry("multithreading", Path.of("category", "java","multithreading.xml").toString()),
            Map.entry("performance", Path.of("category", "java","performance.xml").toString()),
            Map.entry("security", Path.of("category", "java","security.xml").toString()),
            Map.entry("maven-pmd-plugin-default", Path.of("rulesets", "java","maven-pmd-plugin-default.xml").toString()),
            Map.entry("custom-rules", Path.of("src", "resources","customRule", "custom-pmd-ruleset.xml").toString())

    );
    private static final String PMD_REPORT_INPUT_FILE_PATH =
            Path.of("src", "main","java", "edu", "kit", "informatik")
                    .toString();
    private static final String PMD_REPORT_FILE_PATH =
            Path.of("src", "resources", "pmd-report.json")
                    .toString();
    private static final String PMD_REPORT_FILE_FORMAT = "json";

    static PMDTestResult issues;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        PMDConfiguration configuration = new PMDConfiguration();
        configuration.setInputPaths(PMD_REPORT_INPUT_FILE_PATH);

        configuration.setRuleSets(new ArrayList<>(PMD_RULE_SET_FILE_PATHS.values()));

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

    @DisplayName("Test HardcodedLogic")
    @Test
    void testHardcodedLogic() {
        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("AvoidLiteralsInIfCondition"));
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

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


    @DisplayName("Test WrongLoopType")
    @Test
    void testWrongLoopType() {
        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("ForLoopCanBeForeach", "ForLoopShouldBeWhileLoop"));
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }


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
                Arrays.asList("UnusedPrivateField", "UnusedPrivateMethod", "UnusedLocalVariable", "UnusedFormalParameter")
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

    @DisplayName("Test FinalModifier")
    @Test
    void testFinalModifier() {
        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("MethodArgumentCouldBeFinal", "LocalVariableCouldBeFinal"));
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

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
    @DisplayName("Test ClassInsteadOfInterface")
    @Test
    void testClassInsteadOfInterface() {
        ArrayList<String> relevantRules = new ArrayList<String>(Arrays.asList("LooseCoupling"));
        checkOccurringIssues(findOccurringIssues(relevantRules));
    }

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