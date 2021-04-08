package com.howei.util;

public class DateFormat {

    /**
     *
     * @param timeMillis
     * @param level
     * @return
     */
    public static Long getConfirmTimeMills(Long timeMillis, String level) {
        if ("0".equals(level)) {
            return timeMillis + 8 * 60 * 60 * 1000;
        } else if ("1".equals(level)) {
            return timeMillis + 16 * 60 * 60 * 1000;
        } else if ("2".equals(level)) {
            return timeMillis + 24 * 60 * 60 * 1000;
        } else if ("3".equals(level)) {
            return timeMillis + 72 * 60 * 60 * 1000;
        } else if ("4".equals(level)) {
            return timeMillis + 16 * 60 * 60 * 1000;
        } else if ("5".equals(level)) {
            return timeMillis + 168 * 60 * 60 * 1000;
        }
        return null;
    }
}
