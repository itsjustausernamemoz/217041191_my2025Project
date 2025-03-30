package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.Identifier
import kotlinx.serialization.Serializable

/**
 * Value class representing a word in the Alias game
 * @property word The actual word to be guessed
 */
@Serializable
@JvmInline
value class Word(val word: String)

/**
 * Data class representing a card in the Alias game
 * @property id Unique identifier for the card
 * @property words List of words on the card
 */
@Serializable
data class Card(
    val id: Identifier,
    val words: List<Word>
) 