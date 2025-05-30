package net.scapeemulator.game.model

object Interface {
    /* root interfaces */
    const val WELCOME: Int = 549

    //window pane
    const val FIXED: Int = 548
    const val RESIZABLE: Int = 746
    const val CHATBOX: Int = 752

    const val WORLD_MAP: Int = 755

    /* tabs */
    const val INVENTORY: Int = 149
    const val LOGOUT: Int = 182
    const val MUSIC: Int = 187
    const val MAGIC: Int = 192
    const val SETTINGS: Int = 261
    const val PRAYER: Int = 271
    const val QUESTS: Int = 274
    const val SKILLS: Int = 320
    const val EQUIPMENT: Int = 387
    const val EMOTES: Int = 464
    const val FRIENDS: Int = 550
    const val IGNORES: Int = 551
    const val CLAN: Int = 589
    const val SUMMONING: Int = 662


    /* attack tabs */
    const val ATTACK_AXE: Int = 75
    const val ATTACK_MAUL: Int = 76
    const val ATTACK_BOW: Int = 77
    const val ATTACK_CLAWS: Int = 78
    const val ATTACK_LONGBOW: Int = 79
    const val ATTACK_FIXED_DEVICE: Int = 80
    const val ATTACK_GODSWORD: Int = 81
    const val ATTACK_SWORD: Int = 82
    const val ATTACK_PICKAXE: Int = 83
    const val ATTACK_HALBERD: Int = 84
    const val ATTACK_STAFF: Int = 85//spear
    const val ATTACK_SCYTHE: Int = 86
    const val ATTACK_SPEAR: Int = 87
    const val ATTACK_MACE: Int = 88
    const val ATTACK_DAGGER: Int = 89
    const val ATTACK_MAGIC_STAFF: Int = 90
    const val ATTACK_THROWN: Int = 91
    const val ATTACK_UNARMED: Int = 92
    const val ATTACK_WHIP: Int = 93

    /* orbs */
    const val SUMMONING_ORB: Int = 747
    const val HITPOINTS_ORB: Int = 748
    const val PRAYER_ORB: Int = 749
    const val ENERGY_ORB: Int = 750

    /* windows */
    const val DISPLAY_SETTINGS: Int = 742
    const val AUDIO_SETTINGS: Int = 743

}
//fun main() {
//    val attackTabs = arrayOf(
//        SKILLS,
//        QUESTS,
//        INVENTORY,
//        EQUIPMENT,
//        PRAYER,
//        MAGIC,
//        SUMMONING,
//        FRIENDS,
//        IGNORES,
//        CLAN,
//        SETTINGS,
//        EMOTES,
//        MUSIC,
//        LOGOUT
//    )
//    println(attackTabs.sorted())
//
//
//    val map = LinkedHashMap<String, Int>()
//    Interface::class.memberProperties.map {
////        println(it.name)
////        println(it.call(it))
//        val name = it.name
//        val value = it.getter.call() as Int
//        if (attackTabs.contains(value)) map.put(name, value)
//    }
//
//    val sortedMap = map.toSortedMap()
//    println(sortedMap)
//
//    // Sort by values
//    val sortedByValue = map.toList().sortedBy { it.second }
//    println("\nSorted by value:")
//    sortedByValue.forEach { (key, value) ->
//        println("const val $key: Int = $value")
//    }
//
//}