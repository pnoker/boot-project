package com.pnoker.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author pnoker
 */
public class RandomUtils {
    private static final String BASE_STRING = "abcdefghijklmnopqrstuvwxyz0123456789";

    public RandomUtils() {
    }

    /**
     * random int
     *
     * @return
     */
    public static int randomInt() {
        return getRandom().nextInt();
    }

    /**
     * random int
     *
     * @param limit
     * @return
     */
    public static int randomInt(int limit) {
        return getRandom().nextInt(limit);
    }

    /**
     * random int
     *
     * @param min
     * @param max
     * @return
     */
    public static int randomInt(int min, int max) {
        return getRandom().nextInt(min, max);
    }

    /**
     * random long
     *
     * @return
     */
    public static long randomLong() {
        return getRandom().nextLong();
    }

    /**
     * random long
     *
     * @param limit
     * @return
     */
    public static long randomLong(long limit) {
        return getRandom().nextLong(limit);
    }

    /**
     * random long
     *
     * @param min
     * @param max
     * @return
     */
    public static long randomLong(long min, long max) {
        return getRandom().nextLong(min, max);
    }

    /**
     * random double
     *
     * @return
     */
    public static double randomDouble() {
        return getRandom().nextDouble();
    }

    /**
     * random double
     *
     * @param limit
     * @return
     */
    public static double randomDouble(double limit) {
        return getRandom().nextDouble(limit);
    }

    /**
     * random double
     *
     * @param min
     * @param max
     * @return
     */
    public static double randomDouble(double min, double max) {
        return getRandom().nextDouble(min, max);
    }

    /**
     * random string
     *
     * @param length
     * @return
     */
    public static String randomString(int length) {
        return randomString(BASE_STRING, length);
    }

    /**
     * random upper string
     *
     * @param length
     * @return
     */
    public static String randomStringUpper(int length) {
        return randomString(BASE_STRING, length).toUpperCase();
    }

    /**
     * random lower string
     *
     * @param length
     * @return
     */
    public static String randomStringLower(int length) {
        return randomString(BASE_STRING, length).toLowerCase();
    }


    public static String randomString(String baseString, int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        if (length < 1) {
            length = 1;
        }

        int baseLength = baseString.length();

        for (int i = 0; i < length; ++i) {
            int number = randomInt(baseLength);
            stringBuilder.append(baseString.charAt(number));
        }

        return stringBuilder.toString();
    }

    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }
}
