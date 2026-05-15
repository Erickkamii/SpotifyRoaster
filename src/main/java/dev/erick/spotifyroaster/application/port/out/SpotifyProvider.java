package dev.erick.spotifyroaster.application.port.out;

import dev.erick.spotifyroaster.domain.model.Artist;
import dev.erick.spotifyroaster.domain.model.Track;

import java.util.List;

public interface SpotifyProvider {
    List<Track> getTopTracks(String accessToken);

    List<Artist> getTopArtists(String accessToken);
}
