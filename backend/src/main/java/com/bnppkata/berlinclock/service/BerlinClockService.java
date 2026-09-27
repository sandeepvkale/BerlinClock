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

    private static final char YELLOW = 'Y';
    private static final char RED = 'R';
    private static final char OFF = 'O';

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

    public String toDigitalTime(String berlinTime) {
        if (berlinTime == null || berlinTime.length() != 24) {
            throw new IllegalArgumentException("Berlin time must contain exactly 24 lamps");
        }
        validateBerlinTime(berlinTime);

        int second = berlinTime.charAt(0) == 'Y' ? 0 : 1;

        int hour = count(berlinTime.substring(1, 5), 'R') * 5
                + count(berlinTime.substring(5, 9), 'R');

        int minute = countFiveMinuteValue(berlinTime.substring(9, 20)) * 5
                + count(berlinTime.substring(20, 24), 'Y');

        return "%02d:%02d:%02d".formatted(hour, minute, second);
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

    private void validateBerlinTime(String value) {
        if (value.charAt(0) != 'Y' && value.charAt(0) != 'O') throw invalid();
        validateRow(value.substring(1, 5), 'R', 4);
        validateRow(value.substring(5, 9), 'R', 4);
        validateFiveMinutes(value.substring(9, 20));
        validateRow(value.substring(20, 24), 'Y', 4);

        int hour = count(value.substring(1, 5), 'R') * 5 + count(value.substring(5, 9), 'R');
        int minute = countFiveMinuteValue(value.substring(9, 20)) * 5 + count(value.substring(20, 24), 'Y');
        if (hour > 23 || minute > 59) throw invalid();
    }

    private void validateRow(String row, char lit, int max) {
        for (char c : row.toCharArray()) if (c != lit && c != 'O') throw invalid();
        if (count(row, lit) > max) throw invalid();
    }

    private void validateFiveMinutes(String row) {
        for (int i = 0; i < row.length(); i++) {
            char c = row.charAt(i);
            if (c == 'O') continue;
            if (i % 3 == 2 && c != 'R') throw invalid();
            if (i % 3 != 2 && c != 'Y') throw invalid();
        }
    }

    private int countFiveMinuteValue(String row) {

        return count(row, 'Y') + count(row, 'R');
    }

    private int count(String value, char c) {
        return (int) value.chars().filter(ch -> ch == c).count();
    }

    private IllegalArgumentException invalid() {
        return new IllegalArgumentException("Invalid Berlin clock representation");
    }

}
