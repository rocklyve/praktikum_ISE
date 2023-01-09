import edu.kit.informatik.SonarFile;
import org.apache.commons.lang.ArrayUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.sonar.api.utils.command.StringStreamConsumer;
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

public class SonarQubeTests {
    private static ArrayList<Issue> issues;

    // TODO: please change to String[]  or ArrayList<String>
    private static Object[] issueNumbers;
    private static String rulePrefix = "java:S";

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        issues = new ArrayList<Issue>();

        Path javaPlugin = getJavaPlugin();
        // TODO: Check also for a relative path
        var path = Path.of("src/main/java", "edu/kit/informatik/").toAbsolutePath();
        var javaFiles = getFiles(path);

        StandaloneGlobalConfiguration configuration = StandaloneGlobalConfiguration.builder().addEnabledLanguage(Language.JAVA).addPlugins(javaPlugin).setWorkDir(path).build();
        StandaloneSonarLintEngine standaloneSonarLintEngine = new StandaloneSonarLintEngineImpl(configuration);
        StandaloneAnalysisConfiguration sac = StandaloneAnalysisConfiguration.builder().setBaseDir(path).addInputFiles(javaFiles).build();
        standaloneSonarLintEngine.analyze(sac, SonarQubeTests::listen, (formattedMessage, level) -> System.out.println(formattedMessage), null);
        standaloneSonarLintEngine.stop();

        issueNumbers = issues.stream().map(x -> x.getRuleKey()).toArray();
    }

    @DisplayName("Test NonFinalAttributesShouldBeFinal")
    @Test
    void testNonFinalAttributesShouldBeFinal() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test SystemDependentLineBreak")
    @Test
    void testSystemDependentLineBreak() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test RawType")
    @Test
    void testRawType() {
        ArrayList<String> relevantIssueNumbers = new ArrayList<String>(Arrays.asList(rulePrefix + "3740"));
        assertTrue(!isIssueAppearing(relevantIssueNumbers));
    }

    @DisplayName("Test ConcreteClassInsteadOfInterface")
    @Test
    void testConcreteClassInsteadOfInterface() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test AssertInsteadOfIfLoop")
    @Test
    void testAssertInsteadOfIfLoop() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test ObjectInsteadOfConcreteClass")
    @Test
    void testObjectInsteadOfConcreteClass() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test PublicEnumInsideClassAndNotInSeparateFile")
    @Test
    void testPublicEnumInsideClassAndNotInSeparateFile() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test VisibilityAsLowAsPossible")
    @Test
    void testVisibilityAsLowAsPossible() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test Code Duplication")
    @Test
    void testCodeDuplication() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test CodeDuplicationRepetitionsFixableByInheritance")
    @Test
    void testCodeDuplicationRepetitionsFixableByInheritance() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test InheritanceInsteadOfEnums")
    @Test
    void testInheritanceInsteadOfEnums() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test OperationsInsteadOfDomain")
    @Test
    void testOperationsInsteadOfDomain() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test HardcodedLogic")
    @Test
    void testHardcodedLogic() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test StringReferences")
    @Test
    void testStringReferences() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test ExceptionsForControlFlow")
    @Test
    void testExceptionsForControlFlow() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test TryCatchBlock")
    @Test
    void testTryCatchBlock() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test UnspecifiedErrorMessage")
    @Test
    void testUnspecifiedErrorMessage() {
        assertTrue(issues.size() == 0);
    }


    @DisplayName("Test WrongLoopType")
    @Test
    void testWrongLoopType() {
        assertTrue(issues.size() == 0);
    }


    @DisplayName("Test UnnecessaryComplexity")
    @Test
    void testUnnecessaryComplexity() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test ClumsySolution")
    @Test
    void testClumsySolution() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test ParsingIntegerValues")
    @Test
    void testParsingIntegerValues() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test UtilityClass")
    @Test
    void testUtilityClass() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test UnsafeCast")
    @Test
    void testUnsafeCast() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test EmptyConstructor")
    @Test
    void testEmptyConstructor() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test MeaninglessConstant")
    @Test
    void testMeaninglessConstant() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test Scanner")
    @Test
    void testScanner() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test UnusedElement")
    @Test
    void testUnusedElement() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test MissingThrowsInMethodSignature")
    @Test
    void testMissingThrowsInMethodSignature() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test PublicEnumInClass")
    @Test
    void testPublicEnumInClass() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test ParsingIntegerValues")
    @Test
    void testClassOfConstants() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test TrivialJavaDoc")
    @Test
    void testTrivialJavaDoc() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test BadNaming")
    @Test
    void testBadNaming() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test DataEncapsulationViolation")
    @Test
    void testDataEncapsulationViolation() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test SeparationOfLogicAndInteraction")
    @Test
    void testSeparationOfLogicAndInteraction() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test TooComplexCode")
    @Test
    void testTooComplexCode() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test StaticMethods")
    @Test
    void testStaticMethods() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test StaticAttributeOfInstanceAttribute")
    @Test
    void testStaticAttributeOfInstanceAttribute() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test FinalModifier")
    @Test
    void testFinalModifier() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test AssertVsIf")
    @Test
    void testAssertVsIf() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test ParsingIntegerValues")
    @Test
    void testJavaAPI() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test ToStringVsEquals")
    @Test
    void testToStringVsEquals() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test DoNotUseObject")
    @Test
    void testDoNotUseObject() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test ClassInsteadOfInterface")
    @Test
    void testClassInsteadOfInterface() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test EnumForClosedSet")
    @Test
    void testEnumForClosedSet() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test EmptyBlock")
    @Test
    void testEmptyBlock() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test PackageUsage")
    @Test
    void testPackageUsage() {
        assertTrue(issues.size() == 0);
    }

    @DisplayName("Test DynamicBinding")
    @Test
    void testDynamicBinding() {
        assertTrue(issues.size() == 0);
    }

    @AfterAll
    public static void tearDownAfterClass() {
        issues = null;
    }

    private boolean isIssueAppearing(ArrayList<String> relevantIssueNumbers) {
        for (String issueNumber: relevantIssueNumbers) {
            if (ArrayUtils.contains(issueNumbers, issueNumber)) {
                return true;
            }
        }
        return false;
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
            if (filePath.getFileName().toString().endsWith(".java")) {
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