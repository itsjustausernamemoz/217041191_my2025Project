package jetbrains.kotlin.course.alias.results

import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Service

/**
 * Type alias for game results
 */
typealias GameResult = List<Team>

/**
 * Service class responsible for managing game results
 */
@Service
class GameResultsService {
    companion object {
        /**
         * Storage for all game results
         */
        val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    /**
     * Saves the results of a game
     * @param result List of teams with their final scores
     * @throws IllegalArgumentException if the result is empty or contains invalid team IDs
     */
    fun saveGameResults(result: GameResult) {
        require(result.isNotEmpty()) { "Game result cannot be empty" }
        require(result.all { team -> team.id in TeamService.teamsStorage }) {
            "All teams in the result must exist in the teams storage"
        }
        gameHistory.add(result)
    }

    /**
     * Retrieves all game results in reverse chronological order
     * @return List of game results
     */
    fun getAllGameResults(): List<GameResult> = gameHistory.reversed()
}
