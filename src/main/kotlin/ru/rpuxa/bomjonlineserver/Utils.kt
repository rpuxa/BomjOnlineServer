package ru.rpuxa.bomjonlineserver

import com.google.gson.Gson
import java.security.SecureRandom
import java.util.*


val secureRandom = SecureRandom()
val random = Random(secureRandom.nextLong())

fun Random.nextInt(from: Int, to: Int) = from + nextInt(to - from)

operator fun StringBuilder.plusAssign(char: Char) {
    append(char)
}

private val gson = Gson()

fun Map<String, Any>.toJSON(): String {
    return gson.toJson(this)
}

fun Pair<String, Any>.toJSON() = mapOf(this).toJSON()