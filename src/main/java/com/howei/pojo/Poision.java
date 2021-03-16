package com.howei.pojo;

public class Poision {
    private String PoisionA;
    private String PoisionB;

    public String getPoisionA() {
        return PoisionA;
    }

    public void setPoisionA(String poisionA) {
        PoisionA = poisionA;
    }

    public String getPoisionB() {
        return PoisionB;
    }

    public void setPoisionB(String poisionB) {
        PoisionB = poisionB;
    }

    @Override
    public String toString() {
        return "Poision [PoisionA=" + PoisionA + ", PoisionB=" + PoisionB + "]";
    }

}
