package dev.erick.spotifyroaster.infrastructure.spotify.dto;

import dev.erick.spotifyroaster.domain.model.Artist;

import java.util.List;

public record SpotifyArtistsResponse(
        String id,
        String name,
        List<String> genres
) {
    public Artist toDomain(){
        return new Artist(id, name, genres);
    }
}
