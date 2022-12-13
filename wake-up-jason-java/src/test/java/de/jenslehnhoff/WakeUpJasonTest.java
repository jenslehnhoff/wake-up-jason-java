package de.jenslehnhoff;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WakeUpJasonTest {
    private WakeUpJason wakeUpJason = new WakeUpJason();

    @Test
    void testShowSomething() {

        // given


        // when
        final List<OffsetDateTime> futureNextFriday13th = wakeUpJason.findFutureNextFriday13th(100);

        // then
        Assertions.assertEquals(100, futureNextFriday13th.size());

    }

    @Test
    void testShowSomething2() {

        // given


        // when
        final List<OffsetDateTime> futureNextFriday13th = wakeUpJason.findFutureFriday13thFromNow();

        // then
        Assertions.assertTrue(futureNextFriday13th.size() == 100);

    }


}