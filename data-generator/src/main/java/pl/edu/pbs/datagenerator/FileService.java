package pl.edu.pbs.datagenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileService {
    public void saveFile(Path path, List<String> lines) {
        if (!Files.isRegularFile(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new IllegalArgumentException("Unable to create file " + path);
            }
        }

        try {
            Files.write(path, lines);
        } catch (IOException e) {
            throw new IllegalArgumentException("Data cannot be written to file" + path);
        }
    }
}
