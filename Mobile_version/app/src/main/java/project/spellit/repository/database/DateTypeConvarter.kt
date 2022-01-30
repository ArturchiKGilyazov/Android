package project.spellit.repository.database

import androidx.room.TypeConverter
import java.sql.Date

class DateTypeConvarter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}