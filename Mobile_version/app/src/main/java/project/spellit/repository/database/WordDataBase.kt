package project.spellit.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [WordEntity::class], version = 1)
@TypeConverters(DateTypeConvarter::class)
abstract class WordDataBase: RoomDatabase() {

    abstract fun wordDAO(): WordDAO

    companion object {
        var INSTANCE: WordDataBase? = null

        fun getAppDataBase(context: Context): WordDataBase? {
            if (INSTANCE == null) {
                synchronized(WordDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WordDataBase::class.java,
                        "myDB"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

}