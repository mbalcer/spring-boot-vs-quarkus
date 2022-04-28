package pl.edu.pbs.csvjsonconverter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pbs.csvjsonconverter.service.CsvToJsonService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/json", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class JsonController {
    private final CsvToJsonService csvToJsonService;

    @GetMapping(path = "/small", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getAllProjects() {
        return csvToJsonService.getJson("src/main/resources/data/username.csv");
    }
}
