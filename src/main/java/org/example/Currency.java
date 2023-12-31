package org.example;

public class Currency {
    private String id;
    private String name;
    private String value;

    public Currency(String id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Currency() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
