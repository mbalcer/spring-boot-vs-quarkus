package pl.edu.pbs.csvjsonconverter.service;

import io.smallrye.mutiny.Uni;
import org.json.JSONObject;
import pl.edu.pbs.csvjsonconverter.model.Request;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ApplicationScoped
public class CsvToJsonService {

    private final FileService fileService;

    public CsvToJsonService(FileService fileService) {
        this.fileService = fileService;
    }

    public Uni<List<String>> convertCsvToJson(Request request) {
        return fileService.readFile(request.getPath())
                .map(lines -> Arrays.stream(lines).map(line -> {
                    String[] split = line.split(request.getSeparator().getValue());

                    StringBuilder jsonObject = new StringBuilder("{");
                    IntStream.range(0, split.length)
                            .forEach(i -> jsonObject.append("title" + i + ": " + split[i] + ",\n"));

                    jsonObject.append("}");

                    return jsonObject.toString();
                }).collect(Collectors.toList()));
    }
}
