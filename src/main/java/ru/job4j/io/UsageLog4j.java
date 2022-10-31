package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    public static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int i = 20;
        boolean bl = true;
        float ft = 50.1F;
        double db = 1.1433453453435343;
        byte dt = 45;
        short st = 1290;
        char ch = 15001;
        long lg = 51_244_365_545_464L;
        LOG.debug("{}, {}, {}, {}, {}, {}, {}, {}",
                i, bl, ft, db, dt, st, ch, lg);
        /*LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");*/
    }
}
