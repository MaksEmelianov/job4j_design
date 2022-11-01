package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class AutoMain {

    public static void main(String[] args) throws IOException, JAXBException {
        Auto auto = new Auto(true, 10, "OOO123",
                new Parameter(2309, 4567), new String[]{"Maks", "Petr"});
        JAXBContext jaxbContext = JAXBContext.newInstance(Auto.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(auto, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Auto autoFromXml = (Auto) unmarshaller.unmarshal(reader);
            System.out.println(autoFromXml);
        }
    }
}
