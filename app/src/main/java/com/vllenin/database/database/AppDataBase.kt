package com.vllenin.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Vllenin on 5/15/20.
 */
@Database(entities = [User::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDataBase(context).also { appDataBase ->
                    INSTANCE = appDataBase
                }
            }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "AppDB.db")
                .build()

    }
}