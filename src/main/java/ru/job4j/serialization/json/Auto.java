package ru.job4j.serialization.json;

import java.util.Arrays;

public class Auto {

    private final boolean shipping;
    private final int countWheels;
    private final String number;
    private final Parameter parameter;
    private final String[] drivers;

    public Auto(boolean shipping, int countWheels, String number,
                Parameter parameter, String[] drivers) {
        this.shipping = shipping;
        this.countWheels = countWheels;
        this.number = number;
        this.parameter = parameter;
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "Auto{"
                + "shipping=" + shipping
                + ", countWheels=" + countWheels
                + ", number='" + number + '\''
                + ", parameter=" + parameter
                + ", drivers=" + Arrays.toString(drivers)
                + '}';
    }
}
