package ru.rpuxa.bomjonlineserver.requests

import ru.rpuxa.bomjonlineserver.Request
import ru.rpuxa.bomjonlineserver.RequestCodes
import ru.rpuxa.bomjonlineserver.RequestCodes.BAD_ARGUMENTS
import ru.rpuxa.bomjonlineserver.RequestCodes.EMAIL_ALREADY_USED
import ru.rpuxa.bomjonlineserver.RequestCodes.INCORRECT_EMAIL
import ru.rpuxa.bomjonlineserver.RequestCodes.LOGIN_ALREADY_EXISTS
import ru.rpuxa.bomjonlineserver.RequestCodes.LOGIN_IS_TOO_LONG
import ru.rpuxa.bomjonlineserver.RequestCodes.LOGIN_IS_TOO_SHORT
import ru.rpuxa.bomjonlineserver.RequestCodes.PASSWORD_IS_TOO_SHORT
import ru.rpuxa.bomjonlineserver.RequestCodes.WRONG_LOGIN_SYMBOLS
import ru.rpuxa.bomjonlineserver.UserData
import ru.rpuxa.bomjonlineserver.dataBase
import java.util.regex.Pattern

object RegRequest : Request("reg") {
    override fun request(query: Query): String {
        val login = query["login"] ?: return BAD_ARGUMENTS
        val password = query["password"] ?: return BAD_ARGUMENTS
        val mail = query["mail"] ?: return BAD_ARGUMENTS

        if (login.length < LOGIN_MIN_LENGTH)
            return LOGIN_IS_TOO_SHORT
        if (login.length > LOGIN_MAX_LENGTH)
            return LOGIN_IS_TOO_LONG
        if (dataBase.userByLogin(login) != null)
            return LOGIN_ALREADY_EXISTS

        if (!login.all { it in LOGIN_SYMBOLS })
            return WRONG_LOGIN_SYMBOLS
        if (password.length < PASSWORD_MIN_LENGTH)
            return PASSWORD_IS_TOO_SHORT

        if (!isEmail(mail))
            return INCORRECT_EMAIL
        if (dataBase.userByMail(mail) != null)
            return EMAIL_ALREADY_USED

        val user = UserData.createNewUser(login, password, mail)
        dataBase.updateUser(user)

        return ("token" to user.token).toJSON()
    }

    private fun isEmail(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$"

        return Pattern.compile(emailRegex).matcher(email).matches()
    }


    private const val LOGIN_MIN_LENGTH = 4
    private const val LOGIN_MAX_LENGTH = 16
    @JvmStatic
    private val LOGIN_SYMBOLS = arrayOf(
        '-', '_',
        *('0'..'9').toSet().toTypedArray(),
        *('a'..'z').toSet().toTypedArray(),
        *('A'..'Z').toSet().toTypedArray(),
        *('а'..'я').toSet().toTypedArray(),
        *('А'..'Я').toSet().toTypedArray()
    )
    private const val PASSWORD_MIN_LENGTH = 5
}