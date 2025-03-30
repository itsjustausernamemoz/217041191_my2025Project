package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.Identifier
import kotlinx.serialization.Serializable

/**
 * Data class representing a team in the Alias game
 * @property id Unique identifier for the team
 * @property points Current score of the team
 * @property name Name of the team, automatically generated as "Team#${id + 1}"
 */
@Serializable
data class Team(
    val id: Identifier,
    var points: Int = 0
) {
    val name: String = "Team#${id + 1}"
} 