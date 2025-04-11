package org.hotaku.listy.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<ListyDataBase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(ListyDataBase.DB_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}