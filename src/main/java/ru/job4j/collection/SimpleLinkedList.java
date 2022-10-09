package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size;
    private int modCount;

    private Node<E> head;

    @Override
    public void add(E value) {
        final Node<E> h = head;
        final Node<E> newNode = new Node<>(value);
        if (h == null) {
            head = newNode;
        }
        newNode.next = head;
        head = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return getNode(index).item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            final int expectedModCount = modCount;
            Node<E> node = getNode(0);

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Concurrent Modification");
                }
                return node != null && node.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No Such Element");
                }
                E next = node.item;
                node = node.next;
                return next;
            }
        };
    }

    private Node<E> getNode(int index) {
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element) {
            this.item = element;
        }
    }
}
