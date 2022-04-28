package pl.edu.pbs.csvjsonconverter.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.edu.pbs.csvjsonconverter.model.Request;
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
                            .forEach(i -> object.put(titles[i], values[i]));

                    return object;
                })
                .map(JSONObject::toString);
    }
}
