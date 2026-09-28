package com.bnppkata.berlinclock.controller;

import com.bnppkata.berlinclock.domain.BerlinClock;
import com.bnppkata.berlinclock.service.BerlinClockService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clock")
@CrossOrigin(origins = "http://localhost:5173")
public class BerlinClockController {

    private final BerlinClockService service;

    public BerlinClockController(BerlinClockService service) {
        this.service = service;
    }

    @GetMapping("/to-berlin")
    public String toBerlin(String time) {
        return service.toBerlinClock(time).asString();

    }

    @GetMapping("/to-digital")
    public String toDigital(String berlinTime) {
        return service.toDigitalTime(berlinTime);
    }
}