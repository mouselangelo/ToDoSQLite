package com.deuxlapins.todosqlite.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by chetan on 06/01/17.
 */

public class FormatterUtils {

    private static DateFormat formatter;

    private static DateFormat getFormatter() {
        if (formatter != null) {
            return  formatter;
        }
        formatter = SimpleDateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        return formatter;
    }


    public static String formatDate(Date date) {
        return getFormatter().format(date);
    }

}
