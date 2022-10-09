package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size;
    private int modCount;

    private Node<E> head;

    @Override
    public void add(E value) {
    if (head == null) {
        head = new Node<>(value);
    } else {
        Node<E> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node<>(value);
    }
    size++;
    modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            final int expectedModCount = modCount;
            Node<E> node = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Concurrent Modification");
                }
                return node != null;
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

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element) {
            this.item = element;
        }
    }
}
