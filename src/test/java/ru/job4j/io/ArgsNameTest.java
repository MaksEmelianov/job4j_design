package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ArgsNameTest {

    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        assertThatThrownBy(() -> jvm.get("Xms"))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("There is no such key");
    }

    @Test
    void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("Empty array args");
    }

    @Test
    void whenNoPointer() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"Xmx=512", "-encoding=UTF-8"}))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("There is no pointer \"-\"");
    }

    @Test
    void whenNoSeparator() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-Xmx:512", "-encoding:UTF-8"}))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("There is no separator \"=\"");
    }

    @Test
    void whenEmptyKey() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-=512", "-encoding=UTF-8"}))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("Empty key");
    }

    @Test
    void whenEmptyValue() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-key=", "-encoding=UTF-8"}))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("Empty value");
    }
}
