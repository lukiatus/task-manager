package com.example.taskmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.taskmanager.dataaccess.AppDatabase
import com.example.taskmanager.dataaccess.Task

class ViewActivity : AppCompatActivity() {
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        id = intent.getIntExtra("taskId", 0)
        title = "View task #$id"

        var task = AppDatabase.getDatabase(applicationContext).taskDao().getById(id)
        findViewById<TextView>(R.id.viewTitle).apply {
            text = task.title
        }
        findViewById<TextView>(R.id.viewResponsible).apply {
            text = task.responsible
        }
        findViewById<TextView>(R.id.viewDescription).apply {
            text = task.description
        }
    }

    fun editTask(taskId: Int) {
        var i = Intent(this, EditActivity::class.java)
        i.putExtra("taskId", taskId)
        startActivity(i)
    }

    fun editButtonCliked(view: View) {
        editTask(id)
    }

    fun deleteButtonClicked(view: View) {
        var task = AppDatabase.getDatabase(applicationContext).taskDao().getById(id)
        AppDatabase.getDatabase(applicationContext).taskDao().delete(task)
        startActivity(Intent(this, MainActivity::class.java))
    }
}