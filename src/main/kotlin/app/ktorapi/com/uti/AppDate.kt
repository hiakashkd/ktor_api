package app.ktorapi.com.uti

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


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