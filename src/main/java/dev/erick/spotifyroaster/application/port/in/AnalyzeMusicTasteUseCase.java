package dev.erick.spotifyroaster.application.port.in;

import dev.erick.spotifyroaster.domain.model.RoastAnalysis;

public interface AnalyzeMusicTasteUseCase {
    RoastAnalysis analyze(String accessToken);
}
