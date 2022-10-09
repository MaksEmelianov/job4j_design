package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size;
    private int modCount;

    private Node<E> head;

    @Override
    public void add(E value) {
        final Node<E> tmp = head;
        final Node<E> newNode = new Node<>(value, null);
        head = newNode;
        if (tmp != null) {
            tmp.next = newNode;
        }
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
            Node<E> node;

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

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
