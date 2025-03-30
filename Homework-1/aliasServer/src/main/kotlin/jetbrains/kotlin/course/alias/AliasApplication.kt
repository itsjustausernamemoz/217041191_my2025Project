package jetbrains.kotlin.course.alias

import jetbrains.kotlin.course.alias.util.GameStateService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext

/**
 * Main application class for the Alias game
 */
@SpringBootApplication
class AliasApplication

fun main(args: Array<String>) {
    val context = runApplication<AliasApplication>(*args)
    val gameStateService = context.getBean(GameStateService::class.java)
    
    try {
        // Loading the saved game state if it exists
        gameStateService.loadGameState()
        
        // Add shutdown hook to save game state
        Runtime.getRuntime().addShutdownHook(Thread {
            gameStateService.saveGameState()
        })
        
        // Keeping the application running to avoid crashing
        Thread.currentThread().join()
    } catch (e: Exception) {
        println("Error during game state management: ${e.message}")
        e.printStackTrace()
    }
}



