package com.saintsapp.saintsserver.entities;

public enum CatholicTitle {
    DOCTOR_DE_LA_IGLESIA("DOCTOR DE LA IGLESIA"),
    MARTIR("MARTIR"),
    BAUTISTA("BAUTISTA"),
    EVANGELISTA("EVANGELISTA"),
    PAPA("PAPA"),
    APOSTOL("APOSTOL");

    private String code;

    CatholicTitle(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
