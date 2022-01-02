package hexlet.tests;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    @Test
    void testDiffer() throws Exception {
        String expected = """
                {\s
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String actual = Differ.generate(new File("/home/alexey/java-project-lvl2/src/test/resources/filepath1.json"),
                new File("/home/alexey/java-project-lvl2/src/test/resources/filepath2.json"));
        assertThat(expected).isEqualTo(actual);
    }
    @Test
    void testDifferIfEmpty() throws Exception {
        String expected = "one of the files is empty";
        String actual = Differ.generate(new File("/home/alexey/java-project-lvl2/src/test/resources/filepath1.json"),
                new File("/home/alexey/java-project-lvl2/src/test/resources/result.json"));
        assertThat(expected).isEqualTo(actual);
    }
}
