package com.example.taskmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.taskmanager.dataaccess.AppDatabase
import com.example.taskmanager.dataaccess.Task

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "List tasks"

        val db = AppDatabase.getDatabase(applicationContext)
        val taskList = db.taskDao().getAll()
        val itemsAdapter = ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, taskList)
        var listControl = findViewById<View>(R.id.taskListView) as ListView
        listControl.setAdapter(itemsAdapter);

        listControl.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position) as Task
            viewTask(selectedItem.uid)
        }
    }

    private fun viewTask(taskId: Int) {
        var i = Intent(this, ViewActivity::class.java)
        i.putExtra("taskId", taskId)
        startActivity(i)
    }

    fun createButtonClicked(view:View) {
        startActivity(Intent(this, CreateActivity::class.java))
    }
}