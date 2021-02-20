package domain

interface Damageable {

    fun takeDamage(shell: Shells, amount: Int)
}