package com.hibernate.exception;

public class RaceResultException extends Exception {
    public RaceResultException(String message) {
        super(message);
    }

    public RaceResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class PrimaryKeyViolationException extends RaceResultException {
        public PrimaryKeyViolationException(String message) {
            super(message);
        }

        public PrimaryKeyViolationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class PositionOccupiedException extends RaceResultException {
        public PositionOccupiedException(String message) {
            super(message);
        }

        public PositionOccupiedException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class RaceResultInsertionException extends RaceResultException {
        public RaceResultInsertionException(String message) {
            super(message);
        }

        public RaceResultInsertionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
