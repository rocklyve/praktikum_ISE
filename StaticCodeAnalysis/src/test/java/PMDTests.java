import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PMDTests {
    public static final String NEW_LINE = System.lineSeparator();

    private final Map<String, String> PMD_RULE_SET_FILE_PATHS = rulesFromMap(Map.ofEntries(
            Map.entry("codestyle", Path.of("category", "java","codestyle.xml")),
            Map.entry("design", Path.of("category", "java","design.xml")),
            Map.entry("bestpractices", Path.of("category", "java","bestpractices.xml")),
            Map.entry("documentation", Path.of("category", "java","documentation.xml")),
            Map.entry("errorprone", Path.of("category", "java","errorprone.xml")),
            Map.entry("multithreading", Path.of("category", "java","multithreading.xml")),
            Map.entry("performance", Path.of("category", "java","performance.xml")),
            Map.entry("security", Path.of("category", "java","security.xml")),
            Map.entry("custom-rules", Path.of( "custom-pmd-ruleset.xml"))
    ));

    private static final String PMD_REPORT_INPUT_FILE_PATH =
            Path.of("src", "main","java", "edu", "kit", "informatik")
                    .normalize()
                    .toString();
    private static final String PMD_REPORT_FILE_PATH =
            Path.of("target", "pmd-report", "pmd-report.json")
                    .normalize()
                    .toString();
    private static final String PMD_REPORT_FILE_FORMAT = "json";

    PMDTestResult issues;
    private static final Logger logger = LoggerFactory.getLogger(PMDTests.class);

    @BeforeAll
    public void setUpBeforeClass() throws Exception {
        PMDConfiguration configuration = new PMDConfiguration();
        configuration.addInputPath(PMD_REPORT_INPUT_FILE_PATH);

        configuration.setRuleSets(new ArrayList<>(PMD_RULE_SET_FILE_PATHS.values()));

        configuration.setReportFormat(PMD_REPORT_FILE_FORMAT);
        configuration.setReportFile(PMD_REPORT_FILE_PATH);

        PMD.runPmd(configuration);

        ObjectMapper objectMapper = new ObjectMapper();
        issues = objectMapper.readValue(new File(PMD_REPORT_FILE_PATH), PMDTestResult.class);
    }

    /**
     * This is a parameterized test method for testing a codebase. It takes in a Pair object of
     * a String and a List of Strings as a parameter, where the String represents relevant
     * issue numbers and the List of Strings represents the test type parameters. The method
     * uses these parameters to find occurring issues and checks them against expected results
     * using the checkOccurringIssues method.
     * @param description a String representing the description of the test
     * @param relevantIssueNumbers containing a List of Strings with relevant issue numbers
     * */
    @DisplayName("Test Codebase")
    @ParameterizedTest(name = "{index} => relevant rule: \"{0}\"")
    @MethodSource("getTestTypeParameters")
    public void testCodeBase(String description, List<String> relevantIssueNumbers) {
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

    private Stream<Arguments> getTestTypeParameters() {
        return Stream.of(
                // This is a custom rule, which detects, if any code in the codebase has system dependent line breaks
                Arguments.of("Test SystemDependentLineBreak", List.of("SystemDependentLineBreakNotAllowed")),
                Arguments.of("Test ConcreteClassInsteadOfInterface", List.of("LooseCoupling")),
                // this is a custom rule, which detects assert statements in public functions,
                // but only if they are the first statement
                Arguments.of("Test AssertInsteadOfIfLoop", List.of("AssertStatementFirstInPublicFunction")),
                // this is a custom rule, which detects, if there are public enums inside of classes or interfaces
                Arguments.of("Test PublicEnumInsideClassAndNotInSeparateFile", List.of("PublicEnumInsideClassOrInterface")),
                Arguments.of("Test HardcodedLogic", List.of("AvoidLiteralsInIfCondition")),
                Arguments.of("Test ExceptionsForControlFlow", List.of("EmptyCatchBlock")),
                Arguments.of("Test TryCatchBlock", List.of("TooLongTryBlockStatement")),
                Arguments.of("Test WrongLoopType", List.of("ForLoopCanBeForeach", "ForLoopShouldBeWhileLoop")),
                Arguments.of("Test UtilityClass", List.of("UseUtilityClass")),
                Arguments.of("Test EmptyConstructor", List.of("UncommentedEmptyConstructor", "UnnecessaryConstructor")),
                Arguments.of("Test UnusedElement",
                        List.of("UnusedPrivateField", "UnusedPrivateMethod", "UnusedLocalVariable", "UnusedFormalParameter")
                ),
                // this is a custom pmd rule, which detects, if classes only keeps constants, but no other attributes and functions
                Arguments.of("Test ClassOfConstants", List.of("ClassOfConstants")),
                Arguments.of("Test FinalModifier", List.of("MethodArgumentCouldBeFinal", "LocalVariableCouldBeFinal"))
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

    private Map<String, String> rulesFromMap(Map<String, Path> map) {
        return map.entrySet().stream().collect(
                Collectors.toMap(
                        // this `.replace("\\", "/")` is necessary because the PMD API does not work with Windows paths
                        // thats because the framework will always require forward slashes there
                        Map.Entry::getKey, e -> e.getValue().normalize().toString().replace("\\", "/")
                )
        );
    }

    @AfterAll
    public void tearDownAfterClass() {
        logger.info("over");
    }
}
