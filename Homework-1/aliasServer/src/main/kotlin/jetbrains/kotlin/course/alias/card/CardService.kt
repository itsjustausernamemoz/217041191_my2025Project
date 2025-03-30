package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import jetbrains.kotlin.course.alias.util.words
import org.springframework.stereotype.Service

/**
 * Service class responsible for managing cards in the Alias game
 */
@Service
class CardService {
    /**
     * Factory for generating unique card identifiers
     */
    val identifierFactory: IdentifierFactory = IdentifierFactory()

    companion object {
        /**
         * Number of words per card
         */
        const val WORDS_IN_CARD = 4

        /**
         * Total number of cards based on available words
         */
        val cardsAmount: Int = words.size / WORDS_IN_CARD
    }

    /**
     * List of all available cards
     */
    val cards: List<Card> = generateCards()

    /**
     * Extension function to convert a list of strings to a list of Word objects
     * @return List of Word objects
     */
    private fun List<String>.toWords(): List<Word> = map { Word(it) }

    /**
     * Generates the list of cards for the game
     * @return List of generated cards
     */
    private fun generateCards(): List<Card> {
        val shuffledWords = words.shuffled()
        return shuffledWords
            .chunked(WORDS_IN_CARD)
            .take(cardsAmount)
            .mapIndexed { index, wordList ->
                Card(
                    id = identifierFactory.uniqueIdentifier(),
                    words = wordList.toWords()
                )
            }
    }

    /**
     * Retrieves a card by its index
     * @param index The index of the card to retrieve
     * @return The card at the specified index
     * @throws IllegalArgumentException if the index is out of bounds
     */
    fun getCardByIndex(index: Int): Card {
        require(index in 0 until cards.size) { "Card index out of bounds" }
        return cards[index]
    }
}
