package dev.erick.spotifyroaster.application.port.out;

import dev.erick.spotifyroaster.domain.model.RoastAnalysis;
import dev.erick.spotifyroaster.domain.model.UserProfile;

public interface AiAnalyzisProvider {
    RoastAnalysis analyze(UserProfile profile);
}
