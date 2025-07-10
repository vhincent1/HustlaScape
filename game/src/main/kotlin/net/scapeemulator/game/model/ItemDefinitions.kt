package net.scapeemulator.game.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import net.scapeemulator.cache.*
import java.io.File
import java.io.IOException
import java.util.*

//todo item durability
object ItemDefinitions {
    private val logger = KotlinLogging.logger {}

    //    private lateinit var definitions: MutableMap<Int, ItemDefinition>
    private lateinit var definitions: Array<ItemDefinition>

    @JvmStatic
    @Throws(IOException::class)
    fun init(cache: Cache) {
        var count = 0
        val tableContainer = Container.decode(cache.store.read(255, 19))
        val table = ReferenceTable.decode(tableContainer.getData())
        val files = table.capacity()
//        definitions = arrayOfNulls(files * 256)

        for (file in 0..<files) {
            val entry = table.getEntry(file) ?: continue

            val archive = Archive.decode(cache.read(19, file).getData(), entry.size())
            var nonSparseMember = 0
            for (member in 0..<entry.capacity()) {
                val childEntry = entry.getEntry(member) ?: continue

                val id = file * 256 + member
                val definition =
                    net.scapeemulator.cache.def.ItemDefinition.decode(id, archive.getEntry(nonSparseMember++)!!)

//                definitions[id] = definition(id)
                count++
            }
        }
        logger.info { "Loaded $count item definitions." }
    }

    fun init(file: File) {
        val mapper: ObjectMapper = jacksonObjectMapper()
//        val map: MutableMap<Int, ItemDefinition> =
//            mapper.readValue(file, object : TypeReference<MutableMap<Int, ItemDefinition>>() {})
        //        definitions = map
        val map: Array<ItemDefinition> = mapper.readValue(file, object : TypeReference<Array<ItemDefinition>>() {})
        definitions = map
        logger.info { "Loaded ${definitions.size} item definitions" }
    }

    fun forId(id: Int): ItemDefinition? {
        val find = definitions.find { it.id == id }
        if (find == null) logger.debug { "Unknown item definition $id" }
        return find
    }

    fun getDefinitions() = definitions
}


fun dump() {
    val mapper: ObjectMapper = jacksonObjectMapper()
    // Load from a File
    val path = "./game/src/main/resources/data"
    val file = File("$path/itemDefinitionsOLD.json")
    val map: MutableMap<Int, ItemDefinition> =
        mapper.readValue(file, object : TypeReference<MutableMap<Int, ItemDefinition>>() {})
    KotlinLogging.logger { }.info { "Loaded ${map.size} item definitions" }

    val copy = map

    //configs
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class ItemConfig {
        val id: Int = 0
        val alchemizable: Boolean? = null
        val defence_anim: Int? = null
        val equip_audio: Int? = null
        val shop_price: Int? = null
        val ge_buy_limit: Int? = null
        val grand_exchange_price: Int? = null
        val tradeable: Boolean? = null

        val remove_head: Boolean? = null
        val remove_beard: Boolean? = null
        val remove_sleeves: Boolean? = null
    }

    val file2 = File("$path/item_configs.json")
    val itemConfigs: Array<ItemConfig> = mapper.readValue(file2, object : TypeReference<Array<ItemConfig>>() {})
    KotlinLogging.logger { }.info { "Loaded ${itemConfigs.size} item definitions" }

    //modify
    map.onEach { def ->
        copy[def.key] = def.value.also { it ->
            if (it.attackSpeed == 0) it.attackSpeed = null
            if (it.shopPrice == 0) it.shopPrice = null
            if (it.pointPrice == 0) it.pointPrice = null
            it.grandExchangeLimit = itemConfigs.find { it.id == def.key }?.ge_buy_limit
            it.grandExchangePrice = itemConfigs.find { it.id == def.key }?.grand_exchange_price
            it.alchemizable = itemConfigs.find { it.id == def.key }?.alchemizable
            it.defenceAnimation = itemConfigs.find { it.id == def.key }?.defence_anim
            it.shopPrice = itemConfigs.find { it.id == def.key }?.shop_price
            it.equipSound = itemConfigs.find { it.id == def.key }?.equip_audio
            it.tradeable = itemConfigs.find { it.id == def.key }?.tradeable
            it.isFullHelm = itemConfigs.find { it.id == def.key }?.remove_head
            it.isFullBody = itemConfigs.find { it.id == def.key }?.remove_sleeves
            it.isFullMask = itemConfigs.find { it.id == def.key }?.remove_beard
        }
    }

//write
    val writer = mapper.writer(DefaultPrettyPrinter())
    writer?.writeValue(File("$path/itemDefinitions.json"), copy.values.toTypedArray())
//    val file3 = File("$path/itemDefs-new.json")
//    val newItems: Array<ItemDefinition> = mapper.readValue(file3, object : TypeReference<Array<ItemDefinition>>() {})
//    KotlinLogging.logger { }.info { "Loaded ${newItems.size} item definitions" }
}

fun main() {
//    dump()
    val search = "partyhat"
    val path = "./game/src/main/resources/data"
    ItemDefinitions.init(File("$path/itemDefinitions.json"))
    val list = ItemDefinitions.getDefinitions().filter {
        it.name.lowercase().contains(search) && it.lendable && it.unnoted
    }
    list.forEach {
        println(it)
        println(it.equipmentSlot)
        println(it.equipmentId)
    }
}


fun main2(){
    ItemDefinitions.init(Cache(FileStore.Companion.open("./game/src/main/resources/data/cache")))
}
