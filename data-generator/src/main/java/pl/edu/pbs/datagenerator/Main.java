package pl.edu.pbs.datagenerator;

import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        new CommandLine(new DataGeneratorCmd()).execute(args);
    }
}
