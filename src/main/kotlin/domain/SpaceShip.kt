package domain

import java.util.*
import kotlin.collections.HashSet

class SpaceShip(val name: String, maxHealth: Int, private val maxCrew: Int) : Damageable {

    var health: Int = maxHealth
        private set(value) {
            field = if (value > 0) value else 0
        }

    val crew: HashSet<Person> = HashSet()

    private val weapons: EnumMap<Shells, Weapon> = EnumMap(Shells::class.java)

    val isDead: Boolean
        get() = this.health <= 0

    override fun takeDamage(shell: Shells, amount: Int) {
        damage(shell.damage * amount)
    }

    fun addCrewMember(member: Person) {
        if (crew.size < maxCrew) crew.add(member)
        else throw Exception("Crew is full")
    }

    fun removeCrewMember(member: Person) {
        crew.remove(member)
    }

    private fun damage(healthPoints: Int) {
        health -= healthPoints
    }

    fun addWeapon(weapon: Weapon) {
        if (weapons[weapon.shell] != null) throw Exception("Weapon with ${weapon.shell} already exists")
        weapons[weapon.shell] = weapon
    }

    fun removeWeapon(weapon: Weapon) {
        weapons.remove(weapon.shell)
    }

    fun shootFromWeapon(shell: Shells, aim: Damageable, shellAmount: Int) {
        val weapon = weapons[shell] ?: throw Exception("No weapon with $shell")
        weapon.shoot(aim, shellAmount)
    }
}