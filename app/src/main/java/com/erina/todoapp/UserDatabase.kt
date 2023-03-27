package com.erina.todoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserData::class], version = 1)
abstract class UserDatabase : RoomDatabase(){
    abstract fun getDao(): DAO

    companion object{
        var db: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase{

            if (db == null){
                db = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    "to_do"
                ).allowMainThreadQueries().build()
                return db as UserDatabase
            }else{
                return db as UserDatabase
            }


        }
    }

}