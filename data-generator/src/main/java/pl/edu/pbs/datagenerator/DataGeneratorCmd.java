package pl.edu.pbs.datagenerator;

import picocli.CommandLine;

@CommandLine.Command(name = "dg", mixinStandardHelpOptions = true)
public class DataGeneratorCmd implements Runnable {
    @CommandLine.Option(names = {"-r", "--rows"})
    private Integer rows;

    @CommandLine.Option(names = {"-c", "--columns"})
    private Integer columns;

    @CommandLine.Option(names = {"-o", "--output"})
    private String outputPath;

    @CommandLine.Option(names = {"-s", "--separator"})
    private String separator;

    @Override
    public void run() {
        System.out.println("rows: " + rows + ", columns: " + columns + ", outputPath: " + outputPath + ", separator: " + separator);
    }
}
