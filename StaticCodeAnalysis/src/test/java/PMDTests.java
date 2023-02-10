import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;

public class PMDTests {
    public static final String newLine = System.lineSeparator();
    private static final String PMD_REPORT_INPUT_FILE_PATH =
            Path.of("src", "main", "java", "edu", "kit", "informatik")
                    .normalize()
                    .toString();
    private static final String PMD_RULE_SET_FILE_PATH =
            Path.of("rulesets", "java", "quickstart.xml")
                    .normalize()
                    .toString();
    private static final String PMD_REPORT_FILE_PATH =
            Path.of("src", "resources", "pmd-report.json")
                    .normalize()
                    .toString();
    private static final String PMD_REPORT_FILE_FORMAT = "json";
    static PMDTestResult issues;
    private static Logger logger = LoggerFactory.getLogger(PMDTests.class);

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        PMDConfiguration configuration = new PMDConfiguration();
        configuration.setInputPaths(PMD_REPORT_INPUT_FILE_PATH);
        configuration.addRuleSet(PMD_RULE_SET_FILE_PATH);
        configuration.setReportFormat(PMD_REPORT_FILE_FORMAT);
        configuration.setReportFile(PMD_REPORT_FILE_PATH);

        PMD.runPmd(configuration);

        ObjectMapper objectMapper = new ObjectMapper();
        issues = objectMapper.readValue(new File(PMD_REPORT_FILE_PATH), PMDTestResult.class);
        int countViolations = 0;
        for (PMDTestResultFile file: issues.files()) {
            countViolations += file.violations().size();
        }
        logger.info("CountViolations: " + countViolations);
    }

    @DisplayName("Test Codebase")
    @ParameterizedTest(name = "{index} => relevantIssueNumbers={0}")
    @MethodSource("getTestTypeParameters")
    void testCodeBase(Pair<String, List<String>> relevantIssueNumbers) {
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers.getRight()));
    }

    private static Stream<Arguments> getTestTypeParameters() {
        return Stream.of(
//                Arguments.of(List.of(Pair.of("Test testNonFinalAttributesShouldBeFinal", List.of()))),
//                Arguments.of(List.of(Pair.of("Test SystemDependentLineBreak", List.of()))),
//                Arguments.of(Pair.of("Test RawType", List.of())),
                Arguments.of(Pair.of("Test ConcreteClassInsteadOfInterface", List.of("LooseCoupling"))),
//                Arguments.of(Pair.of("Test AssertInsteadOfIfLoop", List.of())),
//                Arguments.of(Pair.of("Test ObjectInsteadOfConcreteClass", List.of())),
//                Arguments.of(Pair.of("Test PublicEnumInsideClassAndNotInSeparateFile", List.of()),
//                Arguments.of(Pair.of("Test VisibilityAsLowAsPossible", List.of())),
//                Arguments.of(Pair.of("Test Code Duplication", List.of())),
//                Arguments.of(Pair.of("Test CodeDuplicationRepetitionsFixableByInheritance", List.of())),
//                Arguments.of(Pair.of("Test InheritanceInsteadOfEnums", List.of())),
//                Arguments.of(Pair.of("Test OperationsInsteadOfDomain", List.of())),
//                Arguments.of(Pair.of("Test HardcodedLogic", List.of())),
//                Arguments.of(Pair.of("Test StringReferences", List.of())),
                Arguments.of(Pair.of("Test ExceptionsForControlFlow", List.of("EmptyCatchBlock"))),
//                Arguments.of(Pair.of("Test TryCatchBlock", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test UnspecifiedErrorMessage", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test WrongLoopType", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test UnnecessaryComplexity", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test ClumsySolution", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test ParsingIntegerValues", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test UtilityClass", List.of("UseUtilityClass"))),
//                Arguments.of(Pair.of("Test UnsafeCast", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test EmptyConstructor", List.of("UncommentedEmptyConstructor", "UnnecessaryConstructor"))),
//                Arguments.of(Pair.of("Test MeaninglessConstant", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test Scanner", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test UnusedElement",
                        List.of("UnusedPrivateField", "UnusedPrivateMethod", "UnusedLocalVariable"))
                )
//                Arguments.of(Pair.of("Test MissingThrowsInMethodSignature", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test PublicEnumInClass", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test ParsingIntegerValues", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test TrivialJavaDoc", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test BadNaming", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test DataEncapsulationViolation", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test SeparationOfLogicAndInteraction", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test TooComplexCode", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test StaticMethods", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test StaticAttributeOfInstanceAttribute", List.of())),
//                Arguments.of(Pair.of("Test FinalModifier", List.of())),
//                Arguments.of(Pair.of("Test ParsingIntegerValues", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test ToStringVsEquals", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test DoNotUseObject", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test ClassInsteadOfInterface", List.of())),
//                Arguments.of(Pair.of("Test EnumForClosedSet", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test EmptyBlock", List.of())),
//                Arguments.of(Pair.of("Test PackageUsage", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test DynamicBinding", List.of(RULE_PREFIX + ))),
        );
    }

    private static void checkOccurringIssues(List<PMDTestResultFile> occurringIssues) {
        if (occurringIssues.isEmpty()) {
            return;
        }
        String mergedMessage = newLine;
        for (PMDTestResultFile file : occurringIssues) {
            for (PMDTestViolation violation : file.violations()) {
                String fileName = file.filename().split(PMD_REPORT_INPUT_FILE_PATH)[1];
                mergedMessage +=
                        "Issue: " + violation.rule() + " with message: " + violation.description() +
                                " File: " + fileName + ", Line: " + violation.beginline() + " " + newLine;
            }
        }

        String finalMergedMessage = "Found " + occurringIssues.size()+ " issues: full text: " + mergedMessage;
        // assert fail outside of
        Assert.fail(finalMergedMessage);
    }

    private List<PMDTestResultFile> findOccurringIssues(List<String> relevantRules) {
        // remove irrelevant violations
        return issues.files().stream()
                .filter(file -> file.violations().stream()
                        .anyMatch(violation -> relevantRules.contains(violation.rule())))
                .map(file -> {

                    PMDTestResultFile newFile = new PMDTestResultFile(
                            file.filename(),
                            file.violations().stream()
                                    .filter(violation -> relevantRules.contains(violation.rule()))
                                    .collect(Collectors.toList())
                    );
                    return newFile;
                })
                .filter(file -> !file.violations().isEmpty())
                .collect(Collectors.toList());
    }

    @AfterAll
    public static void tearDownAfterClass() {
        logger.info("over");
    }
}
