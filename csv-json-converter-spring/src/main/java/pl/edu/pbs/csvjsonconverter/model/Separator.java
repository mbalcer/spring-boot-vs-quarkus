package pl.edu.pbs.csvjsonconverter.model;

import com.fasterxml.jackson.annotation.JsonCreator;

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

    @Override
    public String toString() {
        return value;
    }

    @JsonCreator
    public static Separator fromValue(String sep) {
        for (Separator s : Separator.values()) {
            if (s.value.equals(sep)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unexpected separator");
    }
}
