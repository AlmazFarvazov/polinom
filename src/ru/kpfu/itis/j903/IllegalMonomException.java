package ru.kpfu.itis.j903;

public class IllegalMonomException extends RuntimeException {
    public IllegalMonomException() {
    }

    public IllegalMonomException(String s) {
        super(s);
    }

    public IllegalMonomException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public IllegalMonomException(Throwable throwable) {
        super(throwable);
    }

    public IllegalMonomException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
