package com.example.productivity.Calendar;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Appointment implements Serializable {
    private String name;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private static final long serialVersionUID = -3825880683075034385L;

    Appointment(String name) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        this.name = name;
        this.mYear = cal.get(Calendar.YEAR);
        this.mMonth = cal.get(Calendar.MONTH);
        this.mDay = cal.get(Calendar.DAY_OF_MONTH);
        this.mHour = cal.getTime().getHours();
        this.mMinute = cal.getTime().getMinutes();
    }

    Appointment(String name, int mYear, int mMonth, int mDay, int mHour, int mMinute) {
        this.name = name;
        this.mMinute = mMinute;
        this.mHour = mHour;
        this.mDay = mDay;
        this.mMonth = mMonth;
        this.mYear = mYear;
    }

    public String getName() {
        return name;
    }

    String getDate() {
        return String.format("%02d.%02d.%02d %02d:%02d", mDay, mMonth + 1, mYear, mHour, mMinute);
    }
}
