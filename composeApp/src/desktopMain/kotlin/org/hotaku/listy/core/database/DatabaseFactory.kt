package org.hotaku.listy.core.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<ListyDataBase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "Listy")
            os.contains("mac") -> File(userHome, "Library/Application Support/Listy")
            else -> File(userHome, ".local/share/Listy")
        }

        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, ListyDataBase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}