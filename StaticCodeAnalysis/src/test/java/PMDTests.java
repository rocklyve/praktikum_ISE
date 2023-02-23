import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.sourceforge.pmd.PmdAnalysis;
import net.sourceforge.pmd.Report;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;
import pmdModel.PMDTestResult;
import pmdModel.PMDTestResultFile;
import pmdModel.PMDTestViolation;

public class PMDTests {
    public static final String NEW_LINE = System.lineSeparator();

    private static final Map<String, String> PMD_RULE_SET_FILE_PATHS = Map.ofEntries(
            Map.entry("codestyle", Path.of("category", "java","codestyle.xml")),
            Map.entry("design", Path.of("category", "java","design.xml")),
            Map.entry("bestpractices", Path.of("category", "java","bestpractices.xml")),
            Map.entry("documentation", Path.of("category", "java","documentation.xml")),
            Map.entry("errorprone", Path.of("category", "java","errorprone.xml")),
            Map.entry("multithreading", Path.of("category", "java","multithreading.xml")),
            Map.entry("performance", Path.of("category", "java","performance.xml")),
            Map.entry("security", Path.of("category", "java","security.xml")),
            Map.entry("custom-rules", Path.of( "custom-pmd-ruleset.xml"))
    ).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().normalize().toString()));

    private static final String PMD_REPORT_INPUT_FILE_PATH =
            Path.of("src", "main","java", "edu", "kit", "informatik")
                    .normalize()
                    .toString();
    private static final String PMD_REPORT_FILE_PATH =
            Path.of("target", "pmd-report", "pmd-report.json")
                    .normalize()
                    .toString();
    private static final String PMD_REPORT_FILE_FORMAT = "json";

    static PMDTestResult issues;
    private static Logger logger = LoggerFactory.getLogger(PMDTests.class);

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        PMDConfiguration configuration = new PMDConfiguration();
        configuration.addInputPath(PMD_REPORT_INPUT_FILE_PATH);

        configuration.setRuleSets(new ArrayList<>(PMD_RULE_SET_FILE_PATHS.values()));

        configuration.setReportFormat(PMD_REPORT_FILE_FORMAT);
        configuration.setReportFile(PMD_REPORT_FILE_PATH);

        PMD.runPmd(configuration);
//        try (PmdAnalysis pmd = PmdAnalysis.create(configuration)) {
//            // optional: add more rulesets
////            Report report = pmd.performAnalysisAndCollectReport();
//            pmd.performAnalysis();
//        } catch (Exception e) {
//            logger.error("Error while running PMD", e);
//        }

        ObjectMapper objectMapper = new ObjectMapper();
        issues = objectMapper.readValue(new File(PMD_REPORT_FILE_PATH), PMDTestResult.class);
    }

    @DisplayName("Test Codebase")
    @ParameterizedTest(name = "{index} => relevantIssueNumbers={0}")
    @MethodSource("getTestTypeParameters")
    void testCodeBase(Pair<String, List<String>> relevantIssueNumbers) {
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers.getRight()));
    }

    private static Stream<Arguments> getTestTypeParameters() {
        return Stream.of(
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
                Arguments.of(Pair.of("Test HardcodedLogic", List.of("AvoidLiteralsInIfCondition"))),
//                Arguments.of(Pair.of("Test StringReferences", List.of())),
                Arguments.of(Pair.of("Test ExceptionsForControlFlow", List.of("EmptyCatchBlock"))),
//                Arguments.of(Pair.of("Test TryCatchBlock", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test UnspecifiedErrorMessage", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test WrongLoopType", List.of("ForLoopCanBeForeach", "ForLoopShouldBeWhileLoop"))),
//                Arguments.of(Pair.of("Test UnnecessaryComplexity", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test ClumsySolution", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test ParsingIntegerValues", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test UtilityClass", List.of("UseUtilityClass"))),
//                Arguments.of(Pair.of("Test UnsafeCast", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test EmptyConstructor", List.of("UncommentedEmptyConstructor", "UnnecessaryConstructor"))),
//                Arguments.of(Pair.of("Test MeaninglessConstant", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test Scanner", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test UnusedElement",
                        List.of("UnusedPrivateField", "UnusedPrivateMethod", "UnusedLocalVariable", "UnusedFormalParameter"))
                ),
//                Arguments.of(Pair.of("Test MissingThrowsInMethodSignature", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test PublicEnumInClass", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test ClassOfConstants", List.of("ClassOfConstants"))),
//                Arguments.of(Pair.of("Test ParsingIntegerValues", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test TrivialJavaDoc", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test BadNaming", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test DataEncapsulationViolation", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test SeparationOfLogicAndInteraction", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test TooComplexCode", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test StaticMethods", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test StaticAttributeOfInstanceAttribute", List.of())),
                Arguments.of(Pair.of("Test FinalModifier", List.of("MethodArgumentCouldBeFinal", "LocalVariableCouldBeFinal")))
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
        String mergedMessage = NEW_LINE;
        for (PMDTestResultFile file : occurringIssues) {
            for (PMDTestViolation violation : file.violations()) {
                String fileName = file.filename().split(PMD_REPORT_INPUT_FILE_PATH)[1];
                mergedMessage +=
                        "Issue: " + violation.rule() + " with message: " + violation.description() +
                                " File: " + fileName + ", Line: " + violation.beginline() + " " + NEW_LINE;
            }
        }

        String finalMergedMessage = "Found " + occurringIssues.size()+ " issues: full text: " + mergedMessage;
        // assert fail outside of
        Assertions.fail(finalMergedMessage);
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
                                    .collect(Collectors.toSet())
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
