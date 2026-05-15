package dev.erick.spotifyroaster.domain.model;


import java.util.List;
import java.util.Objects;

public record Artist(
        String id,
        String name,
        List<String> genres,
        int popularity
) {
    public Artist{
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");

        genres = genres == null ? List.of() : List.copyOf(genres);

        if (popularity < 0 || popularity > 100) {
            throw new IllegalArgumentException("popularity must be between 0 and 100");
        }
    }
}
