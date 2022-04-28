package pl.edu.pbs.csvjsonconverter.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CsvToJsonService {

    private final FileService fileService;

    public Flux<String> getJson(String path) {
        Flux<String> file = fileService.readFile(path);
        String[] titles = file.next()
                .share()
                .block()
                .split(";");

        return file
                .map(line -> line.split(";"))
                .map(values -> {
                    JSONObject object = new JSONObject();
                    IntStream.range(0, values.length)
                            .forEach(i -> object.put(titles[i], values[i]));

                    return object;
                })
                .map(JSONObject::toString);
    }
}
