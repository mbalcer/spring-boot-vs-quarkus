package pl.edu.pbs.csvjsonconverter.service;

import io.smallrye.mutiny.Multi;
import org.json.JSONObject;
import pl.edu.pbs.csvjsonconverter.model.Request;

import javax.enterprise.context.ApplicationScoped;
import java.util.stream.IntStream;

@ApplicationScoped
public class CsvToJsonService {

    private final FileService fileService;

    public CsvToJsonService(FileService fileService) {
        this.fileService = fileService;
    }

    public Multi<String> convertCsvToJson(Request request) {
        Multi<String> file = fileService.readFile(request.getPath());

        file.select()
                .first()
                .onItem()
                .transform(line -> line.split(request.getSeparator().getValue()))
                .subscribe()
                .with(request::setTitles);

        return file.skip()
                .first()
                .onItem()
                .transform(line -> line.split(request.getSeparator().getValue()))
                .onItem()
                .transform(values -> {
                    JSONObject jsonObject = new JSONObject();

                    IntStream.range(0, values.length)
                            .forEach(i -> {
                                jsonObject.put(request.getTitles()[i], values[i]);
                            });

                    return jsonObject;
                })
                .onItem()
                .transform(JSONObject::toString);
    }
}
