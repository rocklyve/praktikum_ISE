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
    private static final String projectEntryPath = "src/main/java/edu/kit/informatik/";
    private static final String javaFilePrefix = ".java";

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        issues = new ArrayList<Issue>();

        Path javaPlugin = getJavaPlugin();
        var path = Path.of(projectEntryPath).toAbsolutePath();
        var javaFiles = getFiles(path);

        StandaloneGlobalConfiguration configuration = StandaloneGlobalConfiguration.builder().addEnabledLanguage(Language.JAVA).addPlugins(javaPlugin).setWorkDir(path).build();
        StandaloneSonarLintEngine standaloneSonarLintEngine = new StandaloneSonarLintEngineImpl(configuration);
        StandaloneAnalysisConfiguration sac = StandaloneAnalysisConfiguration.builder().setBaseDir(path).addInputFiles(javaFiles).build();
        standaloneSonarLintEngine.analyze(sac, SonarQubeTests::listen, (formattedMessage, level) -> System.out.println(formattedMessage), null);
        standaloneSonarLintEngine.stop();
    }

//    @DisplayName("Test NonFinalAttributesShouldBeFinal")
//    @Test
//    void testNonFinalAttributesShouldBeFinal() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test SystemDependentLineBreak")
//    @Test
//    void testSystemDependentLineBreak() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test RawType")
    @Test
    void testRawType() {
        List<String> relevantIssueNumbers = List.of(rulePrefix + "3740");
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

    @DisplayName("Test ConcreteClassInsteadOfInterface")
    @Test
    void testConcreteClassInsteadOfInterface() {
        List<String> relevantIssueNumbers = List.of(rulePrefix + "1319");
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

    @DisplayName("Test AssertInsteadOfIfLoop")
    @Test
    void testAssertInsteadOfIfLoop() {
        List<String> relevantIssueNumbers = List.of(
             rulePrefix + "5960",
            rulePrefix + "4274"
        );
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test ObjectInsteadOfConcreteClass")
//    @Test
//    void testObjectInsteadOfConcreteClass() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test PublicEnumInsideClassAndNotInSeparateFile")
//    @Test
//    void testPublicEnumInsideClassAndNotInSeparateFile() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test VisibilityAsLowAsPossible")
//    @Test
//    void testVisibilityAsLowAsPossible() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test Code Duplication")
    @Test
    void testCodeDuplication() {
        List<String> relevantIssueNumbers = List.of(rulePrefix + "4144");
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test CodeDuplicationRepetitionsFixableByInheritance")
//    @Test
//    void testCodeDuplicationRepetitionsFixableByInheritance() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test InheritanceInsteadOfEnums")
//    @Test
//    void testInheritanceInsteadOfEnums() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test OperationsInsteadOfDomain")
//    @Test
//    void testOperationsInsteadOfDomain() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test HardcodedLogic")
//    @Test
//    void testHardcodedLogic() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test StringReferences")
//    @Test
//    void testStringReferences() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test ExceptionsForControlFlow")
//    @Test
//    void testExceptionsForControlFlow() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test TryCatchBlock")
//    @Test
//    void testTryCatchBlock() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test UnspecifiedErrorMessage")
//    @Test
//    void testUnspecifiedErrorMessage() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }


//    @DisplayName("Test WrongLoopType")
//    @Test
//    void testWrongLoopType() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }


//    @DisplayName("Test UnnecessaryComplexity")
//    @Test
//    void testUnnecessaryComplexity() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test ClumsySolution")
//    @Test
//    void testClumsySolution() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test ParsingIntegerValues")
//    @Test
//    void testParsingIntegerValues() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test UtilityClass")
    @Test
    void testUtilityClass() {
        List<String> relevantIssueNumbers = List.of(rulePrefix + "1118");
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test UnsafeCast")
//    @Test
//    void testUnsafeCast() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test EmptyConstructor")
//    @Test
//    void testEmptyConstructor() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test MeaninglessConstant")
//    @Test
//    void testMeaninglessConstant() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test Scanner")
//    @Test
//    void testScanner() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test UnusedElement")
    @Test
    void testUnusedElement() {
        List<String> relevantIssueNumbers = List.of(
            rulePrefix + "1144",
            rulePrefix + "1068",
            rulePrefix + "1481"
        );
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test MissingThrowsInMethodSignature")
//    @Test
//    void testMissingThrowsInMethodSignature() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test PublicEnumInClass")
//    @Test
//    void testPublicEnumInClass() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test ParsingIntegerValues")
//    @Test
//    void testClassOfConstants() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test TrivialJavaDoc")
//    @Test
//    void testTrivialJavaDoc() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test BadNaming")
//    @Test
//    void testBadNaming() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test DataEncapsulationViolation")
//    @Test
//    void testDataEncapsulationViolation() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test SeparationOfLogicAndInteraction")
//    @Test
//    void testSeparationOfLogicAndInteraction() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test TooComplexCode")
//    @Test
//    void testTooComplexCode() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test StaticMethods")
//    @Test
//    void testStaticMethods() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test StaticAttributeOfInstanceAttribute")
    @Test
    void testStaticAttributeOfInstanceAttribute() {
        List<String> relevantIssueNumbers = List.of(rulePrefix + "1170");
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

    @DisplayName("Test FinalModifier")
    @Test
    void testFinalModifier() {
        List<String> relevantIssueNumbers = List.of(rulePrefix + "3008");
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test ParsingIntegerValues")
//    @Test
//    void testJavaAPI() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test ToStringVsEquals")
//    @Test
//    void testToStringVsEquals() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test DoNotUseObject")
//    @Test
//    void testDoNotUseObject() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test ClassInsteadOfInterface")
    @Test
    void testClassInsteadOfInterface() {
        List<String> relevantIssueNumbers = List.of(rulePrefix + "1319");
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test EnumForClosedSet")
//    @Test
//    void testEnumForClosedSet() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

    @DisplayName("Test EmptyBlock")
    @Test
    void testEmptyBlock() {
        List<String> relevantIssueNumbers = List.of(
            rulePrefix + "2094",
            rulePrefix + "1186",
            rulePrefix + "108"
        );
        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
    }

//    @DisplayName("Test PackageUsage")
//    @Test
//    void testPackageUsage() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
//        checkOccurringIssues(findOccurringIssues(relevantIssueNumbers));
//    }

//    @DisplayName("Test DynamicBinding")
//    @Test
//    void testDynamicBinding() {
//        List<String> relevantIssueNumbers = List.of(rulePrefix + "");
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

    private List<Issue> findOccurringIssues(List<String> relevantIssueNumbers) {
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