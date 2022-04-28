package pl.edu.pbs.csvjsonconverter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pbs.csvjsonconverter.model.Request;
import pl.edu.pbs.csvjsonconverter.model.Separator;
import pl.edu.pbs.csvjsonconverter.service.CsvToJsonService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/json", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
@RequiredArgsConstructor
public class JsonController {
    private final CsvToJsonService csvToJsonService;

    @GetMapping("/small")
    public Flux<String> getAllProjects() {
        Request request = new Request("small.csv", Separator.SEMI_COLON, false);
        return csvToJsonService.getJson(request);
    }
}
