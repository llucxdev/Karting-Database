package com.hibernate.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class LapTimer {
    private boolean onLap = false;
    private LocalDateTime start;
    private LocalDateTime finish;

    public void startLap() {
        if (!onLap) {
            onLap = true;
            this.start = LocalDateTime.now();
        } else {
            throw new IllegalStateException("Someone is already on lap!");
        }
    }

    public Timestamp finishLap() {
        if (onLap) {
        	onLap = false;
            finish = LocalDateTime.now();
            Duration durationLap = Duration.between(start, finish);
            long millis = durationLap.toMillis();
            Timestamp lapTimestamp = new Timestamp(millis);
            return lapTimestamp;
        } else {
            throw new IllegalStateException("No one is on lap!");
        }
    }

	public boolean isOnLap() {
		return onLap;
	}

	public void setOnLap(boolean onLap) {
		this.onLap = onLap;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getFinish() {
		return finish;
	}

	public void setFinish(LocalDateTime finish) {
		this.finish = finish;
	}
	
}
