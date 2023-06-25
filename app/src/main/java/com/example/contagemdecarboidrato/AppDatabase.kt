package com.example.contagemdecarboidrato


import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Alimento::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun alimentoDao(): AlimentoDao

    //resolvi não usar este trecho de código , pois atrapalharia a finalidade deste app
    /*
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "carbo_por_grama_database"
                    ).build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }

     */

}

