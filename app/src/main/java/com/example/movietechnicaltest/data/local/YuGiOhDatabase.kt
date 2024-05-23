package com.example.movietechnicaltest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CardsEntity::class],
    version = 1
)
abstract class YuGiOhDatabase : RoomDatabase() {
    abstract val dao: CardsDao
}