package pl.edu.pbs.csvjsonconverter.service;

import org.javaync.io.AsyncFiles;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.nio.file.Path;

@Service
public class FileService {

    public Flux<String> readFile(String path) {
        return Flux.from(AsyncFiles.lines(Path.of(path).toAbsolutePath()));
    }
}
