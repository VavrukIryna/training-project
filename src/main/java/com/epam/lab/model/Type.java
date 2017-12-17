package com.epam.lab.model;

public enum Type {
    DARK("dark"),
    LIGHT("light"),
    LAGER("lager"),
    LIVE("live");

    private String currentStatus;

    Type(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

}