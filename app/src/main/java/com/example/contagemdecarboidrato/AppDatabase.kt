package com.example.contagemdecarboidrato


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Alimento::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun alimentoDao(): AlimentoDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "my_db"
                    ).createFromAsset("database/my_db.db").build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }


}

