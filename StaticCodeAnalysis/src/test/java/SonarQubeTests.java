import edu.kit.informatik.SonarFile;
import junit.framework.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.sonarsource.sonarlint.core.StandaloneSonarLintEngineImpl;
import org.sonarsource.sonarlint.core.analysis.api.ClientInputFile;
import org.sonarsource.sonarlint.core.client.api.common.analysis.Issue;
import org.sonarsource.sonarlint.core.client.api.standalone.StandaloneAnalysisConfiguration;
import org.sonarsource.sonarlint.core.client.api.standalone.StandaloneGlobalConfiguration;
import org.sonarsource.sonarlint.core.client.api.standalone.StandaloneSonarLintEngine;
import org.sonarsource.sonarlint.core.commons.Language;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SonarQubeTests {
    public static final String newLine = System.lineSeparator();
    private static ArrayList<Issue> issues;
    private static final String rulePrefix = "java:S";
    private static final String projectEntryPath = "src/main/java";
    private static final String packagePath = "edu/kit/informatik/";
    private static final String javaFilePrefix = ".java";

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        issues = new ArrayList<>();

        // TODO: saveActions plugin, or other cleanup
        // TODO: or with eclipse cleanup

        Path javaPlugin = getJavaPlugin();
        var path = Path.of(projectEntryPath, packagePath).toAbsolutePath();
        var javaFiles = getFiles(path);

        StandaloneGlobalConfiguration configuration = StandaloneGlobalConfiguration.builder().addEnabledLanguage(Language.JAVA).addPlugins(javaPlugin).setWorkDir(path).build();
        StandaloneSonarLintEngine standaloneSonarLintEngine = new StandaloneSonarLintEngineImpl(configuration);
        StandaloneAnalysisConfiguration sac = StandaloneAnalysisConfiguration.builder().setBaseDir(path).addInputFiles(javaFiles).build();
        standaloneSonarLintEngine.analyze(sac, SonarQubeTests::listen, (formattedMessage, level) -> System.out.println(formattedMessage), null);
//        standaloneSonarLintEngine.analyze(sac, this::listen, (formattedMessage, level) -> System.out.println(formattedMessage), null);
        standaloneSonarLintEngine.stop();
    }

//    @DisplayName("Test NonFinalAttributesShouldBeFinal")
//    @Test
//    void testNonFinalAttributesShouldBeFinal() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test SystemDependentLineBreak")
//    @Test
//    void testSystemDependentLineBreak() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test RawType")
    @Test
    void testRawType() {
        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + "3740"));
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

    @DisplayName("Test ConcreteClassInsteadOfInterface")
    @Test
    void testConcreteClassInsteadOfInterface() {
        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + "1319"));
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

    @DisplayName("Test AssertInsteadOfIfLoop")
    @Test
    void testAssertInsteadOfIfLoop() {
        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(
                Arrays.asList(
                        rulePrefix + "5960",
                        rulePrefix + "4274"
                )
        );
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test ObjectInsteadOfConcreteClass")
//    @Test
//    void testObjectInsteadOfConcreteClass() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test PublicEnumInsideClassAndNotInSeparateFile")
//    @Test
//    void testPublicEnumInsideClassAndNotInSeparateFile() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test VisibilityAsLowAsPossible")
//    @Test
//    void testVisibilityAsLowAsPossible() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test Code Duplication")
    @Test
    void testCodeDuplication() {
        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + "4144"));
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test CodeDuplicationRepetitionsFixableByInheritance")
//    @Test
//    void testCodeDuplicationRepetitionsFixableByInheritance() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test InheritanceInsteadOfEnums")
//    @Test
//    void testInheritanceInsteadOfEnums() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test OperationsInsteadOfDomain")
//    @Test
//    void testOperationsInsteadOfDomain() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test HardcodedLogic")
//    @Test
//    void testHardcodedLogic() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test StringReferences")
//    @Test
//    void testStringReferences() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test ExceptionsForControlFlow")
//    @Test
//    void testExceptionsForControlFlow() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test TryCatchBlock")
//    @Test
//    void testTryCatchBlock() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test UnspecifiedErrorMessage")
//    @Test
//    void testUnspecifiedErrorMessage() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }


//    @DisplayName("Test WrongLoopType")
//    @Test
//    void testWrongLoopType() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }


//    @DisplayName("Test UnnecessaryComplexity")
//    @Test
//    void testUnnecessaryComplexity() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test ClumsySolution")
//    @Test
//    void testClumsySolution() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test ParsingIntegerValues")
//    @Test
//    void testParsingIntegerValues() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test UtilityClass")
    @Test
    void testUtilityClass() {
        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + "1118"));
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test UnsafeCast")
//    @Test
//    void testUnsafeCast() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test EmptyConstructor")
//    @Test
//    void testEmptyConstructor() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test MeaninglessConstant")
//    @Test
//    void testMeaninglessConstant() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test Scanner")
//    @Test
//    void testScanner() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test UnusedElement")
    @Test
    void testUnusedElement() {
        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(
                Arrays.asList(
                        rulePrefix + "1144",
                        rulePrefix + "1068",
                        rulePrefix + "1481"
                )
        );
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test MissingThrowsInMethodSignature")
//    @Test
//    void testMissingThrowsInMethodSignature() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test PublicEnumInClass")
//    @Test
//    void testPublicEnumInClass() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test ParsingIntegerValues")
//    @Test
//    void testClassOfConstants() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test TrivialJavaDoc")
//    @Test
//    void testTrivialJavaDoc() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test BadNaming")
//    @Test
//    void testBadNaming() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test DataEncapsulationViolation")
//    @Test
//    void testDataEncapsulationViolation() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test SeparationOfLogicAndInteraction")
//    @Test
//    void testSeparationOfLogicAndInteraction() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test TooComplexCode")
//    @Test
//    void testTooComplexCode() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test StaticMethods")
//    @Test
//    void testStaticMethods() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test StaticAttributeOfInstanceAttribute")
    @Test
    void testStaticAttributeOfInstanceAttribute() {
        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + "1170"));
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

    @DisplayName("Test FinalModifier")
    @Test
    void testFinalModifier() {
        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + "3008"));
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test ParsingIntegerValues")
//    @Test
//    void testJavaAPI() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test ToStringVsEquals")
//    @Test
//    void testToStringVsEquals() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test DoNotUseObject")
//    @Test
//    void testDoNotUseObject() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test ClassInsteadOfInterface")
    @Test
    void testClassInsteadOfInterface() {
        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + "1319"));
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test EnumForClosedSet")
//    @Test
//    void testEnumForClosedSet() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test EmptyBlock")
    @Test
    void testEmptyBlock() {
        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(
                Arrays.asList(
                        rulePrefix + "2094",
                        rulePrefix + "1186",
                        rulePrefix + "108"
                )
        );
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test PackageUsage")
//    @Test
//    void testPackageUsage() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test DynamicBinding")
//    @Test
//    void testDynamicBinding() {
//        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + ""));
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

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
            occurringIssues.forEach(issue -> Assert.fail(finalMergedMessage));
        }
    }

    private List<Issue> findOccurringIssues(ArrayList<String> relevantIssueNumbers) {
        return issues.stream().filter(issue -> relevantIssueNumbers.contains(issue.getRuleKey())).collect(Collectors.toList());
    }

    private static Path getJavaPlugin() {
        String classpath = System.getProperty("java.class.path");
        String[] classPathValues = classpath.split(File.pathSeparator);
        for (String classPath : classPathValues) {
            if (classPath.contains("sonar-java-plugin"))
                return Path.of(classPath);
        }
        throw new IllegalStateException("Java Plugin not found!");
    }

    private static List<ClientInputFile> getFiles(Path path) throws IOException {
        final var list = new ArrayList<ClientInputFile>();
        Files.walk(path).forEach(filePath -> {
            if (filePath.getFileName().toString().endsWith(javaFilePrefix)) {
                list.add(new SonarFile(path, filePath));
            }
        });

        return list;
    }

    private static void listen(Issue i) {
        issues.add(i);
        System.err.println(i);
    }
}