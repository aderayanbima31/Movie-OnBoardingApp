package id.dana.onboarding.movie.exception;


import android.content.Context;

import id.dana.data.exception.MovieNotFoundException;
import id.dana.data.exception.NetworkConnectionException;
import id.dana.onboarding.movie.R;

public class ErrorMessageFactory {

    private ErrorMessageFactory() {
    }

    /**
     * Creates a String representing an error message
     */
    public static String createErrorMessage(Context context, Exception exception) {
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException) {
            message = context.getString(R.string.exception_message_no_connection);
        } else if (exception instanceof MovieNotFoundException) {
            message = context.getString(R.string.exception_message_movie_not_found);
        }

        return message;
    }
}
