package co.skreel.android.utils;

import java.util.Calendar;

public class CardUtil {

    public static boolean isAllDigits(String pan){
        if(pan == null)
            return false;

        for (char c : pan.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }


    public static boolean hasMonthPassed(int year, int month){
        return true;
    }

}
