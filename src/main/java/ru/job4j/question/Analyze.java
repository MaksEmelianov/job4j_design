package ru.job4j.question;

import java.util.*;
import java.util.stream.Collectors;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added  = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> usersOld = new HashMap<>();
        for (var user : previous) {
            usersOld.put(user.getId(), user.getName());
        }
        for (var user : current) {
            if (!usersOld.containsKey(user.getId())) {
                added++;
            } else if (!Objects.equals(usersOld.get(user.getId()), user.getName())) {
                changed++;
            }
            usersOld.remove(user.getId());
        }
        deleted = usersOld.size();
        return new Info(added, changed, deleted);
    }
}
