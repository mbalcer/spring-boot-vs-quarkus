package pl.edu.pbs.csvjsonconverter.service;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.buffer.Buffer;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FileService {
    private Vertx vertx;

    public FileService() {
        vertx = Vertx.vertx();
    }

    public Uni<String[]> readFile(String path) {
        return vertx.fileSystem()
                .readFile(path)
                .map(Buffer::toString)
                .map(content -> content.split("\n"));
    }
}
