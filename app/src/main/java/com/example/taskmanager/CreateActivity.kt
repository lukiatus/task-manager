package com.example.taskmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.taskmanager.dataaccess.AppDatabase
import com.example.taskmanager.dataaccess.Task

class CreateActivity : AppCompatActivity() {
    private lateinit var titleInput: EditText
    private lateinit var descriptionInput: EditText
    private lateinit var responsibleInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        titleInput = findViewById<EditText>(R.id.newTitle)
        descriptionInput = findViewById<EditText>(R.id.newDescription)
        responsibleInput = findViewById<EditText>(R.id.newResponsible)
    }

    fun onSaveClicked(view: View) {
        var db = AppDatabase.getDatabase(applicationContext);
        var newTask = Task(
            0,
            titleInput.text.toString(),
            descriptionInput.text.toString(),
            responsibleInput.text.toString()
        )
        db.taskDao().insert(newTask)
        startActivity(Intent(this, MainActivity::class.java))
    }
}