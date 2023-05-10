package com.example.contagemdecarboidrato

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Alimento::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun alimentoDao(): AlimentoDao
}