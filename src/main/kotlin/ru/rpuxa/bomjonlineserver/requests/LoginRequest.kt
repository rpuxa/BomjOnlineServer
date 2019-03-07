package ru.rpuxa.bomjonlineserver.requests

import ru.rpuxa.bomjonlineserver.Request
import ru.rpuxa.bomjonlineserver.RequestCodes
import ru.rpuxa.bomjonlineserver.RequestCodes.BAD_ARGUMENTS
import ru.rpuxa.bomjonlineserver.RequestCodes.INCORRECT_LOGIN_OR_PASSWORD
import ru.rpuxa.bomjonlineserver.dataBase

object LoginRequest : Request("login") {
    override fun request(query: Query): String {
        val login = query["login"] ?: return BAD_ARGUMENTS
        val password = query["password"] ?: return BAD_ARGUMENTS

        val user = dataBase.userByLogin(login) ?: return INCORRECT_LOGIN_OR_PASSWORD
        if (user.password != password)
            return INCORRECT_LOGIN_OR_PASSWORD

        return ("token" to user.token).toJSON()
    }
}