package com.saweb;

public class Main {
    public static void main(String[] args) {
        SimpleDate simpleCalendar = new SimpleDate(2015, 10);

        System.out.println(simpleCalendar);

        for (SimpleDate.SimpleWeek week : simpleCalendar.getWeekList()) {
            System.out.println();

            for (SimpleDate.SimpleDay day : week.getDays()) {
                System.out.print(day.getDay() + "\t\t");
            }
        }
    }
}
