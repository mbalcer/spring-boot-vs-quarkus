package pl.edu.pbs.csvjsonconverter.service;

import io.smallrye.mutiny.Uni;
import org.json.JSONObject;
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
        Uni<String[]> file = fileService.readFile(request.getPath());

        file.map(lines -> lines[0])
                .map(line -> line.split(request.getSeparator().getValue()))
                .subscribe()
                .with(request::setTitles);

        return file.map(lines -> Arrays.stream(lines)
                .map(line -> line.split(request.getSeparator().getValue()))
                .map(values -> {
                    JSONObject jsonObject = new JSONObject();
                    for (int i = 0; i < values.length; i++) {
                        jsonObject.put(request.getTitles()[i], values[i]);
                    }
                    return jsonObject.toString();
                })
                .collect(Collectors.toList()));
    }
}
