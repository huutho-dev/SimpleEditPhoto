package com.huutho.photo.models;

/**
 * Created by ThoNh on 11/1/2017.
 */

public class Overlay {

    float fucking = 0.5f;

    private float mIntensity = 90;

    public String nNameOverlay;

    public Overlay(String name) {
        nNameOverlay = name;
    }

    public String getConfig() {
        return "@krblend mix " + nNameOverlay + " " + mIntensity * fucking;
    }

    public String originConfig (){
        return "@krblend mix " + nNameOverlay + " "+mIntensity;
    }

    public void setIntensity(float fuck) {
        fucking = fuck;
    }

    @Override
    public String toString() {
        return "Overlay{" +
                "mConfig='" + fucking + '\'' +
                ", mIntensity='" + mIntensity + '\'' +
                ", nNameOverlay='" + nNameOverlay + '\'' +
                '}';
    }
}
