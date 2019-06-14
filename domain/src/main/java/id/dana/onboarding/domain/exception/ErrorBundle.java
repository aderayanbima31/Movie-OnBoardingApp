package id.dana.onboarding.domain.exception;

/**
 * Interface to represent a wrapper around an exception to manage errors.
 */
public interface ErrorBundle {

    Exception getException();

    String getErrorMessage();
}
