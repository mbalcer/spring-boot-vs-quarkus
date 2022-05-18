package pl.edu.pbs.datagenerator;

import picocli.CommandLine;

import java.nio.file.Path;
import java.util.List;

@CommandLine.Command(name = "dg", mixinStandardHelpOptions = true)
public class DataGeneratorCmd implements Runnable {
    @CommandLine.Option(names = {"-r", "--rows"})
    private Integer rows;

    @CommandLine.Option(names = {"-c", "--columns"})
    private Integer columns;

    @CommandLine.Option(names = {"-o", "--output"}, defaultValue = "../data/1.csv")
    private String outputPath;

    @CommandLine.Option(names = {"-s", "--separator"}, defaultValue = ";")
    private String separator;

    @Override
    public void run() {
        if (!separator.equals(";") && !separator.equals("\t") && !separator.equals(",")) {
            throw new IllegalArgumentException("Unknown separator. Available separator: semi colon(;), comma(,) and tab(\\t)");
        }

        DataGenerator dataGenerator = new DataGenerator();
        List<String> data = dataGenerator.generate(rows, columns, separator);

        FileService fileService = new FileService();
        fileService.saveFile(Path.of(outputPath), data);

        data.forEach(System.out::println);
    }
}
