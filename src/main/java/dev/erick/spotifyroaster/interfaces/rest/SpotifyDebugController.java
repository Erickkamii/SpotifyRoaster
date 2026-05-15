package dev.erick.spotifyroaster.interfaces.rest;

import dev.erick.spotifyroaster.application.port.out.SpotifyProvider;
import dev.erick.spotifyroaster.domain.model.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/debug")
public class SpotifyDebugController {
    private final Logger logger = LoggerFactory.getLogger(SpotifyDebugController.class);
    private final SpotifyProvider spotifyProvider;

    public SpotifyDebugController(SpotifyProvider spotifyProvider) {
        this.spotifyProvider = spotifyProvider;
    }

    @GetMapping("/top-tracks")
    public List<Track> topTracks(@RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient){
        logger.debug("Getting tracks from Spotify");
        return spotifyProvider.getTopTracks(authorizedClient.getAccessToken().getTokenValue());
    }
}
