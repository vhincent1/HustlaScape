package net.scapeemulator.game.model

import net.scapeemulator.game.GameServer
import net.scapeemulator.game.pathfinder.TraversalMap


enum class Direction(private val intValue: Int) {
    NONE(-1), NORTH(1),
    NORTH_EAST(2), EAST(4),
    SOUTH_EAST(7), SOUTH(6),
    SOUTH_WEST(5), WEST(3),
    NORTH_WEST(0);

    fun toInteger(): Int {
        return intValue
    }

    companion object {
        fun between(cur: Position, next: Position): Direction {
            val deltaX = next.x - cur.x
            val deltaY = next.y - cur.y

            when (deltaY) {
                1 -> {
                    when (deltaX) {
                        1 -> return NORTH_EAST
                        0 -> return NORTH
                        -1 -> return NORTH_WEST
                    }
                }

                -1 -> {
                    when (deltaX) {
                        1 -> return SOUTH_EAST
                        0 -> return SOUTH
                        -1 -> return SOUTH_WEST
                    }
                }

                0 -> {
                    when (deltaX) {
                        1 -> return EAST
                        0 -> return NONE
                        -1 -> return WEST
                    }
                }
            }

            throw IllegalArgumentException()
        }

        fun projectileClipping(from: Position, to: Position): Boolean {
            val traversalMap: TraversalMap = GameServer.INSTANCE.world.traversalMap//World.getWorld().getTraversalMap()
            val direction = between(from, to)
            return when (direction) {
                NORTH -> traversalMap.isTraversableNorth(from.height, from.x, from.y, true)
                SOUTH -> traversalMap.isTraversableSouth(from.height, from.x, from.y, true)
                EAST -> traversalMap.isTraversableEast(from.height, from.x, from.y, true)
                WEST -> traversalMap.isTraversableWest(from.height, from.x, from.y, true)
                NORTH_EAST -> traversalMap.isTraversableNorthEast(from.height, from.x, from.y, true)
                NORTH_WEST -> traversalMap.isTraversableNorthWest(
                    from.height, from.x, from.y, true
                )

                SOUTH_EAST -> traversalMap.isTraversableSouthEast(
                    from.height, from.x, from.y, true
                )

                SOUTH_WEST -> traversalMap.isTraversableSouthWest(
                    from.height, from.x, from.y, true
                )

                NONE -> true
                else -> throw RuntimeException("unknown type")
            }
        }
    }
}
