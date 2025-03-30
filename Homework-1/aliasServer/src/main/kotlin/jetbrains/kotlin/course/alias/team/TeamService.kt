package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import org.springframework.stereotype.Service

/**
 * Service class responsible for managing teams in the Alias game
 */
@Service
class TeamService {
    /**
     * Factory for generating unique team identifiers
     */
    val identifierFactory: IdentifierFactory = IdentifierFactory()

    companion object {
        /**
         * Storage for all teams that have participated in games
         */
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()
    }

    /**
     * Generates teams for one round of the game
     * @param teamsCount Number of teams to generate
     * @return List of generated teams
     */
    fun generateTeamsForOneRound(teamsCount: Int): List<Team> {
        val teams = List(teamsCount) {
            val id = identifierFactory.uniqueIdentifier()
            Team(id).also { team -> teamsStorage[id] = team }
        }
        return teams
    }
}
