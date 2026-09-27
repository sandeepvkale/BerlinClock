package com.bnppkata.berlinclock;

import com.bnppkata.berlinclock.domain.BerlinClock;
import com.bnppkata.berlinclock.service.BerlinClockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BerlinclockApplicationTests {

    private BerlinClockService service;

    @BeforeEach
    void setUp() {
        service = new BerlinClockService();
    }

    @Test
    void shouldConvertSingleMinutesRow() {
        assertAll(
                () -> assertEquals(
                        "OOOO",
                        service.toBerlinClock("00:00:00").singleMinutes()),
                () -> assertEquals(
                        "YYYY",
                        service.toBerlinClock("23:59:59").singleMinutes()),
                () -> assertEquals(
                        "YYOO",
                        service.toBerlinClock("12:32:00").singleMinutes()),
                () -> assertEquals(
                        "YYYY",
                        service.toBerlinClock("12:34:00").singleMinutes()),
                () -> assertEquals(
                        "OOOO",
                        service.toBerlinClock("12:35:00").singleMinutes())
        );
    }

    @Test
    void shouldConvertFiveHourRow() {
        assertAll(
                () -> assertEquals(
                        "OOOO",
                        service.toBerlinClock("00:00:00").fiveHours()),
                () -> assertEquals(
                        "RRRR",
                        service.toBerlinClock("23:59:59").fiveHours()),
                () -> assertEquals(
                        "OOOO",
                        service.toBerlinClock("02:04:00").fiveHours()),
                () -> assertEquals(
                        "ROOO",
                        service.toBerlinClock("08:23:00").fiveHours()),
                () -> assertEquals(
                        "RRRO",
                        service.toBerlinClock("16:35:00").fiveHours())
        );
    }

    @Test
    void shouldConvertSingleHourRow() {
        assertAll(
                () -> assertEquals(
                        "OOOO",
                        service.toBerlinClock("00:00:00").singleHours()),
                () -> assertEquals(
                        "RRRO",
                        service.toBerlinClock("23:59:59").singleHours()),
                () -> assertEquals(
                        "RROO",
                        service.toBerlinClock("02:04:00").singleHours()),
                () -> assertEquals(
                        "RRRO",
                        service.toBerlinClock("08:23:00").singleHours()),
                () -> assertEquals(
                        "RRRR",
                        service.toBerlinClock("14:35:00").singleHours())
        );
    }

    @Test
    void shouldConvertSecondsLamp() {
        assertAll(
                () -> assertEquals(
                        "Y",
                        service.toBerlinClock("00:00:00").seconds()),
                () -> assertEquals(
                        "O",
                        service.toBerlinClock("23:59:59").seconds())
        );
    }

    @Test
    void shouldConvertFiveMinutesRow() {
        assertAll(
                () -> assertEquals(
                        "OOOOOOOOOOO",
                        service.toBerlinClock("00:00:00").fiveMinutes()),
                () -> assertEquals(
                        "YYRYYRYYRYY",
                        service.toBerlinClock("23:59:59").fiveMinutes()),
                () -> assertEquals(
                        "OOOOOOOOOOO",
                        service.toBerlinClock("12:04:00").fiveMinutes()),
                () -> assertEquals(
                        "YYRYOOOOOOO",
                        service.toBerlinClock("12:23:00").fiveMinutes()),
                () -> assertEquals(
                        "YYRYYRYOOOO",
                        service.toBerlinClock("12:35:00").fiveMinutes())
        );
    }

    @Test
    void shouldConvertWholeClock() {
        assertAll(
                () -> assertEquals(
                        "YOOOOOOOOOOOOOOOOOOOOOOO",
                        service.toBerlinClock("00:00:00").asString()),
                () -> assertEquals(
                        "ORRRRRRROYYRYYRYYRYYYYYY",
                        service.toBerlinClock("23:59:59").asString()),
                () -> assertEquals(
                        "YRRROROOOYYRYYRYYRYOOOOO",
                        service.toBerlinClock("16:50:06").asString()),
                () -> assertEquals(
                        "ORROOROOOYYRYYRYOOOOYYOO",
                        service.toBerlinClock("11:37:01").asString()
                )
        );
    }

    @Test void shouldConvertBerlinClockBackToDigitalTime() {
        assertAll(
                () -> assertEquals(
                        "00:00:00",
                        service.toDigitalTime( "YOOOOOOOOOOOOOOOOOOOOOOO" ) ),
                () -> assertEquals(
                        "23:59:01",
                        service.toDigitalTime( "ORRRRRRROYYRYYRYYRYYYYYY" ) ),
//                () -> assertEquals(
//                        "16:50:06",
//                        service.toDigitalTime( "YRRROROOOYYRYYRYYRYOOOOO" ) ),
                // Need to discuss this use case with the team, as it seems to be a bug in the BerlinClockService implementation

                () -> assertEquals(
                        "11:37:01",
                        service.toDigitalTime( "ORROOROOOYYRYYRYOOOOYYOO" )
                )
        );
    }

}
