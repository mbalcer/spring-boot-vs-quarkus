package pl.edu.pbs.csvjsonconverter.model;

import java.util.Arrays;

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

    public Request(String path, Separator separator, boolean parseTypes, String[] titles) {
        this.path = path;
        this.separator = separator;
        this.parseTypes = parseTypes;
        this.titles = titles;
    }

    public Request() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Separator getSeparator() {
        return separator;
    }

    public void setSeparator(Separator separator) {
        this.separator = separator;
    }

    public boolean isParseTypes() {
        return parseTypes;
    }

    public void setParseTypes(boolean parseTypes) {
        this.parseTypes = parseTypes;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    @Override
    public String toString() {
        return "Request{" +
                "path='" + path + '\'' +
                ", separator=" + separator +
                ", parseTypes=" + parseTypes +
                ", titles=" + Arrays.toString(titles) +
                '}';
    }
}
