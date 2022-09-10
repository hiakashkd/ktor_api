package app.ktorapi.com.uti

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


fun String.toLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.parse(this, formatter)
}

fun Date.toLocalDate(): LocalDateTime = LocalDateTime.ofInstant(
    this.toInstant(),
    ZoneId.systemDefault()
)

fun LocalDateTime.toDate(): Date {
    val mDate = Date.from(this.atZone(ZoneId.systemDefault())?.toInstant())
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
    val requiredDate: String? = formatter.format(mDate)
    println("newDateString : $requiredDate")
    return mDate
}