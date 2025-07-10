package net.scapeemulator.game.msg.codec

import net.scapeemulator.game.msg.ClearChunkMessage
import net.scapeemulator.game.net.game.DataTransformation
import net.scapeemulator.game.net.game.DataType
import net.scapeemulator.game.net.game.GameFrameBuilder

internal val ClearRegionChunkEncoder = MessageEncoder(ClearChunkMessage::class) { alloc, message ->
    val builder = GameFrameBuilder(alloc, 112)
    val lastKnownRegion = message.player.lastKnownRegion ?: return@MessageEncoder builder.toGameFrame()
//    Location l = context.getPlayer().getPlayerFlags().getLastSceneGraph();
//    int x = context.getChunk().getCurrentBase().getSceneX(l);
//    int y = context.getChunk().getCurrentBase().getSceneY(l);
    val x = message.chunk.base.getRegionX(lastKnownRegion)
    val y = message.chunk.base.getRegionY(lastKnownRegion)
    builder.put(DataType.BYTE, x)
    builder.put(DataType.BYTE, DataTransformation.NEGATE, y)
//    if (x >= 0 && y >= 0 && x < 96 && y < 96)
    return@MessageEncoder builder.toGameFrame()
}