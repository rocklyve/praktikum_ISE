import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import org.sonarsource.sonarlint.core.StandaloneSonarLintEngineImpl;
import org.sonarsource.sonarlint.core.analysis.api.ClientInputFile;
import org.sonarsource.sonarlint.core.client.api.common.analysis.Issue;
import org.sonarsource.sonarlint.core.client.api.standalone.StandaloneAnalysisConfiguration;
import org.sonarsource.sonarlint.core.client.api.standalone.StandaloneGlobalConfiguration;
import org.sonarsource.sonarlint.core.client.api.standalone.StandaloneSonarLintEngine;
import org.sonarsource.sonarlint.core.commons.Language;

import edu.kit.informatik.SonarFile;

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
    private static ArrayList<Issue> issues;
    static Logger logger = LoggerFactory.getLogger(SonarQubeTests.class);

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        issues = new ArrayList<>();

        // TODO: saveActions plugin, or other cleanup
        // TODO: or with eclipse cleanup

        Path javaPlugin = getJavaPlugin();
        var path = Path.of(PROJECT_ENTRY_PATH).toAbsolutePath();
        var javaFiles = getFiles(path);

        StandaloneGlobalConfiguration configuration = StandaloneGlobalConfiguration.builder().addEnabledLanguage(Language.JAVA).addPlugins(javaPlugin).setWorkDir(path).build();
        StandaloneSonarLintEngine standaloneSonarLintEngine = new StandaloneSonarLintEngineImpl(configuration);
        StandaloneAnalysisConfiguration sac = StandaloneAnalysisConfiguration.builder().setBaseDir(path).addInputFiles(javaFiles).build();
        standaloneSonarLintEngine.analyze(sac, SonarQubeTests::listen, (formattedMessage, level) -> System.out.println(formattedMessage), null);
//        standaloneSonarLintEngine.analyze(sac, this::listen, (formattedMessage, level) -> System.out.println(formattedMessage), null);
        standaloneSonarLintEngine.stop();
    }

    @DisplayName("Test Codebase")
    @ParameterizedTest(name = "{index} => relevantIssueNumbers={0}")
    @MethodSource("getTestTypeParameters")
    void testCodeBase(Pair<String, List<String>> relevantIssueNumbers) {
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers.getRight()));
    }

    private static Stream<Arguments> getTestTypeParameters() {
        return Stream.of(
//                Arguments.of(Pair.of("Test testNonFinalAttributesShouldBeFinal", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test SystemDependentLineBreak", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test RawType", List.of(RULE_PREFIX + "3740"))),
                Arguments.of(Pair.of("Test ConcreteClassInsteadOfInterface", List.of(RULE_PREFIX + "1319"))),
                Arguments.of(Pair.of("Test AssertInsteadOfIfLoop", List.of(RULE_PREFIX + "5960", RULE_PREFIX + "4274"))),
//                Arguments.of(Pair.of("Test ObjectInsteadOfConcreteClass", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test PublicEnumInsideClassAndNotInSeparateFile", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test VisibilityAsLowAsPossible", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test Code Duplication", List.of(RULE_PREFIX + "4144"))),
//                Arguments.of((Pair.of("Test CodeDuplicationRepetitionsFixableByInheritance", List.of(RULE_PREFIX + ))),
//                Arguments.of((Pair.of("Test InheritanceInsteadOfEnums", List.of(RULE_PREFIX + ))),
//                Arguments.of((Pair.of("Test OperationsInsteadOfDomain", List.of(RULE_PREFIX + ))),
//                Arguments.of((Pair.of("Test HardcodedLogic", List.of(RULE_PREFIX + ))),
//                Arguments.of((Pair.of("Test StringReferences", List.of(RULE_PREFIX + ))),
//                Arguments.of((Pair.of("Test ExceptionsForControlFlow", List.of(RULE_PREFIX + ))),
//                Arguments.of((Pair.of("Test TryCatchBlock", List.of(RULE_PREFIX + ))),
//                Arguments.of((Pair.of("Test UnspecifiedErrorMessage", List.of(RULE_PREFIX + )))),
//                Arguments.of((Pair.of("Test WrongLoopType", List.of(RULE_PREFIX + ))),
//                Arguments.of((Pair.of("Test UnnecessaryComplexity", List.of(RULE_PREFIX + ))),
//                Arguments.of((Pair.of("Test ClumsySolution", List.of(RULE_PREFIX + ))),
//                Arguments.of((Pair.of("Test ParsingIntegerValues", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test UtilityClass", List.of(RULE_PREFIX + "1118"))),
//                Arguments.of(Pair.of("Test UnsafeCast", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test EmptyConstructor", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test MeaninglessConstant", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test Scanner", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test UnusedElement",
                        List.of(RULE_PREFIX + "1144", RULE_PREFIX + "1068", RULE_PREFIX + "1481"))
                ),
//                Arguments.of(Pair.of("Test MissingThrowsInMethodSignature", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test PublicEnumInClass", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test ParsingIntegerValues", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test TrivialJavaDoc", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test BadNaming", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test DataEncapsulationViolation", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test SeparationOfLogicAndInteraction", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test TooComplexCode", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test StaticMethods", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test StaticAttributeOfInstanceAttribute", List.of(RULE_PREFIX + "1170"))),
                Arguments.of(Pair.of("Test FinalModifier", List.of(RULE_PREFIX + "3008"))),
//                Arguments.of(Pair.of("Test ParsingIntegerValues", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test ToStringVsEquals", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test DoNotUseObject", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test ClassInsteadOfInterface", List.of(RULE_PREFIX + "1319"))),
//                Arguments.of(Pair.of("Test EnumForClosedSet", List.of(RULE_PREFIX + ))),
                Arguments.of(Pair.of("Test EmptyBlock",
                        List.of(RULE_PREFIX + "2094", RULE_PREFIX + "1186", RULE_PREFIX + "108"))
                )
//                Arguments.of(Pair.of("Test PackageUsage", List.of(RULE_PREFIX + ))),
//                Arguments.of(Pair.of("Test DynamicBinding", List.of(RULE_PREFIX + ))),
        );
    }

    @AfterAll
    public static void tearDownAfterClass() {
        issues = null;
    }

    private static void checkOccurringIssues(List<Issue> occurringIssues) {
        if (occurringIssues.isEmpty()) {
            return;
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

    private static Path getJavaPlugin() {
        String classpath = System.getProperty(JAVA_CLASS_PATH);
        String[] classPathValues = classpath.split(File.pathSeparator);
        for (String classPath : classPathValues) {
            if (classPath.contains(SONAR_JAVA_PLUGIN))
                return Path.of(classPath);
        }
        throw new IllegalStateException("Java Plugin not found!");
    }

    private static List<ClientInputFile> getFiles(Path path) throws IOException {
        final var list = new ArrayList<ClientInputFile>();
        Files.walk(path).forEach(filePath -> {
            if (filePath.getFileName().toString().endsWith(JAVA_FILE_PREFIX)) {
                list.add(new SonarFile(path, filePath));
            }
        });

        return list;
    }

    private static void listen(Issue i) {
        issues.add(i);
        System.err.println(i);
        logger.debug("Issue added: ", issues);
    }
}