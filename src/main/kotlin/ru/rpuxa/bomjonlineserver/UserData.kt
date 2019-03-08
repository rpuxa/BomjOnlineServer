package ru.rpuxa.bomjonlineserver

typealias HashString = String

class UserData(
    val login: String,
    var password: HashString,
    var email: String,
    var id: Int,
    var token: String
) {

    companion object {


        fun createNewUser(login: String, password: HashString, mail: String): UserData =
            UserData(login, password, mail, dataBase.nextRandomId(), generateToken())

        const val TOKEN_LENGTH = 32

        fun generateToken(): String {
            val builder = StringBuilder()
            repeat(TOKEN_LENGTH) {
                builder += if (secureRandom.nextBoolean()) {
                    secureRandom.nextInt('a'.toInt(), 'z'.toInt()).toChar()
                } else {
                    secureRandom.nextInt('A'.toInt(), 'Z'.toInt()).toChar()
                }
            }
            return builder.toString()
        }
    }
}