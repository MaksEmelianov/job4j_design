package ru.job4j.generics;

public class Role extends Base {

    private final String nameRole;

    public Role(String id, String nameRole) {
        super(id);
        this.nameRole = nameRole;
    }

    public String getNameRole() {
        return nameRole;
    }
}
