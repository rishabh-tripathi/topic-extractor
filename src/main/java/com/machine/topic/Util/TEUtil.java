package com.machine.topic.Util;

import com.google.inject.Singleton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rishabh.tripathi on 9/25/15.
 */

@Singleton
public class TEUtil {

    public Date convertStringToDate(String strDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date date = formatter.parse(strDate);
        return date;
    }

    public String convertDateToString(Date date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        return df.format(date);
    }

    public String sanitizeEntityName(String name) {
        return name.toLowerCase().replaceAll(" ","_").replaceAll("[^A-Za-z0-9_]", "");
    }
}
