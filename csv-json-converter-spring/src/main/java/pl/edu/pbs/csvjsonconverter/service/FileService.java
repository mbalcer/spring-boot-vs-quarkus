package pl.edu.pbs.csvjsonconverter.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.BaseStream;

@Service
public class FileService {

    public Flux<String> readFile(String path) {
        return Flux.using(() -> Files.lines(Path.of(path)),
                Flux::fromStream,
                BaseStream::close);
    }
}
