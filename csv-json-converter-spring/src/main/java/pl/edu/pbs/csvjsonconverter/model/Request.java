package pl.edu.pbs.csvjsonconverter.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class Request {
    private String path;
    private Separator separator;
    private boolean parseNumber;

    private static final String PATH_TO_DATA = "../data/";

    public Request(String fileName, Separator separator, boolean parseNumber) {
        this.path = PATH_TO_DATA + fileName;
        this.separator = separator;
        this.parseNumber = parseNumber;
    }
}
