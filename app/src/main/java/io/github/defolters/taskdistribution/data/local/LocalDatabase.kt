package io.github.defolters.taskdistribution.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.defolters.taskdistribution.data.local.dao.ItemsDao
import io.github.defolters.taskdistribution.data.local.dao.OrdersDao
import io.github.defolters.taskdistribution.data.local.dao.TasksDao
import io.github.defolters.taskdistribution.data.local.model.ItemDBModel
import io.github.defolters.taskdistribution.data.local.model.OrderDBModel
import io.github.defolters.taskdistribution.data.local.model.TaskDBModel

@Database(entities = [TaskDBModel::class, ItemDBModel::class, OrderDBModel::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao
    abstract fun ordersDao(): OrdersDao
    abstract fun itemsDao(): ItemsDao

    companion object {

        private var INSTANCE: LocalDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): LocalDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java, "Tasks.db"
                    )
                        .build()
                }
                return INSTANCE!!
            }
        }
    }

}