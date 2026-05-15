package dev.erick.spotifyroaster.infrastructure.spotify.dto;

import dev.erick.spotifyroaster.domain.model.Track;

import java.util.List;

public record SpotifyTrackResponse(
        String id,
        String name,
        List<SpotifyArtistSummaryResponse> artists,
        Integer popularity,
        Boolean explicit
) {
    public Track toDomain(){
        var artistsName = artists == null
                ? List.<String>of()
                : artists.stream()
                    .map(SpotifyArtistSummaryResponse::name)
                    .toList();

        return new Track(
                id,
                name,
                artistsName,
                popularity != null ? popularity : 0,
                explicit
        );
    }

    public record SpotifyArtistSummaryResponse(
            String id,
            String name
    ){
    }
}
