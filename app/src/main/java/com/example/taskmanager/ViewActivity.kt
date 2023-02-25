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
        // Database initialization
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "taskManagerDb"
        ).allowMainThreadQueries().build()

        var task = db.taskDao().getById(id)
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
}