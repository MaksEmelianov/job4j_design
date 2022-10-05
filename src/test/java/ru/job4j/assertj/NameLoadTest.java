package ru.job4j.assertj;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkArrayEmpty() {
        NameLoad nameLoad = new NameLoad();
        String[] names = Arrays.array();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void checkNotSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] names = Arrays.array("weffwfwefwf");
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain the symbol")
                .hasMessageContaining(names[0]);
    }

    @Test
    void checkStartWithSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"=dvwewwef", "wfwfe"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a key")
                .hasMessageContaining(names[0]);
    }

    @Test
    void checkEndSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"svsdsdsf=", "wfwfwef"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a value")
                .hasMessageContaining(names[0]);
    }
}
