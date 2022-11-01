package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "auto")
@XmlAccessorType(XmlAccessType.FIELD)
public class Auto {

    @XmlAttribute
    private boolean shipping;
    @XmlAttribute
    private int countWheels;
    @XmlAttribute
    private String number;
    private Parameter parameter;
    @XmlElementWrapper(name = "drivers")
    @XmlElement(name = "driver")
    private String[] drivers;

    public Auto(boolean shipping, int countWheels, String number,
                Parameter parameter, String[] drivers) {
        this.shipping = shipping;
        this.countWheels = countWheels;
        this.number = number;
        this.parameter = parameter;
        this.drivers = drivers;
    }

    public Auto() {

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
