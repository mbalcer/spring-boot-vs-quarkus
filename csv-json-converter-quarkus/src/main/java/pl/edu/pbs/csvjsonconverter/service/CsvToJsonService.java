package pl.edu.pbs.csvjsonconverter.service;

import io.smallrye.mutiny.Uni;
import pl.edu.pbs.csvjsonconverter.model.Request;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CsvToJsonService {

    private final FileService fileService;

    public CsvToJsonService(FileService fileService) {
        this.fileService = fileService;
    }

    public Uni<List<String>> convertCsvToJson(Request request) {
        return fileService.readFile(request.getPath())
                .onItem()
                .transform(lines -> Arrays.stream(lines).collect(Collectors.toList()));
    }
}
