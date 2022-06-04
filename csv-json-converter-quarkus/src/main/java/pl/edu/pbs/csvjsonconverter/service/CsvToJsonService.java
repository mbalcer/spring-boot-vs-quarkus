package pl.edu.pbs.csvjsonconverter.service;

import io.smallrye.mutiny.Uni;
import org.json.JSONObject;
import pl.edu.pbs.csvjsonconverter.model.Request;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CsvToJsonService {

    private final FileService fileService;

    public CsvToJsonService(FileService fileService) {
        this.fileService = fileService;
    }

    public Uni<List<String>> convertCsvToJson(Request request) {
        Uni<String[]> file = fileService.readFile(request.getPath());

        file.onItem()
                .transform(lines -> lines[0])
                .onItem()
                .transform(line -> line.split(request.getSeparator().getValue()))
                .subscribe()
                .with(request::setTitles);

        return file.onItem()
                .transform(lines -> {
                    List<String> json = new ArrayList<>();
                    for (int i = 0; i < lines.length; i++) {
                        String[] split = lines[i].split(request.getSeparator().getValue());
                        JSONObject jsonObject = new JSONObject();
                        for (int j = 0; j < split.length; j++) {
                            jsonObject.put(request.getTitles()[j], split[j]);
                        }
                        json.add(jsonObject.toString());
                    }

                    return json;
                });
    }
}
