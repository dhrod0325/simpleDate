#SimpleDate

SimpleDate is easy make java calendar

## How to use
```java
for (SimpleDate.SimpleWeek week : simpleCalendar.getWeekList()) {
    System.out.println();

    for (SimpleDate.SimpleDay day : week.getDays()) {
        System.out.print(day.getDay() + "\t\t");
    }
}
```

##Result
![Result](/dhrod0325/simpleDate/screenshot.png)
