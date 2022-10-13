package ru.job4j.tree;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenCheckAddWithLeftParentMoreChild() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        assertThat(tree.add(2, 1)).isFalse();
    }

    @Test
    void whenCheckStatusAdd() {
        Tree<Integer> tree = new SimpleTree<>(0);
        assertThat(tree.add(0, 1)).isTrue();
    }

    @Test
    void whenCheckStatusBinaryTrue() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 4);
        tree.add(3, 6);
        tree.add(3, 7);
        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    void whenCheckStatusBinaryFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        assertThat(tree.isBinary()).isFalse();
    }
}
