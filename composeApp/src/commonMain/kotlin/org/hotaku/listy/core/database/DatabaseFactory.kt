package org.hotaku.listy.core.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<ListyDataBase>
}