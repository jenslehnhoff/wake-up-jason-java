package de.jenslehnhoff;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class WakeUpJason {

    public List<OffsetDateTime> findFutureFriday13thFromNow(){

        Instant start = Instant.now();
        List<OffsetDateTime> friday13thList = alternative(100);
        Instant end = Instant.now();
        System.out.println("Execution Time: " + Duration.between(start, end).toMillis() + " Milliseconds");

        System.out.println("Nächster: " + friday13thList.get(0) +  " / " + friday13thList.get(1));

        System.out.println("Final List Size: " + friday13thList.size());


        return friday13thList;
    }


    public List<OffsetDateTime> alternative(int count){

        final OffsetDateTime maxDateTime = OffsetDateTime.parse("9999-12-31T00:00:00Z");


        final OffsetDateTime todayUtc = OffsetDateTime.now(ZoneOffset.UTC).truncatedTo(ChronoUnit.DAYS);

        return Stream.iterate(todayUtc, m -> m.isBefore(maxDateTime), m -> m.plusMonths(1)) //
                .map(m -> m.withDayOfMonth(13))
                .filter(m -> DayOfWeek.FRIDAY.equals(m.getDayOfWeek()))
//                .limit(count)
                .toList();

    }

    public List<OffsetDateTime> findFutureNextFriday13th(int count){

        Instant start = Instant.now();

        final OffsetDateTime todayUtc = OffsetDateTime.now(ZoneOffset.UTC).truncatedTo(ChronoUnit.DAYS);

        List<OffsetDateTime> friday13thList = new ArrayList<>();

        OffsetDateTime referenceDay = todayUtc;

        while (friday13thList.size() < count){
            OffsetDateTime nextFriday13th = findNextFriday13thAfter(referenceDay);
            friday13thList.add(nextFriday13th);
            referenceDay = nextFriday13th;
        }

        Instant end = Instant.now();
        System.out.println("Execution Time: " + Duration.between(start, end).toMillis() + " Milliseconds");
//        System.out.println("Final List: " + friday13thList);




        return friday13thList;

    }

    public OffsetDateTime findNextFriday13thAfter(OffsetDateTime referenceDate){

        boolean isNotFridayq13th = true;
        long dayOffset = 1;

        while(isNotFridayq13th){
            isNotFridayq13th = referenceDate.plusDays(dayOffset).getDayOfWeek() == DayOfWeek.FRIDAY &&
                    referenceDate.plusDays(dayOffset).getDayOfMonth() == 13 ? false : true;

            if (isNotFridayq13th) dayOffset++;
        }

//        System.out.println("Datum UTC: " + referenceDate);
//        System.out.println("Nächster Freitag der 13. : " + referenceDate.plusDays(dayOffset));
//        System.out.println("Iterationen : " + dayOffset);

        return referenceDate.plusDays(dayOffset);
    }

}
