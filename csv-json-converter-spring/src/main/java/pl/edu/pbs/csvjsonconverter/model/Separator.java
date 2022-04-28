package pl.edu.pbs.csvjsonconverter.model;

public enum Separator {
    COMMA(","),
    SEMI_COLON(";"),
    TAB("\t");

    private String value;

    Separator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
