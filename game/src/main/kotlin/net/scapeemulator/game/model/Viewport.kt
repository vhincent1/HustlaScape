package net.scapeemulator.game.model

class Viewport {
    companion object {
        const val CHUNK_SIZE = 5
    }

    val region: Region
    private val chunks: Array<Array<RegionChunk?>> = Array(CHUNK_SIZE) { arrayOfNulls(CHUNK_SIZE) }


    constructor(region: Region) {
        this.region = region
    }
}