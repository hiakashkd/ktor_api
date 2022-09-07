package app.ktorapi.com.db

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object LogTable : Table<Nothing>(DBConstant.TABLE_LOG) {
    val id = int("id").primaryKey()
    val value = varchar("value")
    val apiName = varchar("api_name")
    val createdAt = varchar("created_at")
    val updatedAt = varchar("updated_at")
}