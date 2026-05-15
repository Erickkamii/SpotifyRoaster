package dev.erick.spotifyroaster.domain.model;

import java.util.List;
import java.util.Objects;

public record Track(
        String id,
        String name,
        List<String> artistsNames,
        int popularity,
        boolean explicit
) {
    public Track{
        Objects.requireNonNull(id, "Track ID is required");
        Objects.requireNonNull(name, "Track name is required");

        artistsNames = artistsNames == null ? List.of() : List.copyOf(artistsNames);

        if (popularity < 0 || popularity > 100) {
            throw new IllegalArgumentException("Popularity must be between 0 and 100");
        }
    }
}
