package com.projects.pucprcalculadoraimc.services;

public class WeightChecker {
    private WeightChecker() {
        throw new AssertionError();
    }

    public static float minimum(float height) {
        return (float) (18.5 * height * height);
    }

    public static float maximum(float height) {
        return (float) (24.9 * height * height);
    }
}
