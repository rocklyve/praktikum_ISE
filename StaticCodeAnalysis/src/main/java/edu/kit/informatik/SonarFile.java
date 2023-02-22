package edu.kit.informatik;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.jetbrains.annotations.Nullable;
import org.sonarsource.sonarlint.core.analysis.api.ClientInputFile;

public class SonarFile implements ClientInputFile {

    private final Path root;
    private final Path filePath;


    public SonarFile(Path root, Path filePath){
        this.root = root;
        this.filePath = filePath;
    }

    @Override
    public String getPath() {
        return filePath.toString();
    }

    @Override
    public boolean isTest() {
        return false;
    }

    @Nullable
    @Override
    public Charset getCharset() {
        return Charset.defaultCharset();
    }

    @Override
    public <G> G getClientObject() {
        return null;
    }

    @Override
    public InputStream inputStream() throws IOException {
        return new ByteArrayInputStream(Files.readAllBytes(filePath));
    }

    @Override
    public String contents() throws IOException {
        return Files.readString(filePath);
    }

    @Override
    public String relativePath() {
        return root.relativize(filePath).toString();
    }

    @Override
    public URI uri() {
        return filePath.toUri();
    }
}
