package pl.edu.pbs.csvjsonconverter.controller;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import pl.edu.pbs.csvjsonconverter.model.Request;
import pl.edu.pbs.csvjsonconverter.model.Separator;
import pl.edu.pbs.csvjsonconverter.service.CsvToJsonService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/json")
@Produces("application/x-ndjson")
public class JsonController {

    private final CsvToJsonService csvToJsonService;

    public JsonController(CsvToJsonService csvToJsonService) {
        this.csvToJsonService = csvToJsonService;
    }

    @GET
    @Path("/small")
    public Uni<List<String>> getSmallJsonFile() {
        Request request = new Request(Separator.COMMA, false, "small.csv");
        return csvToJsonService.convertCsvToJson(request);
    }

    @GET
    @Path("/medium")
    public Uni<List<String>> getMediumJsonFile() {
        Request request = new Request(Separator.SEMI_COLON, false, "medium.csv");
        return csvToJsonService.convertCsvToJson(request);
    }

    @GET
    @Path("/large")
    public Uni<List<String>> getLargeJsonFile() {
        Request request = new Request(Separator.SEMI_COLON, false, "large.csv");
        return csvToJsonService.convertCsvToJson(request);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<List<String>> convertCsvToJson(Request request) {
        return csvToJsonService.convertCsvToJson(request);
    }
}