package ru.job4j.set;

import java.util.*;

public class UsageSet {

    public static void main(String[] args) {
        java.util.Set<String> strings = new HashSet<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        for (String s : strings) {
            System.out.println("Текущий элемент: " + s);
        }
        strings.addAll(List.of("one", "four", "five"));
        Iterator<String> it = strings.iterator();
        System.out.println();
        while (it.hasNext()) {
            System.out.println("Текущий элемент: " + it.next());
        }
        strings.remove("one");
        strings.add("one2");
        strings.add("one3");
        strings.removeAll(List.of("two", "three"));
        strings.retainAll(List.of("one2"));
        strings.removeIf(s -> s.endsWith("3"));
        int size = strings.size();
        strings.addAll(List.of("test", "test2"));
        strings.stream()
                .filter(s -> s.length() < 5)
                .forEach(st -> System.out.println("Текущий элемент: " + st));
        System.out.println();
        java.util.Set<Integer> numbers = new LinkedHashSet<>(List.of(1, 2, 3, 4, 2, 3, 5));
        java.util.Set<Integer> numbers2 = new TreeSet<>(List.of(5, 4, 2, 5, 6, 7, 2, 3, 4));
        numbers.forEach(System.out::print);
        System.out.println();
        numbers2.forEach(System.out::print);
    }
}
