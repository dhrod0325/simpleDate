package com.saweb;

public class Main {
    public static void main(String[] args) {
        SimpleDate simpleCalendar = new SimpleDate(2015, 2, 4);

        System.out.println(simpleCalendar.toPrevWeek().getCurrentWeekDays());
    }
}
