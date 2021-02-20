import domain.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DomainTest {

    private val spaceShip = SpaceShip("Space Test", 200, 3)
    private val enemyShip = SpaceShip("Enemy Ship", 100, 1)

    private val ford = Person("Ford", 34, Positions.COMMANDER)
    private val elis = Person("Elis", 42, Positions.FLIGHT_ENGINEER)
    private val zafod = Person("Zafod", 29, Positions.MISSION_SPECIALIST)
    private val doge = Person("Doge", 2, Positions.SECOND_PILOT)

    private val rocketWeapon = Weapon("Rockets", Shells.ROCKET, 20)
    private val bombWeapon = Weapon("Bombs", Shells.BOMB, 5)
    private val nukeWeapon = Weapon("Nuke!!!!", Shells.NUCLEAR_BOMB, 1)

    @Test
    fun canAddToCrew() {
        with(spaceShip) {
            addCrewMember(ford)
            addCrewMember(elis)
            addCrewMember(zafod)
        }
        Assertions.assertEquals(hashSetOf(ford, elis, zafod), spaceShip.crew)
    }

    @Test
    fun cantAddToCrewMoreThanCapacity() {
        with(spaceShip) {
            addCrewMember(ford)
            addCrewMember(elis)
            addCrewMember(zafod)
        }
        Assertions.assertThrows(Exception::class.java) { spaceShip.addCrewMember(doge) }
    }

    @Test
    fun canRemoveCrewMember() {
        with(spaceShip) {
            addCrewMember(ford)
            addCrewMember(elis)
            addCrewMember(zafod)
        }
        spaceShip.removeCrewMember(zafod)
        Assertions.assertEquals(hashSetOf(ford, elis), spaceShip.crew)
    }

    @Test
    fun canAddShellsToWeapon() {
        rocketWeapon.addShells(10)
        Assertions.assertEquals(30, rocketWeapon.shellCount)
    }

    @Test
    fun cantAddSameShellWeapon() {
        spaceShip.addWeapon(rocketWeapon)
        Assertions.assertThrows(Exception::class.java) { spaceShip.addWeapon(rocketWeapon) }
    }

    @Test
    fun canShootShellsFromWeapon() {
        spaceShip.addWeapon(rocketWeapon)
        spaceShip.shootFromWeapon(Shells.ROCKET, enemyShip, 1)
        Assertions.assertEquals(19, rocketWeapon.shellCount)
        Assertions.assertEquals(90, enemyShip.health)
    }

    @Test
    fun canDestroyShip() {
        spaceShip.addWeapon(nukeWeapon)
        spaceShip.shootFromWeapon(Shells.NUCLEAR_BOMB, enemyShip, 1)
        Assertions.assertEquals(true, enemyShip.isDead)
    }

    @Test
    fun canRemoveWeapon() {
        spaceShip.addWeapon(bombWeapon)
        Assertions.assertThrows(Exception::class.java) { spaceShip.addWeapon(bombWeapon) }
        spaceShip.removeWeapon(bombWeapon)
        Assertions.assertDoesNotThrow { spaceShip.addWeapon(bombWeapon) }
    }

}