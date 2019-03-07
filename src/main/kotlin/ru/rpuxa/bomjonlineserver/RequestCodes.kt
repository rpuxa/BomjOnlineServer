package ru.rpuxa.bomjonlineserver

import ru.rpuxa.bomjonlineserver.requests.LoginRequest
import ru.rpuxa.bomjonlineserver.requests.RegRequest

object RequestCodes {
    @JvmStatic
    val BAD_ARGUMENTS = error(1)

    @JvmStatic
    val UNKNOWN_TOKEN = error(2)

    @JvmStatic
    val LOGIN_IS_TOO_SHORT = error(3)

    @JvmStatic
    val LOGIN_IS_TOO_LONG = error(4)

    @JvmStatic
    val WRONG_LOGIN_SYMBOLS = error(5)

    @JvmStatic
    val PASSWORD_IS_TOO_SHORT = error(6)

    @JvmStatic
    val INCORRECT_EMAIL = error(7)

    @JvmStatic
    val LOGIN_ALREADY_EXISTS = error(8)

    @JvmStatic
    val EMAIL_ALREADY_USED = error(9)

    @JvmStatic
    val INCORRECT_LOGIN_OR_PASSWORD = error(10)

    private fun error(code: Int) = "{\"error_code\":$code}"

    @JvmStatic
    val ALL_REQUESTS = arrayOf(
        RegRequest,
        LoginRequest
    )
}