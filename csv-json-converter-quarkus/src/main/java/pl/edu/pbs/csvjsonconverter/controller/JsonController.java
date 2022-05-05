package pl.edu.pbs.csvjsonconverter.controller;

import io.smallrye.mutiny.Multi;
import pl.edu.pbs.csvjsonconverter.model.Request;
import pl.edu.pbs.csvjsonconverter.model.Separator;
import pl.edu.pbs.csvjsonconverter.service.FileService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
        Request request = new Request(Separator.SEMI_COLON, false, "small.csv");
        return fileService.readFile(request.getPath());
    }
}