package pl.edu.pbs.csvjsonconverter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Request {
    private String path;
    private Separator separator;
    private boolean parseTypes;
    private String[] titles;

    private static final String PATH_TO_DATA = "../data/";

    public Request(Separator separator, boolean parseTypes, String fileName) {
        this.path = PATH_TO_DATA + fileName;
        this.separator = separator;
        this.parseTypes = parseTypes;
    }
}
