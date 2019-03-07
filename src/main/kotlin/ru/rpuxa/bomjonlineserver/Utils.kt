package ru.rpuxa.bomjonlineserver

import java.security.SecureRandom
import java.util.*



val secureRandom = SecureRandom()
val random = Random(secureRandom.nextLong())

fun Random.nextInt(from: Int, to: Int) = from + nextInt(to - from)

operator fun StringBuilder.plusAssign(char: Char) {
    append(char)
}