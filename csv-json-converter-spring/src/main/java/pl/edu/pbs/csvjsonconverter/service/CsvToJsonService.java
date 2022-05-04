package pl.edu.pbs.csvjsonconverter.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.edu.pbs.csvjsonconverter.model.Request;
import pl.edu.pbs.csvjsonconverter.util.TypesUtils;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CsvToJsonService {

    private final FileService fileService;

    public Flux<String> getJson(Request request) {
        Flux<String> file = fileService.readFile(request.getPath());
        String[] titles = file.next()
                .share()
                .block()
                .split(request.getSeparator().getValue());

        return file
                .map(line -> line.split(request.getSeparator().getValue()))
                .map(values -> {
                    JSONObject object = new JSONObject();
                    IntStream.range(0, values.length)
                            .forEach(i -> {
                                if (request.isParseTypes() && values[i].equals("null")) {
                                    object.put(titles[i], JSONObject.NULL);
                                } else if (request.isParseTypes() && TypesUtils.isBoolean(values[i])) {
                                    object.put(titles[i], Boolean.parseBoolean(values[i]));
                                } else if (request.isParseTypes() && TypesUtils.isNumeric(values[i])) {
                                    object.put(titles[i], Double.parseDouble(values[i]));
                                } else {
                                    object.put(titles[i], values[i]);
                                }
                            });
                    return object;
                })
                .map(JSONObject::toString);
    }
}