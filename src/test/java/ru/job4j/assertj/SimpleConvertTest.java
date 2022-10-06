package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray(
                "first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> array = simpleConvert.toList(
                "first", "second", "three", "four", "five");
        assertThat(array).isNotEmpty()
                .filteredOn(e -> e.length() < 7)
                .containsSequence("three", "four")
                .contains("second")
                .allMatch(e -> e.length() < 7)
                .hasSize(5)
                .doesNotContain("six");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet(
                "first", "first", "second", "second", "three", "four", "five");
        assertThat(set).isNotEmpty()
                .filteredOnAssertions(e -> assertThat(e).isNotEmpty())
                .doesNotHaveDuplicates()
                .doesNotContain("six")
                .containsExactlyInAnyOrder(
                        "first", "second", "three", "four", "five");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap(
                "first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsEntry("five", 4)
                .doesNotContainKey("six")
                .doesNotContainValue(5)
                .allSatisfy(
                        (key, value) -> {
                            assertThat(value).isLessThan(6);
                            assertThat(key).doesNotContain("x");
                        }
                );
    }
}
