package com.example.okno;

public class Task {
    private String name;
    private String opis;

    public Task(String name, String opis) {
        this.name = name;
        this.opis = opis;
    }

    public String getName() {
        return name;
    }

    public String getOpis() {
        return opis;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
