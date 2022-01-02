package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;


@Command(name = "genDiff", mixinStandardHelpOptions = true, version = "genDiff 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private File filepath1;

    @Parameters(index = "1", description = "path to second file")
    private File filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format",
            defaultValue = "stylish", description = "output format [default: stylish]")
    private String format = "stylish";


    /**
    Only print method generate().
    */
    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(filepath1, filepath2));
        return 0;
    }

    public static void main(String... args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
