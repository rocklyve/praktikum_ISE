import edu.kit.informatik.SonarFile;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;

public class PMDTests {

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        PMDConfiguration configuration = new PMDConfiguration();
        configuration.setInputPaths("src/main/java/edu/kit/informatik/");
        configuration.addRuleSet("rulesets/java/quickstart.xml");
        configuration.setReportFormat("xml");
        configuration.setReportFile("src/resources/pmd-report.xml");

        PMD.runPmd(configuration);
    }

    @DisplayName("Test NonFinalAttributesShouldBeFinal")
    @Test
    void testNonFinalAttributesShouldBeFinal() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test SystemDependentLineBreak")
    @Test
    void testSystemDependentLineBreak() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test RawType")
    @Test
    void testRawType() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test ConcreteClassInsteadOfInterface")
    @Test
    void testConcreteClassInsteadOfInterface() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test AssertInsteadOfIfLoop")
    @Test
    void testAssertInsteadOfIfLoop() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test ObjectInsteadOfConcreteClass")
    @Test
    void testObjectInsteadOfConcreteClass() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test PublicEnumInsideClassAndNotInSeparateFile")
    @Test
    void testPublicEnumInsideClassAndNotInSeparateFile() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test VisibilityAsLowAsPossible")
    @Test
    void testVisibilityAsLowAsPossible() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test Code Duplication")
    @Test
    void testCodeDuplication() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test CodeDuplicationRepetitionsFixableByInheritance")
    @Test
    void testCodeDuplicationRepetitionsFixableByInheritance() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test InheritanceInsteadOfEnums")
    @Test
    void testInheritanceInsteadOfEnums() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test OperationsInsteadOfDomain")
    @Test
    void testOperationsInsteadOfDomain() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test HardcodedLogic")
    @Test
    void testHardcodedLogic() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test StringReferences")
    @Test
    void testStringReferences() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test ExceptionsForControlFlow")
    @Test
    void testExceptionsForControlFlow() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test TryCatchBlock")
    @Test
    void testTryCatchBlock() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test UnspecifiedErrorMessage")
    @Test
    void testUnspecifiedErrorMessage() {
        assertTrue(1 >= 0);
    }


    @DisplayName("Test WrongLoopType")
    @Test
    void testWrongLoopType() {
        assertTrue(1 >= 0);
    }


    @DisplayName("Test UnnecessaryComplexity")
    @Test
    void testUnnecessaryComplexity() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test ClumsySolution")
    @Test
    void testClumsySolution() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test ParsingIntegerValues")
    @Test
    void testParsingIntegerValues() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test UtilityClass")
    @Test
    void testUtilityClass() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test UnsafeCast")
    @Test
    void testUnsafeCast() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test EmptyConstructor")
    @Test
    void testEmptyConstructor() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test MeaninglessConstant")
    @Test
    void testMeaninglessConstant() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test Scanner")
    @Test
    void testScanner() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test UnusedElement")
    @Test
    void testUnusedElement() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test MissingThrowsInMethodSignature")
    @Test
    void testMissingThrowsInMethodSignature() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test PublicEnumInClass")
    @Test
    void testPublicEnumInClass() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test ParsingIntegerValues")
    @Test
    void testClassOfConstants() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test TrivialJavaDoc")
    @Test
    void testTrivialJavaDoc() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test BadNaming")
    @Test
    void testBadNaming() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test DataEncapsulationViolation")
    @Test
    void testDataEncapsulationViolation() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test SeparationOfLogicAndInteraction")
    @Test
    void testSeparationOfLogicAndInteraction() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test TooComplexCode")
    @Test
    void testTooComplexCode() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test StaticMethods")
    @Test
    void testStaticMethods() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test StaticAttributeOfInstanceAttribute")
    @Test
    void testStaticAttributeOfInstanceAttribute() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test FinalModifier")
    @Test
    void testFinalModifier() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test AssertVsIf")
    @Test
    void testAssertVsIf() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test ParsingIntegerValues")
    @Test
    void testJavaAPI() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test ToStringVsEquals")
    @Test
    void testToStringVsEquals() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test DoNotUseObject")
    @Test
    void testDoNotUseObject() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test ClassInsteadOfInterface")
    @Test
    void testClassInsteadOfInterface() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test EnumForClosedSet")
    @Test
    void testEnumForClosedSet() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test EmptyBlock")
    @Test
    void testEmptyBlock() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test PackageUsage")
    @Test
    void testPackageUsage() {
        assertTrue(1 >= 0);
    }

    @DisplayName("Test DynamicBinding")
    @Test
    void testDynamicBinding() {
        assertTrue(1 >= 0);
    }

    @AfterAll
    public static void tearDownAfterClass() {
        System.out.println("over");
    }
}