package dev.erick.spotifyroaster.infrastructure.spotify;

import dev.erick.spotifyroaster.application.port.out.SpotifyProvider;
import dev.erick.spotifyroaster.domain.exception.SpotifyProviderException;
import dev.erick.spotifyroaster.domain.model.Track;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Component
public class SpotifyProviderAdapter implements SpotifyProvider {

    private static final String DEFAULT_TIME = "medium_term";
    private static final int DEFAULT_LIMIT = 20;

    private final SpotifyApiClient spotifyApiClient;

    public  SpotifyProviderAdapter(SpotifyApiClient spotifyApiClient) {
        this.spotifyApiClient = spotifyApiClient;
    }

    @Override
    public List<Track> getTopTracks(String accessToken) {
        try{
            var response = spotifyApiClient.getTopTracks(
                    bearer(accessToken),
                    DEFAULT_TIME,
                    DEFAULT_LIMIT
            );
            if (response == null || response.items() == null || response.items().isEmpty()) {
                return List.of();
            }
            return response.items()
                    .stream()
                    .map(t -> t.toDomain())
                    .toList();
        } catch (HttpClientErrorException e){
            throw mapException(e);
        } catch (RestClientException e){
            throw new SpotifyProviderException("Failed to fetch top track from Spotify.",e);
        }
    }

    private String bearer(String accessToken) {
        return "Bearer " + accessToken;
    }

    private SpotifyProviderException mapException(HttpClientErrorException e){
        if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return new SpotifyProviderException("Spotify access token is invalid or expired.", e);
        }
        if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
            return new SpotifyProviderException("Spotify access denied, check if the OAuth scope user-top-read was granted.", e);
        }
        if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS){
            return new SpotifyProviderException("Spotify rate limit exceeded. Try again later.", e);
        }
        if (e.getStatusCode() == HttpStatus.NOT_FOUND){
            return new SpotifyProviderException("Spotify resource not found.", e);
        }

        return new SpotifyProviderException("Spotify request failed with status "+e.getStatusCode(), e);
    }
}
