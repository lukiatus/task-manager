package com.example.taskmanager.dataaccess

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    val db = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java, "taskManagerDb"
                    ).allowMainThreadQueries().build()

                    if (db.taskDao().getAll().isEmpty()) {
                        db.taskDao().insert(
                            Task(
                                1,
                                "First Task",
                                "This should be a very long description belonging to the first demo task...",
                                "Peter"
                            )
                        )
                        db.taskDao().insert(
                            Task(
                                2,
                                "Second Task",
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do " +
                                        "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad" +
                                        "minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
                                        "ex ea commodo consequat. Duis aute irure dolor in reprehenderit in" +
                                        "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Exce+pteur" +
                                        "sint occaecat cupidatat non pr+oident, sunt in culpa qui officia deserunt" +
                                        "mollit anim id est laborum",
                                "Xenia"
                            )
                        )
                    }

                    INSTANCE = db
                }
            }
            return INSTANCE!!
        }
    }
}