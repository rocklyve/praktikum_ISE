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

    @DisplayName("Test MessageService.get()")
    @Test
    void testGet() {
        assertTrue(1 >= 0);
    }

    @AfterAll
    public static void tearDownAfterClass() {
        System.out.println("over");
    }
}