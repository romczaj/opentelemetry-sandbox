package pl.romczaj.opentelemetry.internal;

import java.util.concurrent.TimeUnit;

public class MockDelay {

    public static void ofMilliseconds(Integer milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
