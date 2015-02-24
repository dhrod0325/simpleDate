package com.saweb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by OKS on 2015-02-24.
 */
public class SimpleDate {
    private int year, month, week, totalDays;

    private List<SimpleWeek> weekList = new ArrayList<SimpleWeek>();

    public SimpleDate() {
        Calendar calendar = Calendar.getInstance();

        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.week = calendar.get(Calendar.WEEK_OF_MONTH);

        init();
    }

    public SimpleDate(int year, int month) {
        this.year = year;
        this.month = month;
        this.week = 1;
        init();
    }

    public SimpleDate(int year, int month, int week) {
        this.year = year;
        this.month = month;
        this.week = week;

        init();
    }

    private void init() {
        while (month > 12) {
            month -= 12;
            year++;
        }

        while (month <= 0) {
            month += 12;
            year--;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);

        int maxWeek = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);

        int paramMonth = month;
        int paramYear = year;

        if (week > maxWeek) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
        }

        if (week <= 0) {
            calendar.set(Calendar.WEEK_OF_MONTH, 1);
            calendar.add(Calendar.WEEK_OF_YEAR, -1);
            setWeek(calendar.getActualMaximum(Calendar.WEEK_OF_MONTH));
        }

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;

        if (month > paramMonth || year > paramYear) {
            setWeek(1);
        }

        totalDays = calendar.getActualMaximum(Calendar.DATE);

        initWeekAndDays();
    }

    private void initWeekAndDays() {
        int day = toCalendar().get(Calendar.DAY_OF_WEEK_IN_MONTH);

        while (day > 1) {
            day -= 7;
        }

        while (day <= totalDays) {
            List<SimpleDay> days = new ArrayList<SimpleDay>();

            for (int i = 0; i < 7; i++) {
                SimpleDay simpleDay = null;

                if (day > 0 && day <= totalDays) {
                    simpleDay = new SimpleDay(year, month, day);
                } else {
                    simpleDay = new SimpleDay();
                }

                days.add(simpleDay);
                day++;
            }

            SimpleWeek sWeek = new SimpleWeek();
            sWeek.setDays(days);

            weekList.add(sWeek);
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getNextWeek() {
        return week + 1;
    }

    public int getPrevWeek() {
        return week - 1;
    }

    public int getNextMonth() {
        return month + 1;
    }

    public int getPrevMonth() {
        return month - 1;
    }

    public int getNextYear() {
        return year + 1;
    }

    public int getPrevYear() {
        return year - 1;
    }

    public SimpleDate toNextWeek() {
        return new SimpleDate(year, month, getNextWeek());
    }

    public SimpleDate toPrevWeek() {
        return new SimpleDate(year, month, getPrevWeek());
    }

    public SimpleDate toNextMonth() {
        return new SimpleDate(year, getNextMonth(), 1);
    }

    public SimpleDate toPrevMonth() {
        return new SimpleDate(year, getPrevMonth(), 1);
    }

    public SimpleDate toNextYear() {
        return new SimpleDate(getNextYear(), 1, 1);
    }

    public SimpleDate toPrevYear() {
        return new SimpleDate(getPrevYear(), 1, 1);
    }

    public Calendar toCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        return calendar;
    }

    public List<Integer> getCurrentWeekDays() {
        List<Integer> result = new ArrayList<Integer>();

        Calendar calendar = toCalendar();

        int firstDay = calendar.get(Calendar.DATE);

        for (int i = 0; i < 7; i++) {
            result.add(i + firstDay);
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format("%d %d %d", year, month, week);
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public List<SimpleWeek> getWeekList() {
        return weekList;
    }

    public void setWeekList(List<SimpleWeek> weekList) {
        this.weekList = weekList;
    }

    public static class SimpleWeek {
        private List<SimpleDay> days = new ArrayList<SimpleDay>();

        public List<SimpleDay> getDays() {
            return days;
        }

        public void setDays(List<SimpleDay> days) {
            this.days = days;
        }
    }

    public static class SimpleDay {
        private int year, month, day;

        public SimpleDay() {
        }

        public SimpleDay(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }
    }
}
