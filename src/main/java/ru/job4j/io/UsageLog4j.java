package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    public static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in lod example", e);
            /*LOG.error(e.getMessage());
            LOG.error("Error {}, User {}", e, "Petr");*/
        }
        /*LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");*/
    }
}
