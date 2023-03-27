package com.erina.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erina.todoapp.databinding.ActivityUpdateBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var userDao = UserDatabase.getInstance(this).getDao()

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1){
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            val deatls = DataObject.getData(pos).deatls
            binding.createTitle.setText(title)
            binding.createPriority.setText(priority)
            binding.createDeatls.setText(deatls)

            binding.deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    userDao.userDelete(
                        UserData(pos + 1, binding.createTitle.text.toString(),
                            binding.createPriority.text.toString(), binding.createDeatls.text.toString()
                        )
                    )
                }
                myIntent()

            }

            binding.updateButton.setOnClickListener {
                DataObject.updateData(
                    pos,
                    binding.createTitle.text.toString(),
                    binding.createPriority.text.toString(),
                    binding.createDeatls.text.toString()
                )
                GlobalScope.launch {
                    userDao.userUpdate(
                        UserData(
                            pos + 1, binding.createTitle.text.toString(),
                            binding.createPriority.text.toString(),
                            binding.createDeatls.text.toString()
                        )
                    )
                }
                myIntent()
            }

        }
    }

    fun myIntent(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}