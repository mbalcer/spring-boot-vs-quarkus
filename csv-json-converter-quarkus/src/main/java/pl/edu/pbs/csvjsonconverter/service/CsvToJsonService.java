package pl.edu.pbs.csvjsonconverter.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.json.JSONObject;
import pl.edu.pbs.csvjsonconverter.model.Request;
import pl.edu.pbs.csvjsonconverter.util.TypesUtils;

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
                    StringBuilder jsonObject = new StringBuilder("{");

                    Multi.createFrom().range(0, values.length)
                            .subscribe()
                            .with(i -> {
                                String[] titles = request.getTitles();
                                if (titles != null && titles[i].length() > 0) {
                                    if (request.isParseTypes() && values[i].equals("null")) {
                                        jsonObject.append(titles[i] + ": " + JSONObject.NULL + ",\n");
                                    } else if (request.isParseTypes() && TypesUtils.isBoolean(values[i])) {
                                        jsonObject.append(titles[i] + ": " + Boolean.parseBoolean(values[i]) + ",\n");
                                    } else if (request.isParseTypes() && TypesUtils.isNumeric(values[i])) {
                                        jsonObject.append(titles[i] + ": " + Double.parseDouble(values[i]) + ",\n");
                                    } else {
                                        jsonObject.append(titles[i] + ": " + values[i] + ",\n");
                                    }
                                }
                            });
                    return jsonObject.toString();
                });
    }
}
