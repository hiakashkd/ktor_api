package app.ktorapi.com.uti

import app.ktorapi.com.db.LogTable
import app.ktorapi.com.db.db
import io.ktor.server.application.*
import io.ktor.server.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.ktorm.database.Transaction
import org.ktorm.dsl.insert


suspend inline fun ApplicationCall.withHandler(
    saveLog: Boolean = false,
    crossinline process: suspend (Transaction) -> Unit,
) {
    withContext(Dispatchers.IO) {
        if (saveLog) saveCallLog(this@withHandler)
        db.useTransaction {
            try {
                process(it)
                it.commit()
            } catch (e: Exception) {
                it.rollback()
                throw e
            }
        }
    }
}


suspend fun saveCallLog(call: ApplicationCall) {
    try {
        val readChannel = call.receiveChannel()
        val text = readChannel.readRemaining().readText()
        db.insert(LogTable) {
            set(LogTable.value, text)
        }
    } catch (_: Exception) {
    }
}


suspend fun a (call :ApplicationCall){

    call.withHandler(saveLog = true){

    }
}