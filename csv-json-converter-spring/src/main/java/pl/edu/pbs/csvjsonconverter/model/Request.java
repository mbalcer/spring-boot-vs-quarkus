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
    private boolean parseNumber;
}
