package com.bnppkata.berlinclock.domain;

public record BerlinClock(
        String seconds,
        String fiveHours,
        String singleHours,
        String fiveMinutes,
        String singleMinutes
) {
    public String asString() {
        return seconds + fiveHours + singleHours + fiveMinutes+ singleMinutes;
    }
}