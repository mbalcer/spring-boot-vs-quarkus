package pl.edu.pbs.csvjsonconverter.service;

import io.smallrye.mutiny.Multi;
import org.javaync.io.AsyncFiles;

import javax.enterprise.context.ApplicationScoped;
import java.nio.file.Path;

@ApplicationScoped
public class FileService {

    public Multi<String> readFile(String path) {
        return Multi.createFrom().publisher(AsyncFiles.lines(Path.of(path).toAbsolutePath()));
    }

}
