package ru.rpuxa.bomjonlineserver.db

import ru.rpuxa.bomjonlineserver.UserData

interface UserDataBase {

    fun updateUser(user: UserData)

    fun nextRandomId(): Int

    fun userByToken(token: String): UserData?

    fun userByLogin(login: String): UserData?

    fun userByMail(mail: String): UserData?
}

inline fun UserDataBase.updateUser(user: UserData, block: (UserData) -> Unit) {
    block(user)
    updateUser(user)
}