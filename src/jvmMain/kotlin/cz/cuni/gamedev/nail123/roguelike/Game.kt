package cz.cuni.gamedev.nail123.roguelike

import cz.cuni.gamedev.nail123.roguelike.actions.GameAction
import cz.cuni.gamedev.nail123.roguelike.actions.Move
import cz.cuni.gamedev.nail123.roguelike.actions.PickUp
import cz.cuni.gamedev.nail123.roguelike.events.GameStep
import cz.cuni.gamedev.nail123.roguelike.world.Direction
import cz.cuni.gamedev.nail123.roguelike.world.World
import org.hexworks.zircon.internal.Zircon

/**
 * A class containing a state of the game (World) and the game logic.
 */
class Game(val world: World = GameConfig.defaultWorld()) {
    val area
        get() = world.currentArea

    var steps = 0

    /** The possible actions the player may perform. */
    enum class PlayerAction(val action: GameAction) {
        MOVE_NORTH(Move(Direction.NORTH)),
        MOVE_EAST(Move(Direction.EAST)),
        MOVE_WEST(Move(Direction.WEST)),
        MOVE_SOUTH(Move(Direction.SOUTH)),
        MOVE_NORTHEAST(Move(Direction.NORTH_EAST)),
        MOVE_SOUTHEAST(Move(Direction.SOUTH_EAST)),
        MOVE_SOUTHWEST(Move(Direction.SOUTH_WEST)),
        MOVE_NORTHWEST(Move(Direction.NORTH_WEST)),
        PICK_UP(PickUp())
    }

    // The main game loop
    fun step(playerAction: PlayerAction) {
        val actionPerformed = playerAction.action.tryPerform(area)
        if (!actionPerformed) return
        for (entity in area.entities) entity.update()
        ++steps

        GameStep(this).emit()
    }
}