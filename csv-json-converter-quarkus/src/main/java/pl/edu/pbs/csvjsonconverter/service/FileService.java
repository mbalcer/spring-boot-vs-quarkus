package pl.edu.pbs.csvjsonconverter.service;

import io.smallrye.mutiny.Multi;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@ApplicationScoped
public class FileService {
    public Multi<String> readFile(String path) {
        Multi<String> file = null;
        try {
            file = Multi.createFrom().items(
                    Files.lines(Path.of(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
