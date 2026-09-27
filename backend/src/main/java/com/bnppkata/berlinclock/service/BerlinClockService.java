package com.bnppkata.berlinclock.service;

import com.bnppkata.berlinclock.domain.BerlinClock;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

@Service
public class BerlinClockService {

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);

    public BerlinClock toBerlinClock(String digitalTime) {
        LocalTime time = parse(digitalTime);

        return new BerlinClock(
                secondsRow(time),   // Single Row
                fourLampsRow(time.getHour() / 5, 4, 'R'), // fiveHours
                fourLampsRow(time.getHour() % 5, 4, 'R'), // SingleHours
                fiveMinutes(time.getMinute()), // FiveMinutes
                fourLampsRow(time.getMinute() % 5, 4, 'Y') //singleMinutes
        );
    }

    private String fourLampsRow(int lit, int total, char colour) {
        return String.valueOf(colour).repeat(lit) + "O".repeat(total - lit);
    }

    private LocalTime parse(String value) {
        if (value == null) throw new IllegalArgumentException("Time is required");
        try {
            return LocalTime.parse(value, TIME_FORMAT);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Time must use HH:mm:ss and be a valid 24-hour time");
        }
    }

    private String secondsRow(LocalTime time) {
        return time.getSecond() % 2 == 0 ? "Y" : "O";
    }

    private String fiveMinutes(int minute) {
        StringBuilder row = new StringBuilder(11);
        for (int i = 1; i <= 11; i++) {
            if (i <= minute / 5) {
                row.append(i % 3 == 0 ? 'R' : 'Y');
            } else {
                row.append('O');
            }
        }
        return row.toString();
    }

    public String toDigitalTime(String berlinTime) {
        // Implementation for converting Berlin time to digital time
        return null;
    }
}
