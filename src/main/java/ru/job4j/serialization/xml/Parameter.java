package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "parameter")
public class Parameter {

    @XmlAttribute
    private int yearRelease;
    @XmlAttribute
    private int engineCapacity;

    public Parameter(int yearRelease, int engineCapacity) {
        this.yearRelease = yearRelease;
        this.engineCapacity = engineCapacity;
    }

    public Parameter() {

    }

    @Override
    public String toString() {
        return "Parameter{"
                + "yearRelease=" + yearRelease
                + ", engineCapacity=" + engineCapacity
                + '}';
    }
}
