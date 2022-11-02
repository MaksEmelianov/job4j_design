package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AutoMainJson {

    public static void main(String[] args) {
        final Auto auto = new Auto(true, 6, "A123AA123RUS",
                new Parameter(1990, 6500), new String[]{"Aleksey", "Dmitriy"});
        JSONObject jsonParameter = new JSONObject(
                "{"
                + "\"yearRelease\":2020,"
                + "\"engineCapacity\":3400"
                + "}");
        List<String> driversList = List.of("Maks", "Pert");
        JSONArray jsonDrivers = new JSONArray(driversList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shipping", auto.isShipping());
        jsonObject.put("countWheels", auto.getCountWheels());
        jsonObject.put("number", auto.getNumber());
        jsonObject.put("parameter", jsonParameter);
        jsonObject.put("drivers", jsonDrivers);
        System.out.println(new JSONObject(auto));
        System.out.println(jsonObject);
    }
}
