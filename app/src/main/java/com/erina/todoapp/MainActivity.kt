package com.erina.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.erina.todoapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var userDao = UserDatabase.getInstance(this).getDao()

        binding.add.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        binding.deleteAll.setOnClickListener {
            DataObject.deleteAll()
            GlobalScope.launch {
                userDao.deleteAll()
            }
            setRecycler()
        }
        setRecycler()

    }

    fun setRecycler(){
        binding.recyclerview.adapter = Adapter(DataObject.getAllData())
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
    }
}