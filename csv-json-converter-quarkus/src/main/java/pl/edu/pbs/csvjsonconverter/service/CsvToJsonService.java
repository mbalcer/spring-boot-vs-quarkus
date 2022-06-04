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

    public Uni<List<String>> convertCsvToJson(Request request) {
        Uni<String[]> file = fileService.readFile(request.getPath());

        file.onItem()
                .transform(lines -> lines[0])
                .onItem()
                .transform(line -> line.split(request.getSeparator().getValue()))
                .subscribe()
                .with(request::setTitles);

        return file.onItem()
                .transform(lines -> Arrays.copyOfRange(lines, 1, lines.length))
                .onItem()
                .transform(lines -> Arrays.stream(lines).map(line -> line.split(request.getSeparator().getValue())).collect(Collectors.toList()))
                .onItem()
                .transform(lines -> lines.stream()
                            .map(values -> {
                                JSONObject jsonObject = new JSONObject();

                                IntStream.range(0, values.length)
                                        .forEach(i -> {
                                            String[] titles = request.getTitles();
                                            if (titles != null && titles[i].length() > 0) {
                                                if (request.isParseTypes() && values[i].equals("null")) {
                                                    jsonObject.put(titles[i], JSONObject.NULL);
                                                } else if (request.isParseTypes() && TypesUtils.isBoolean(values[i])) {
                                                    jsonObject.put(titles[i], Boolean.parseBoolean(values[i]));
                                                } else if (request.isParseTypes() && TypesUtils.isNumeric(values[i])) {
                                                    jsonObject.put(titles[i], Double.parseDouble(values[i]));
                                                } else {
                                                    jsonObject.put(titles[i], values[i]);
                                                }
                                            }
                                        });
                                return jsonObject.toString();
                            })
                            .collect(Collectors.toList()));
    }
}
