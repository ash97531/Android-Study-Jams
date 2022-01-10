package com.example.todoapplist.Todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import com.example.todoapplist.R
import com.example.todoapplist.data.local.TodoListDatabase
import com.example.todoapplist.data.local.models.Todo

class AddTodoActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    private var todoDatabase:TodoListDatabase? = null
    private var priority = 1

    lateinit var radioGroup: RadioGroup
    lateinit var add_todo: Button
    lateinit var title_ed: EditText
    lateinit var detail_ed: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        radioGroup = findViewById(R.id.radioGroup)
        add_todo = findViewById(R.id.add_todo)
        title_ed = findViewById(R.id.title_ed)
        detail_ed = findViewById(R.id.detail_ed)

        todoDatabase = TodoListDatabase.getInstance(this)
        radioGroup.setOnCheckedChangeListener(this)

        val title = intent.getStringExtra("title")
        if (title == null || title == ""){
            add_todo.setOnClickListener{
                val todo = Todo(title_ed.text.toString(), priority)
                todo.detail = detail_ed.text.toString()
                todoDatabase!!.getTodoDao().saveTodo(todo)
                finish()
            }
        }else{
            add_todo.text = getString(R.string.update)
            val tId = intent.getIntExtra("tId", 0)
            title_ed.setText(title)
            add_todo.setOnClickListener {
                val todo = Todo(title_ed.text.toString(), priority, tId)
                todo.detail = detail_ed.text.toString()
                todoDatabase!!.getTodoDao().updateTodo(todo)
                finish()
            }
        }

    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

        if (checkedId == R.id.medium){
            priority = 2
        }else if (checkedId == R.id.high) {
            priority = 3
        }else if(checkedId == R.id.low) {
            priority = 1
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == android.R.id.home){
            startActivity(Intent(this, TodoActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}