package cz.cuni.gamedev.nail123.roguelike.gui

import cz.cuni.gamedev.nail123.roguelike.GameConfig
import cz.cuni.gamedev.nail123.roguelike.world.Area
import org.hexworks.zircon.api.data.Position3D
import kotlin.math.max

class CameraMover(val area: Area) {
    fun update() {
        val player = area.player

        val leeway = GameConfig.WORLD_SIZE - GameConfig.VISIBLE_SIZE
        val maxScrollX = max(leeway.xLength, 0)
        val maxScrollY = max(leeway.yLength, 0)

        Position3D.create(
                (player.x - GameConfig.VISIBLE_SIZE.xLength / 2).coerceIn(0, maxScrollX),
                (player.y - GameConfig.VISIBLE_SIZE.yLength / 2).coerceIn(0, maxScrollY),
                player.z
        ).let {
            area.scrollTo(it)
        }
    }
}