package de.gothaer.collection;

public class StapelException extends Throwable {
    public StapelException() {
    }

    public StapelException(final String message) {
        super(message);
    }

    public StapelException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public StapelException(final Throwable cause) {
        super(cause);
    }

    public StapelException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
