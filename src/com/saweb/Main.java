package com.saweb;

public class Main {
    public static void main(String[] args) {
        SimpleDate simpleCalendar = new SimpleDate(2015, 10, 1);

        System.out.println(simpleCalendar.toPrevWeek());
    }
}
