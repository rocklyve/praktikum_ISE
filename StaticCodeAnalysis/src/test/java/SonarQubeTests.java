import edu.kit.informatik.SonarFile;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
import java.util.List;

public class SonarQubeTests {
    private static ArrayList<Issue> issues;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        System.out.println("beforeClass");

        issues = new ArrayList<Issue>();

        Path javaPlugin = getJavaPlugin();
        var path = Path.of("src/main/java", "edu/kit/informatik/").toAbsolutePath();
        var javaFiles = getFiles(path);

        StandaloneGlobalConfiguration configuration = StandaloneGlobalConfiguration.builder().addEnabledLanguage(Language.JAVA).addPlugins(javaPlugin).setWorkDir(path).build();
        StandaloneSonarLintEngine standaloneSonarLintEngine = new StandaloneSonarLintEngineImpl(configuration);
        StandaloneAnalysisConfiguration sac = StandaloneAnalysisConfiguration.builder().setBaseDir(path).addInputFiles(javaFiles).build();
        standaloneSonarLintEngine.analyze(sac, SonarQubeTests::listen, (formattedMessage, level) -> System.out.println(formattedMessage), null);
        standaloneSonarLintEngine.stop();
        System.out.println("beforeClass end");
    }

    @DisplayName("Test MessageService.get()")
    @Test
    void testGet() {
        assertTrue(issues.size() > 0);
    }

    @AfterAll
    public static void tearDownAfterClass() {
        issues = null;
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