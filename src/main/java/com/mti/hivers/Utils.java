package com.mti.hivers;

public class Utils {

    public static void times(int occurences, final Runnable func) {
        for (int i = 0; i < occurences; i++) {
            func.run();
        }
    }

}
