import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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
import org.sonarsource.sonarlint.core.StandaloneSonarLintEngineImpl;
import org.sonarsource.sonarlint.core.analysis.api.ClientInputFile;
import org.sonarsource.sonarlint.core.client.api.common.analysis.Issue;
import org.sonarsource.sonarlint.core.client.api.standalone.StandaloneAnalysisConfiguration;
import org.sonarsource.sonarlint.core.client.api.standalone.StandaloneGlobalConfiguration;
import org.sonarsource.sonarlint.core.client.api.standalone.StandaloneSonarLintEngine;
import org.sonarsource.sonarlint.core.commons.Language;
import edu.kit.informatik.SonarFile;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SonarQubeTests {
    public static final String newLine = System.lineSeparator();
    public static final String JAVA_CLASS_PATH = "java.class.path";
    public static final String SONAR_JAVA_PLUGIN = "sonar-java-plugin";
    private static final String RULE_PREFIX = "java:S";
    private static final String PROJECT_ENTRY_PATH =
            Path.of("src", "main", "java", "edu", "kit", "informatik")
            .normalize()
            .toString();
    private static final String JAVA_FILE_PREFIX = ".java";
    private ArrayList<Issue> issues;
    Logger logger = LoggerFactory.getLogger(SonarQubeTests.class);

    @BeforeAll
    public void setUpBeforeClass() throws Exception {
        issues = new ArrayList<>();

        Path javaPlugin = getJavaPlugin();
        var path = Path.of(PROJECT_ENTRY_PATH).toAbsolutePath();
        var javaFiles = getFiles(path);

        StandaloneGlobalConfiguration configuration = StandaloneGlobalConfiguration.builder().addEnabledLanguage(Language.JAVA).addPlugins(javaPlugin).setWorkDir(path).build();
        StandaloneSonarLintEngine standaloneSonarLintEngine = new StandaloneSonarLintEngineImpl(configuration);
        StandaloneAnalysisConfiguration sac = StandaloneAnalysisConfiguration.builder().setBaseDir(path).addInputFiles(javaFiles).build();
        standaloneSonarLintEngine.analyze(sac, this::listen, (formattedMessage, level) -> System.out.println(formattedMessage), null);
        standaloneSonarLintEngine.stop();
    }

    /**
     * This is a parameterized test method for testing a codebase. It takes in a Pair object of
     * a String and a List of Strings as a parameter, where the String represents relevant
     * issue numbers and the List of Strings represents the test type parameters. The method
     * uses these parameters to find occurring issues and checks them against expected results
     * using the checkOccurringIssues method.
     * @param description containing a String with the description of the test
     * @param relevantIssueNumbers containing a List of String with relevant issue numbers
     * */
    @DisplayName("Test Codebase")
    @ParameterizedTest(name = "{index} => relevant rule: \"{0}\"")
    @MethodSource("getTestTypeParameters")
    void testCodeBase(String description, List<String> relevantIssueNumbers) {
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

    private Stream<Arguments> getTestTypeParameters() {
        return Stream.of(
                Arguments.of("Test RawType", List.of(RULE_PREFIX + "3740")),
                Arguments.of("Test ConcreteClassInsteadOfInterface", List.of(RULE_PREFIX + "1319")),
                // for AssertVSIF, there are existing rules, but they detect all asserts, in the
                // "Programmieren" lecture, only asserts at a start of a public method should not be allowed, this is
                // now solved as a custom pmd rule
//                Arguments.of("Test AssertInsteadOfIfLoop", List.of(RULE_PREFIX + "5960", RULE_PREFIX + "4274")),
                Arguments.of("Test Code Duplication", List.of(RULE_PREFIX + "4144")),
                Arguments.of("Test UtilityClass", List.of(RULE_PREFIX + "1118")),
                Arguments.of("Test UnusedElement",
                        List.of(RULE_PREFIX + "1144", RULE_PREFIX + "1068", RULE_PREFIX + "1481")
                ),
                Arguments.of("Test StaticAttributeOfInstanceAttribute", List.of(RULE_PREFIX + "1170")),
                Arguments.of("Test FinalModifier", List.of(RULE_PREFIX + "3008")),
                Arguments.of("Test ClassInsteadOfInterface", List.of(RULE_PREFIX + "1319")),
                Arguments.of("Test EmptyBlock",
                        List.of(RULE_PREFIX + "2094", RULE_PREFIX + "1186", RULE_PREFIX + "108")
                )
        );
    }

    @AfterAll
    public void tearDownAfterClass() {
        issues = null;
    }

    private void checkOccurringIssues(List<Issue> occurringIssues) {
        if (occurringIssues.isEmpty()) {
        } else {
            String mergedMessage = newLine;
            for (Issue issue : occurringIssues) {
               mergedMessage +=
                       "Issue: " + issue.getRuleKey() + " with message: " + issue.getMessage() +
                       " File: " + issue.getInputFile().relativePath() + ", Line: " + issue.getStartLine() + " " + newLine;
            }
            String finalMergedMessage = "Found " + occurringIssues.size()+ " issues: full text: " + mergedMessage;
            occurringIssues.forEach(issue -> Assertions.fail(finalMergedMessage));
        }
    }

    private List<Issue> findOccurringIssues(List<String> relevantIssueNumbers) {
        return issues.stream().filter(issue -> relevantIssueNumbers.contains(issue.getRuleKey())).collect(Collectors.toList());
    }

    private Path getJavaPlugin() {
        String classpath = System.getProperty(JAVA_CLASS_PATH);
        String[] classPathValues = classpath.split(File.pathSeparator);
        for (String classPath : classPathValues) {
            if (classPath.contains(SONAR_JAVA_PLUGIN))
                return Path.of(classPath);
        }
        throw new IllegalStateException("Java Plugin not found!");
    }

    private List<ClientInputFile> getFiles(Path path) throws IOException {
        final var list = new ArrayList<ClientInputFile>();
        Files.walk(path).forEach(filePath -> {
            if (filePath.getFileName().toString().endsWith(JAVA_FILE_PREFIX)) {
                list.add(new SonarFile(path, filePath));
            }
        });

        return list;
    }

    private void listen(Issue i) {
        issues.add(i);
        System.err.println(i);
        logger.debug("Issue added: ", issues);
    }
}