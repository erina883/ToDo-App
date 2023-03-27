package com.erina.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erina.todoapp.databinding.ActivityCreateBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var userDao = UserDatabase.getInstance(this).getDao()

        binding.saveButton.setOnClickListener {
            if (binding.createTitle.text.toString().trim{it<= ' '}.isNotEmpty()
                && binding.createPriority.text.toString().trim{ it <= ' '}.isNotEmpty()
                && binding.createDeatls.text.toString().trim{it <= ' '}.isNotEmpty()){

                var title = binding.createTitle.text.toString()
                var priority = binding.createPriority.text.toString()
                var deatls = binding.createDeatls.text.toString()
                DataObject.setData(title, priority, deatls)
                GlobalScope.launch {
                    userDao.userInsert(UserData(0, title, priority, deatls))
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }

        }
    }
}