package com.github.wanghy360.base.util;

public class RandomUtil {
    private static final java.util.Random RANDOM = new java.util.Random();

    public static float getRandom(float lower, float upper) {
        return getRandom(upper - lower) + lower;
    }

    public static float getRandom(float range) {
        return RANDOM.nextFloat() * range;
    }

    public static double getRandom(double lower, double upper) {
        return getRandom(upper - lower) + lower;
    }

    public static double getRandom(double range) {
        return RANDOM.nextDouble() * range;
    }

    public static int getRandom(int lower, int upper) {
        return RANDOM.nextInt(upper - lower) + lower;
    }

    public static int getRandom(int range) {
        return RANDOM.nextInt(range);
    }

    public static boolean getRandom() {
        return RANDOM.nextBoolean();
    }

}
