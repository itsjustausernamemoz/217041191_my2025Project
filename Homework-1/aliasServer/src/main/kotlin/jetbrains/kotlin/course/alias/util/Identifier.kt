package jetbrains.kotlin.course.alias.util

/**
 * Type alias for unique identifiers used throughout the application
 */
typealias Identifier = Int

/**
 * Factory class for generating unique identifiers
 */
class IdentifierFactory {
    /**
     * Counter to keep track of the last used identifier
     */
    private var counter: Int = 0

    /**
     * Generates and returns a new unique identifier
     * @return A new unique identifier
     */
    fun uniqueIdentifier(): Identifier = ++counter

    /**
     * Gets the last used identifier
     * @return The last used identifier
     */
    fun getLastId(): Identifier = counter

    /**
     * Sets the last used identifier
     * @param id The identifier to set as the last used one
     */
    fun setLastId(id: Identifier) {
        counter = id
    }
} 