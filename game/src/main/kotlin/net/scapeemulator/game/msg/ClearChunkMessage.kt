package net.scapeemulator.game.msg

import net.scapeemulator.game.model.Player
import net.scapeemulator.game.model.RegionChunk

data class ClearChunkMessage(val player: Player, val chunk: RegionChunk) : Message