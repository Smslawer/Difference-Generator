package hexlet.tests;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    @Test
    void testDifferStylish() throws Exception {
        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        String actualJson = Differ.generate("./src/test/resources/filepath1.json",
                "./src/test/resources/filepath2.json", "stylish");
        assertThat(actualJson).isEqualTo(expected);

        String actualYaml = Differ.generate("./src/test/resources/fileyaml1.yml",
                "./src/test/resources/fileyaml2.yml", "stylish");
        assertThat(actualYaml).isEqualTo(expected);
    }

    @Test
    void testDifferPlain() throws Exception {
        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
        String actualJson = Differ.generate("./src/test/resources/filepath1.json",
                "./src/test/resources/filepath2.json", "plain");
        assertThat(actualJson).isEqualTo(expected);

        String actualYaml = Differ.generate("./src/test/resources/fileyaml1.yml",
                "./src/test/resources/fileyaml2.yml", "plain");
        assertThat(actualYaml).isEqualTo(expected);
    }

    @Test
    void testDifferJson() throws Exception {
        String expected = """
                [ {
                  "diff" : "unchanged",
                  "name" : "chars1",
                  "value" : [ "a", "b", "c" ]
                }, {
                  "diff" : "updated",
                  "name" : "chars2",
                  "newValue" : false,
                  "value" : [ "d", "e", "f" ]
                }, {
                  "diff" : "updated",
                  "name" : "checked",
                  "newValue" : true,
                  "value" : false
                }, {
                  "diff" : "updated",
                  "name" : "default",
                  "newValue" : [ "value1", "value2" ],
                  "value" : null
                }, {
                  "diff" : "updated",
                  "name" : "id",
                  "newValue" : null,
                  "value" : 45
                }, {
                  "diff" : "removed",
                  "name" : "key1",
                  "value" : "value1"
                }, {
                  "diff" : "added",
                  "name" : "key2",
                  "value" : "value2"
                }, {
                  "diff" : "unchanged",
                  "name" : "numbers1",
                  "value" : [ 1, 2, 3, 4 ]
                }, {
                  "diff" : "updated",
                  "name" : "numbers2",
                  "newValue" : [ 22, 33, 44, 55 ],
                  "value" : [ 2, 3, 4, 5 ]
                }, {
                  "diff" : "removed",
                  "name" : "numbers3",
                  "value" : [ 3, 4, 5 ]
                }, {
                  "diff" : "added",
                  "name" : "numbers4",
                  "value" : [ 4, 5, 6 ]
                }, {
                  "diff" : "added",
                  "name" : "obj1",
                  "value" : {
                    "nestedKey" : "value",
                    "isNested" : true
                  }
                }, {
                  "diff" : "updated",
                  "name" : "setting1",
                  "newValue" : "Another value",
                  "value" : "Some value"
                }, {
                  "diff" : "updated",
                  "name" : "setting2",
                  "newValue" : 300,
                  "value" : 200
                }, {
                  "diff" : "updated",
                  "name" : "setting3",
                  "newValue" : "none",
                  "value" : true
                } ]""";

        String actualJson = Differ.generate("./src/test/resources/filepath1.json",
                "./src/test/resources/filepath2.json", "json");
        assertThat(actualJson).isEqualTo(expected);

        String actualYaml = Differ.generate("./src/test/resources/fileyaml1.yml",
                "./src/test/resources/fileyaml2.yml", "json");
        assertThat(actualYaml).isEqualTo(expected);
    }

    @Test
    void testDifferIfEmpty() throws Exception {
        String expected = "one of the files is empty";
        String actual = Differ.generate("./src/test/resources/filepath1.json",
                "./src/test/resources/emptyJSON.json");
        assertThat(actual).isEqualTo(expected);

        String actual2 = Differ.generate("./src/test/resources/fileyaml1.yml",
                "./src/test/resources/emptyYML.yml");
        assertThat(actual2).isEqualTo(expected);
    }
}
