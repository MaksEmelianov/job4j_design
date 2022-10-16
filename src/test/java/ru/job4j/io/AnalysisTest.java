package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnalysisTest {

    @Test
    void checkServerLog() {
        Analysis analysis = new Analysis();
        String pathSource = "./data/server.log";
        String pathTarget = "./data/serverError.csv";
        analysis.unavailable(pathSource, pathTarget);
    }

    @Test
    void checkServer2Log() {
        Analysis analysis = new Analysis();
        String pathSource = "./data/server2.log";
        String pathTarget = "./data/serverError2.csv";
        analysis.unavailable(pathSource, pathTarget);
    }

    @Test
    void checkServer3Log() {
        Analysis analysis = new Analysis();
        String pathSource = "./data/server3.log";
        String pathTarget = "./data/serverError3.csv";
        analysis.unavailable(pathSource, pathTarget);
    }
}
