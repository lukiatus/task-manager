package com.example.taskmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.taskmanager.dataaccess.AppDatabase
import com.example.taskmanager.dataaccess.Task

class EditActivity : AppCompatActivity() {
    private var id: Int = 0
    private lateinit var task: Task
    private lateinit var titleInput: EditText
    private lateinit var descriptionInput: EditText
    private lateinit var responsibleInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        id = intent.getIntExtra("taskId", 0)
        title = "Edit task #$id"
        titleInput = findViewById<EditText>(R.id.editTitle)
        descriptionInput = findViewById<EditText>(R.id.editDescription)
        responsibleInput = findViewById<EditText>(R.id.editResponsible)

        val db = AppDatabase.getDatabase(applicationContext)
        task = db.taskDao().getById(id)
        findViewById<TextView>(R.id.editTitle).apply {
            text = task.title
        }
        findViewById<TextView>(R.id.editResponsible).apply {
            text = task.responsible
        }
        findViewById<TextView>(R.id.editDescription).apply {
            text = task.description
        }
    }

    fun onSaveClicked(view: View) {
        var db = AppDatabase.getDatabase(applicationContext);
        task.title = titleInput.text.toString()
        task.responsible = responsibleInput.text.toString()
        task.description = descriptionInput.text.toString()
        db.taskDao().update(task)
        viewTask(id)
    }

    private fun viewTask(taskId: Int) {
        var i = Intent(this, ViewActivity::class.java)
        i.putExtra("taskId", taskId)
        startActivity(i)
    }
}