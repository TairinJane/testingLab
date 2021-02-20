package domain

class Weapon(val name: String, val shell: Shells, var shellCount: Int) {

    fun shoot(aim: Damageable, amount: Int) {
        if (shellCount < amount) throw Exception("Not enough shells: $shellCount of $amount")
        shellCount -= amount
        aim.takeDamage(shell, amount)
    }

    fun addShells(amount: Int) {
        shellCount += amount
    }
}