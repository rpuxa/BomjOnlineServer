package ru.rpuxa.bomjonlineserver.db

import ru.rpuxa.bomjonlineserver.UserData
import ru.rpuxa.bomjonlineserver.random

class ListUserDataBase : UserDataBase {
    private val list = ArrayList<UserData>()

    override fun updateUser(user: UserData) {
        if (!list.any { it.id == user.id })
            list.add(user)
    }

    override fun nextRandomId(): Int {
        return random.nextInt()
    }

    override fun userByLogin(login: String) = list.find { it.login == login }

    override fun userByMail(mail: String) = list.find { it.email == mail }

    override fun userByToken(token: String) = list.find { it.token == token }
}