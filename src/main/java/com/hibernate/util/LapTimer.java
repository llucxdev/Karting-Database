package com.hibernate.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LapTimer {
    private boolean onLap = false;
    private LocalDateTime start;
    private LocalDateTime finish;

    public void startLap() {
        if (!onLap) {
            onLap = true;
            start = LocalDateTime.now();
        } else {
            throw new IllegalStateException("Someone is already on lap!");
        }
    }

    public LocalTime finishLap() {
        if (onLap) {
        	onLap = false;
            finish = LocalDateTime.now();
            Duration durationLap = Duration.between(start, finish);
            return LocalTime.MIN.plus(durationLap);
        } else {
            throw new IllegalStateException("No one is on lap!");
        }
    }
}
