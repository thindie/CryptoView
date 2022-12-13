package com.example.thindie.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoinDbModel::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun coinListDao(): CoinDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        private val lock = Any()
        private const val DB_NAME = "coins.db"
        fun getInstance(application: Application): AppDataBase {
            INSTANCE?.let { return it }
            synchronized(lock) {
                INSTANCE?.let { return it }
            }
            val db = Room.databaseBuilder(
                application,
               AppDataBase::class.java,
                DB_NAME
            )

                .build()
            INSTANCE = db
            return db
        }
    }
}