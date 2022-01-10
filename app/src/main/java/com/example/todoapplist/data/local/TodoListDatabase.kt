package com.example.todoapplist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapplist.data.local.models.Todo


//You can also check out type converters
@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoListDatabase: RoomDatabase(){

    /**
     * This is an abstract method that returns a dao for the Db
     * */
    abstract fun getTodoDao(): TodoDao

    /**
     * A singleton design pattern is used to ensure that the database instance created is one
     * */
    companion object {
        val databaseName = "tododatabase"
        var todoListDatabase: TodoListDatabase? = null

        fun getInstance(context: Context): TodoListDatabase?{
            if (todoListDatabase == null){
                todoListDatabase = Room.databaseBuilder(context,
                    TodoListDatabase::class.java,
                    TodoListDatabase.databaseName)
                    .allowMainThreadQueries()//i will remove this later, database are not supposed to be called on main thread
                    .build()
            }
            return todoListDatabase
        }
    }
}