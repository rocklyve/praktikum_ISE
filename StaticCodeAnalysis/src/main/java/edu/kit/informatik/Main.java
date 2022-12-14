package edu.kit.informatik;

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

public class Main {
    public static void main(String[] args) throws IOException {
        Path javaPlugin = getJavaPlugin();
        var path = Path.of("src/main/java", "edu/kit/informatik/").toAbsolutePath();
        var javaFiles = getFiles(path);

        StandaloneGlobalConfiguration configuration = StandaloneGlobalConfiguration.builder().addEnabledLanguage(Language.JAVA).addPlugins(javaPlugin).setWorkDir(path).build();
        StandaloneSonarLintEngine standaloneSonarLintEngine = new StandaloneSonarLintEngineImpl(configuration);
        StandaloneAnalysisConfiguration sac = StandaloneAnalysisConfiguration.builder().setBaseDir(path).addInputFiles(javaFiles).build();
        standaloneSonarLintEngine.analyze(sac, Main::listen, (formattedMessage, level) -> System.out.println(formattedMessage), null);
        standaloneSonarLintEngine.stop();
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
        System.err.println(i);
    }
}
