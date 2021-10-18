class User(private val userName: String) {
    companion object {
        const val RECENTLY_TEXT = "только что"
        const val TODAY_TEXT = "сегодня"
        const val YESTERDAY_TEXT = "вчера"
        const val LONG_AGO_TEXT = "давно"

        const val MINUTE_1_TEXT = "минуту"
        const val MINUTE_2_TEXT = "минуты"
        const val MINUTES_TEXT = "минут"

        const val HOUR_1_TEXT = "час"
        const val HOUR_2_TEXT = "часа"
        const val HOURS_TEXT = "часов"

        const val SEC_PER_MINUTE = 60L
        const val SEC_PER_HOUR = SEC_PER_MINUTE * 60
        const val SEC_PER_DAY = SEC_PER_HOUR * 24
    }

    var secAgo: Long = Long.MAX_VALUE

    fun printTimeAgo() {
        println("$userName был(а) в сети ${agoToText(secAgo)}")
    }

    private fun agoToText(secAgo: Long): String {
        val minutes = secAgo / SEC_PER_MINUTE
        val hours = secAgo / SEC_PER_HOUR
        return when (secAgo) {
            in 0..SEC_PER_MINUTE -> RECENTLY_TEXT
            in SEC_PER_MINUTE + 1..SEC_PER_HOUR ->
                "$minutes ${numDeclension(minutes, MINUTE_1_TEXT, MINUTE_2_TEXT, MINUTES_TEXT)} назад"
            in SEC_PER_HOUR + 1..SEC_PER_DAY ->
                "$hours ${numDeclension(hours, HOUR_1_TEXT, HOUR_2_TEXT, HOURS_TEXT)} назад"
            in SEC_PER_DAY + 1..2 * SEC_PER_DAY -> TODAY_TEXT
            in 2 * SEC_PER_DAY + 1..3 * SEC_PER_DAY -> YESTERDAY_TEXT
            else -> LONG_AGO_TEXT
        }
    }

    private fun numDeclension(
        num: Long,
        text1: String,
        text2: String,
        textSeveral: String
    ): String {
        return when {
            num.mod(100L) == 11L -> textSeveral
            num.mod(10L) == 1L -> text1
            num.mod(10L) == 2L -> text2
            else -> textSeveral
        }
    }

}