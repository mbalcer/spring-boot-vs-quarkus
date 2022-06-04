package pl.edu.pbs.csvjsonconverter.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.file.OpenOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.core.file.AsyncFile;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FileService {
    private Vertx vertx;

    public FileService() {
        vertx = Vertx.vertx();
    }

    public Multi<String> readFile(String path) {
        Uni<AsyncFile> uni = vertx.fileSystem()
                .open(path, new OpenOptions().setRead(true));
        return uni.onItem()
                .transformToMulti(AsyncFile::toMulti)
                .map(Buffer::toString);
    }
}
