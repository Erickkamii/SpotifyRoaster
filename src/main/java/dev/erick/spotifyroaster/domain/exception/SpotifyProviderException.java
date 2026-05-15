package dev.erick.spotifyroaster.domain.exception;

public class SpotifyProviderException extends RuntimeException {
    public SpotifyProviderException(String message) {
        super(message);
    }

    public SpotifyProviderException(String message, Throwable cause) {
        super(message, cause);
    }
}
