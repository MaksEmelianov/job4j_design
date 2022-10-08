package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        checkGrow();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T element = get(index);
        container[index] = newValue;
        return element;
    }

    @Override
    public T remove(int index) {
        T element = get(index);
        if (index != container.length - 1) {
            System.arraycopy(
                    container,
                    index + 1,
                    container,
                    index,
                    container.length - index - 1
            );
        }
        container[container.length - 1] = null;
        modCount++;
        size--;
        return element;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private void checkGrow() {
        if (size == 0) {
            container = Arrays.copyOf(container, 10);
        } else if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int indexNext;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Concurrent Modification");
                }
                return indexNext < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No Such Element");
                }
                return container[indexNext++];
            }
        };
    }
}