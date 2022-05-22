package pl.edu.pbs.datagenerator;

import picocli.CommandLine;

import java.nio.file.Path;
import java.util.List;

@CommandLine.Command(name = "dg",
        mixinStandardHelpOptions = true,
        sortOptions = false,
        version = "Data Generator 1.0",
        header = "Generate fake data to CSV file.",
        description = "The command generate fake data and save to the CSV file given as output path. The amount of data and the separator is regulated with the available options.",
        synopsisHeading = "%n",
        descriptionHeading = "%nDescription:%n",
        optionListHeading = "%nOptions:%n")
public class DataGeneratorCmd implements Runnable {
    @CommandLine.Option(names = {"-r", "--rows"}, description = "Number of data lines.", required = true)
    private Integer rows;

    @CommandLine.Option(names = {"-c", "--columns"}, description = "Number of data columns. Min: 1. Max: 15.", required = true)
    private Integer columns;

    @CommandLine.Option(names = {"-o", "--output"}, defaultValue = "../data/1.csv", description = "Path to the .csv file with output file name.")
    private String outputPath;

    @CommandLine.Option(names = {"-s", "--separator"}, defaultValue = ";", description = "Data separator. Available separator: semi colon(;), comma(,) and tab(\\t).")
    private String separator;

    @Override
    public void run() {
        if (!separator.equals(";") && !separator.equals("\t") && !separator.equals(",")) {
            throw new IllegalArgumentException("Unknown separator. Available separator: semi colon(;), comma(,) and tab(\\t)");
        }
        if (columns < 1 || columns > 15) {
            throw new IllegalArgumentException("The columns option must be in the range 1 to 15.");
        }
        if (rows < 1) {
            throw new IllegalArgumentException("The rows option must be greater than 0.");
        }

        DataGenerator dataGenerator = new DataGenerator();
        List<String> data = dataGenerator.generate(rows, columns, separator);

        FileService fileService = new FileService();
        fileService.saveFile(Path.of(outputPath), data);

        data.forEach(System.out::println);
    }
}
