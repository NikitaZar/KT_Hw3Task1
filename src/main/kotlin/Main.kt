fun main() {
    println("Введите имя пользователя")
    val user = readLine()?.let { User(it) }
    println("Сколько секунд прошло с выхода из сети?")
    if (user != null) {
        user.secAgo = readLine()?.toLong()!!
        println(user.printTimeAgo())
    }
}