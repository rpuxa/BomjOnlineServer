package ru.rpuxa.bomjonlineserver.requests

import ru.rpuxa.bomjonlineserver.HTTPServer
import ru.rpuxa.bomjonlineserver.Request
import ru.rpuxa.bomjonlineserver.RequestCodes.BAD_ARGUMENTS
import ru.rpuxa.bomjonlineserver.RequestCodes.UNKNOWN_TOKEN
import ru.rpuxa.bomjonlineserver.UserData
import ru.rpuxa.bomjonlineserver.dataBase

const val TOKEN = "token"

abstract class TokenRequest(name: String) : Request(name) {

    override fun request(query: Query): String {
        val token = query.token ?: return BAD_ARGUMENTS
        val user = dataBase.userByToken(token) ?: return UNKNOWN_TOKEN
        return request(user, query)
    }

    protected abstract fun request(user: UserData, query: Query): String

    private val Query.token get() = answer[TOKEN]
}