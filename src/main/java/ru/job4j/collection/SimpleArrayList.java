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
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        if (isValidateIndex(index)) {
            T element = get(index);
            container[index] = newValue;
            return element;
        }
        return null;
    }

    @Override
    public T remove(int index) {
        if (isValidateIndex(index)) {
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
        return null;
    }

    @Override
    public T get(int index) {
        if (isValidateIndex(index)) {
            return container[index];
        }
        return null;
    }

    @Override
    public int size() {
        return size;
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

    private boolean isValidateIndex(int index) {
        return Objects.checkIndex(index, size) == index;
    }
}