package jetbrains.kotlin.course.alias.util

import jetbrains.kotlin.course.alias.card.Card
import jetbrains.kotlin.course.alias.results.GameResult
import jetbrains.kotlin.course.alias.team.Team
import kotlinx.serialization.Serializable

/**
 * Data class representing the complete game state that needs to be saved
 */
@Serializable
data class GameState(
    val gameHistory: List<GameResult>,
    val teamsStorage: Map<Identifier, Team>,
    val lastTeamId: Identifier,
    val lastCardId: Identifier,
    val usedCards: List<Card>
) 