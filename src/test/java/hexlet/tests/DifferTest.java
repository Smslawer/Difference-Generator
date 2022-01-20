package hexlet.tests;

import hexlet.code.Differ;
import hexlet.code.formatters.PlainFormat;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

class DifferTest {

    @Test
    void differTestWithoutFormat() throws IOException {
        String expected = Files.readString(Path.of("./src/test/resources/expected/expectedStylish.txt")
                .toAbsolutePath().normalize());
        String actualJson = Differ.generate("./src/test/resources/filepath1.json",
                "./src/test/resources/filepath2.json");
        assertThat(actualJson).isEqualTo(expected);
    }

    @Test
    void testDifferStylish() throws Exception {
        String expected = Files.readString(Path.of("./src/test/resources/expected/expectedStylish.txt")
                .toAbsolutePath().normalize());
        String actualJson = Differ.generate("./src/test/resources/filepath1.json",
                "./src/test/resources/filepath2.json", "stylish");
        assertThat(actualJson).isEqualTo(expected);

        String actualYaml = Differ.generate("./src/test/resources/fileyaml1.yml",
                "./src/test/resources/fileyaml2.yml", "stylish");
        assertThat(actualYaml).isEqualTo(expected);
    }

    @Test
    void testDifferPlain() throws Exception {
        String expected = Files.readString(Path.of("./src/test/resources/expected/expectedPlain.txt").
                toAbsolutePath().normalize());
        String actualJson = Differ.generate("./src/test/resources/filepath1.json",
                "./src/test/resources/filepath2.json", "plain");
        assertThat(actualJson).isEqualTo(expected);

        String actualYaml = Differ.generate("./src/test/resources/fileyaml1.yml",
                "./src/test/resources/fileyaml2.yml", "plain");
        assertThat(actualYaml).isEqualTo(expected);

        Map<String, List<String>> valueFilepath1 = Map.of("Key", new ArrayList<>());
        assertThat(PlainFormat.isArrayOrObject(valueFilepath1)).isEqualTo("[complex value]");
    }

    @Test
    void testDifferJson() throws Exception {
        String expected = Files.readString(Path.of("./src/test/resources/expected/expectedJson.txt").
                toAbsolutePath().normalize());

        String actualJson = Differ.generate("./src/test/resources/filepath1.json",
                "./src/test/resources/filepath2.json", "json");
        assertThat(actualJson).isEqualTo(expected);

        String actualYaml = Differ.generate("./src/test/resources/fileyaml1.yml",
                "./src/test/resources/fileyaml2.yml", "json");
        assertThat(actualYaml).isEqualTo(expected);
    }

    @Test
    void testDifferIfEmpty() {
        Throwable thrownJSONWithBraces = catchThrowable(() -> Differ.generate("./src/test/resources/filepath1.json",
                "./src/test/resources/expected/emptyJSON.json"));
        assertThat(thrownJSONWithBraces).isInstanceOf(IOException.class);
        assertThat(thrownJSONWithBraces.getMessage()).isEqualTo("one of the files is empty");

        Throwable thrownJSONWithoutBraces = catchThrowable(() -> Differ.generate("./src/test/resources/filepath1.json",
                "./src/test/resources/expected/emptyJSON2.json"));
        assertThat(thrownJSONWithoutBraces).isInstanceOf(IOException.class);
        assertThat(thrownJSONWithoutBraces.getMessage()).isEqualTo("one of the files is empty");

        Throwable thrownYAML = catchThrowable(() -> Differ.generate("./src/test/resources/fileyaml1.yml",
                "./src/test/resources/expected/emptyYML.yml"));
        assertThat(thrownYAML).isInstanceOf(IOException.class);
        assertThat(thrownYAML.getMessage()).isEqualTo("one of the files is empty");
    }

    @Test
    void differTestWithWrongFileFormat() {
        Throwable thrown = catchThrowable(() -> Differ.generate("./src/test/resources/expected/expectedJson.txt",
                "./src/test/resources/filepath2.json"));
        assertThat(thrown).isInstanceOf(IOException.class);
        assertThat(thrown.getMessage())
                .isEqualTo("Wrong file format! Available formats: .json, .yaml, .yml");
    }

    @Test
    void testFormatter() {
        Throwable thrownJSONWithBraces = catchThrowable(() -> Differ.generate("./src/test/resources/fileyaml1.yml",
                "./src/test/resources/fileyaml2.yml", "jsn"));

        assertThat(thrownJSONWithBraces).isInstanceOf(IOException.class);
        assertThat(thrownJSONWithBraces.getMessage())
                .isEqualTo("jsn is wrong format! Available formats: stylish, plain, json.");
    }
}
