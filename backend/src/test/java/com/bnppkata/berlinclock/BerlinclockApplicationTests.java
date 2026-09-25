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
                        service.toBerlinClock("00:00:00").singleMinutes() ),
                ()  -> assertEquals(
                        "YYYY",
                        service.toBerlinClock("23:59:59").singleMinutes()),
                ()  -> assertEquals(
                        "YYOO",
                        service.toBerlinClock("12:32:00").singleMinutes()),
                ()  -> assertEquals(
                        "YYYY",
                        service.toBerlinClock("12:34:00").singleMinutes()),
                ()  -> assertEquals(
                        "OOOO",
                        service.toBerlinClock("12:35:00").singleMinutes())
        );
    }

}
