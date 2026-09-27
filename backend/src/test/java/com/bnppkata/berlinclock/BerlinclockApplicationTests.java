package com.bnppkata.berlinclock;

import com.bnppkata.berlinclock.service.BerlinClockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
