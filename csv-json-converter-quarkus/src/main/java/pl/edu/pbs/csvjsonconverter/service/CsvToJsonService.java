package pl.edu.pbs.csvjsonconverter.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.json.JSONObject;
import pl.edu.pbs.csvjsonconverter.model.Request;
import pl.edu.pbs.csvjsonconverter.util.TypesUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CsvToJsonService {

    private final FileService fileService;

    public CsvToJsonService(FileService fileService) {
        this.fileService = fileService;
    }

    public Multi<String> convertCsvToJson(Request request) {
        Multi<String> file = fileService.readFile(request.getPath());

        file.select()
                .first()
                .onItem()
                .transform(line -> line.split(request.getSeparator().getValue()))
                .subscribe()
                .with(request::setTitles);

        return file.skip()
                .first()
                .onItem()
                .transform(line -> line.split(request.getSeparator().getValue()))
                .onItem()
                .transform(values -> {
                    JSONObject jsonObject = new JSONObject();

                    Multi.createFrom().range(0, values.length)
                            .subscribe()
                            .with(i -> {
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
                    return jsonObject;
                })
                .onItem()
                .transform(JSONObject::toString);
    }
}
