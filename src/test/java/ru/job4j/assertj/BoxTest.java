package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void whatsThisUNKNOWN() {
        Box box = new Box(1, -10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("unknown")
                .contains("object")
                .doesNotContain("Sphere")
                .isEqualTo("Unknown object");
    }

    @Test
    void getNumberOfVertices() {
        Box box = new Box(8, 12);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex)
                .isLessThan(10)
                .isGreaterThan(5)
                .isNotZero()
                .isEven()
                .isPositive()
                .isEqualTo(8);
    }

    @Test
    void isExistTrue() {
        Box box = new Box(4, 8);
        boolean checkVertex = box.isExist();
        assertThat(checkVertex)
                .isTrue();
    }

    @Test
    void isExistFalse() {
        Box box = new Box(2, 8);
        boolean checkVertex = box.isExist();
        assertThat(checkVertex)
                .isFalse();
    }

    @Test
    void isExistFalseNegativeEdge() {
        Box box = new Box(2, -8);
        boolean checkVertex = box.isExist();
        assertThat(checkVertex)
                .isFalse();
    }

    @Test
    void getArea() {
        Box box = new Box(8, 8);
        double area = box.getArea();
        assertThat(area)
                .isEqualTo(384, offset(0.01D))
                .isGreaterThan(383.9d)
                .isLessThan(384.1d);
    }
}
