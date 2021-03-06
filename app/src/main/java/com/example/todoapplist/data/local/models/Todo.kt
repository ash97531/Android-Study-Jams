package com.example.todoapplist.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
class Todo (
    @ColumnInfo(name = "todo_title")
    var title:String = "",
    @ColumnInfo(name = "todo_priority")
    var priority: Int = 0,
    @PrimaryKey(autoGenerate = true) var tId: Int = 0){
        var detail: String = ""
}