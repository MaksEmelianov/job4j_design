package ru.job4j.serialization.json;

public class Parameter {

    private final int yearRelease;
    private final int engineCapacity;

    public Parameter(int yearRelease, int engineCapacity) {
        this.yearRelease = yearRelease;
        this.engineCapacity = engineCapacity;
    }

    @Override
    public String toString() {
        return "Parameter{"
                + "yearRelease=" + yearRelease
                + ", engineCapacity=" + engineCapacity
                + '}';
    }
}
