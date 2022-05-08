package com.irme.server.utils;

import java.util.Random;

public abstract class ReportWeightUtils {
    private ReportWeightUtils() {
    }

    public static int RandomByValue(int total, int current) {
        Random ran = new Random();
        int delta = Math.round(current * 0.2f);
        int delta1 = ran.nextInt(delta * 2) - delta;
        int expectedWeight = current + delta1;

        if (expectedWeight > total) {
            expectedWeight = total - Math.round(delta * 0.7f);
        }

        if (expectedWeight < 0) {
            expectedWeight = delta;
        }

        return expectedWeight;
    }
}
