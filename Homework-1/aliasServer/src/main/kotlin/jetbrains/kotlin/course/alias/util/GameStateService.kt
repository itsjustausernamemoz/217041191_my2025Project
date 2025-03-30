package jetbrains.kotlin.course.alias.util

import jetbrains.kotlin.course.alias.card.Card
import jetbrains.kotlin.course.alias.card.CardService
import jetbrains.kotlin.course.alias.results.GameResult
import jetbrains.kotlin.course.alias.results.GameResultsService
import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service
import java.io.File
import java.io.IOException

/**
 * Service responsible for saving and loading the game state
 */
@Service
class GameStateService {
    companion object {
        private const val SAVE_FILE_PATH = "game_state.json"
    }

    /**
     * Saves the current game state to a file
     * @throws IOException if there's an error writing to the file
     */
    fun saveGameState() {
        val gameState = GameState(
            gameHistory = GameResultsService.gameHistory,
            teamsStorage = TeamService.teamsStorage,
            lastTeamId = TeamService().identifierFactory.getLastId(),
            lastCardId = CardService().identifierFactory.getLastId(),
            usedCards = CardService().cards
        )

        try {
            val jsonString = Json.encodeToString(gameState)
            File(SAVE_FILE_PATH).writeText(jsonString)
        } catch (e: IOException) {
            throw IOException("Failed to save game state: ${e.message}")
        }
    }

    /**
     * Loads the game state from the file if it exists
     * @throws IOException if there's an error reading from the file
     */
    fun loadGameState() {
        val file = File(SAVE_FILE_PATH)
        if (!file.exists()) return

        try {
            val jsonString = file.readText()
            val gameState = Json.decodeFromString<GameState>(jsonString)

            // Restore game history to allow review of previous results
            GameResultsService.gameHistory.clear()
            GameResultsService.gameHistory.addAll(gameState.gameHistory)

            // Restore teams storage
            TeamService.teamsStorage.clear()
            TeamService.teamsStorage.putAll(gameState.teamsStorage)

            // Restore identifier factories
            TeamService().identifierFactory.setLastId(gameState.lastTeamId)
            CardService().identifierFactory.setLastId(gameState.lastCardId)


        } catch (e: IOException) {
            throw IOException("Failed to load game state: ${e.message}")
        }
    }
} 