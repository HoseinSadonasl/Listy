package org.hotaku.listy.core.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor: RoomDatabaseConstructor<ListyDataBase> {
    override fun initialize(): ListyDataBase
}