package com.trioangle.gofer.GladePay.utils;

import java.util.Calendar;
import java.util.Locale;

/**
 * Card Class Utilities.
 *
 * @author Light Chinaka on 14/Nov/18.
 */
public class CardUtilities {

    public static boolean isWholePositiveNumber(String stringValue) {
        if (stringValue == null || stringValue.trim().length() == 0) {
            return false;
        }
        for (char stringCharacter : stringValue.toCharArray()) {
            if (!Character.isDigit(stringCharacter)) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasMonthPassed(int year, int month) {
        Calendar now = Calendar.getInstance();
        // Expires at end of specified month, Calendar month starts at 0
        return hasYearPassed(year) || normalizeYear(year) == now.get(Calendar.YEAR) && month < (now.get(Calendar.MONTH) + 1);
    }

    public static boolean isValidMonth(int month){
        return (month > 0) && (month < 13);
    }

    public static boolean hasYearPassed(int year) {
        int normalized = normalizeYear(year);
        Calendar now = Calendar.getInstance();
        return normalized < now.get(Calendar.YEAR);
    }

    /**
     * Check if card is expired
     *
     * @param year  - Non-zero based index for year
     * @param month - Non-zero based index for month
     * @return true if the card has expired.
     */
    public static boolean isNotExpired(int year, int month) {
        //check if year && month have passed
        return !hasYearPassed(year) && !hasMonthPassed(year, month);
    }

    // Convert two-digit year to full year if necessary
    private static int normalizeYear(int year) {
        if (year < 100 && year >= 0) {
            Calendar now = Calendar.getInstance();
            String currentYear = String.valueOf(now.get(Calendar.YEAR));
            String prefix = currentYear.substring(0, currentYear.length() - 2);
            year = Integer.parseInt(String.format(Locale.US, "%s%02d", prefix, year));
        }
        return year;
    }
}
