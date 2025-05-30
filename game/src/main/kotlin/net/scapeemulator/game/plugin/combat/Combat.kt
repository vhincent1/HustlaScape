package net.scapeemulator.game.plugin.combat

import net.scapeemulator.game.GameServer
import net.scapeemulator.game.model.Mob
import net.scapeemulator.game.model.Npc
import net.scapeemulator.game.model.Player
import net.scapeemulator.game.model.WeaponClass
import net.scapeemulator.game.plugin.combat.CombatPlugin.setCombat

class Combat(val source: Mob, val target: Mob) {
    private var combatTimeout = 0

    // todo: clean this
    private fun getAttacker(): Mob? = if (source is Npc)
        GameServer.INSTANCE.world.npcs[source.index]
    else
        GameServer.INSTANCE.world.players[source!!.index]

    private fun getVictim(): Mob? = if (target is Npc)
        GameServer.INSTANCE.world.npcs[target.index]
    else
        GameServer.INSTANCE.world.players[target.index]

    private fun getHandler(): CombatHandler? {
        if (source is Player) {
            return when (source.settings.weaponClass) {

                /* magic */
                WeaponClass.STAFF -> {
                    // if no spell is selected
                    Melee()
                }
                /* ranged weapons */
                WeaponClass.BOW,
                WeaponClass.CHINCHOMPA,
                WeaponClass.CROSSBOW,
                WeaponClass.FIXED_DEVICE,
                WeaponClass.MUD_PIE,
                WeaponClass.SALAMANDER,
                WeaponClass.THROWN -> {
                    null
                }

                else -> Melee()
            }
        }
        return null
    }

    internal fun tick() {
        //todo fix when target dies/respawns /isOnline/ Logsout
        val victim = getVictim()
        val handler = getHandler() ?: return

        val nextAttack = CombatPlugin.nextAttack(source)
        if (CombatPlugin.DEBUG) print("next=$nextAttack ")

        if (victim == null) {
            stop()
            return
        }

        if (!handler.canHit(source, victim)) {
            print("timeout=$combatTimeout ")
            if (combatTimeout++ > 10) stop()
            return
        }

        if (source.walkingQueue.isMoving()) stop()

        combatTimeout = 0
        CombatPlugin.setIsAttacking(source, true)
//isAttacking =true ?
        CombatPlugin.setNextAttack(source, nextAttack - 1)
        if (nextAttack < 1) {
            source.faceMob = victim

            val speed = handler.attack(source, victim)
            handler.visualImpact(source, victim)
            CombatPlugin.setNextAttack(source, speed)
        }

        if (nextAttack <= 0) {
            val damage = handler.impact(source, victim)
//            target.appendHit(damage, HitType.NONE)
            println("---------------------------------")
            autoRetaliate() // move down
        }
    }

    internal fun stop() {
        if (source == null) return
        if (CombatPlugin.DEBUG) println("STOPPING COMBAT")
        CombatPlugin.setNextAttack(source, -1)
        CombatPlugin.setIsAttacking(source, false)
        source.setCombat(null)
        source.faceMob = null
    }
}