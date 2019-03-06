typealias HashString = String

class UserData(
    val login: String,
    var password: HashString,
    var id: Int,
    var apiKey: String
) {



    companion object {


        fun createNewUser(login: String, password: HashString): UserData =
                UserData(login, password, sequreRandom.nextInt(), )
    }
}