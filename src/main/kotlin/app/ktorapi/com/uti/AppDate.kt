package app.ktorapi.com.uti

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date


fun Date.toLocalDate(): LocalDateTime = LocalDateTime.ofInstant(
    this.toInstant(),
    ZoneId.systemDefault()
)

fun LocalDateTime.toDate(): Date = Date.from(this.atZone(ZoneId.systemDefault())?.toInstant())