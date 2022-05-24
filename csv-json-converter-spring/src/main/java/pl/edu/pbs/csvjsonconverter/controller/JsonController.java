package pl.edu.pbs.csvjsonconverter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.edu.pbs.csvjsonconverter.model.Request;
import pl.edu.pbs.csvjsonconverter.model.Separator;
import pl.edu.pbs.csvjsonconverter.service.CsvToJsonService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/json", produces = MediaType.APPLICATION_NDJSON_VALUE)
@RequiredArgsConstructor
public class JsonController {
    private final CsvToJsonService csvToJsonService;

    @GetMapping("/small")
    public Flux<String> getSmallJsonFile() {
        Request request = new Request(Separator.COMMA, false, "small.csv");
        return csvToJsonService.getJson(request);
    }

    @GetMapping("/medium")
    public Flux<String> getMediumJsonFile() {
        Request request = new Request(Separator.SEMI_COLON, false, "medium.csv");
        return csvToJsonService.getJson(request);
    }

    @GetMapping("/large")
    public Flux<String> getLargeJsonFile() {
        Request request = new Request(Separator.SEMI_COLON, false, "large.csv");
        return csvToJsonService.getJson(request);
    }

    @PostMapping
    public Flux<String> convertCsvToJson(@RequestBody Request request) {
        return csvToJsonService.getJson(request);
    }
}
