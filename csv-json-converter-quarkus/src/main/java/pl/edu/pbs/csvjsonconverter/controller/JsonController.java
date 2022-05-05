package pl.edu.pbs.csvjsonconverter.controller;

import io.smallrye.mutiny.Multi;
import pl.edu.pbs.csvjsonconverter.service.FileService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/json")
public class JsonController {

    private final FileService fileService;

    public JsonController(FileService fileService) {
        this.fileService = fileService;
    }

    @GET
    @Path("/small")
    @Produces("text/event-stream")
    public Multi<String> getSmallJsonFile() {
        return fileService.readFile("../data/small.csv");
    }
}