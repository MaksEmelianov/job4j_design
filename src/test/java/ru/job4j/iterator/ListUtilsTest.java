package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ListUtilsTest {

    private List<Integer> input;
    private List<Integer> in;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
        in = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(in, integer -> integer % 2 == 0);
        assertThat(in).hasSize(3).containsSequence(1, 3, 5);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.replaceIf(in, integer -> integer % 2 == 0, 0);
        assertThat(in).hasSize(5).containsSequence(1, 0, 3, 0, 5);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.removeAll(in, List.of(1, 2, 3));
        assertThat(in).hasSize(2).containsSequence(4, 5);
    }
}
