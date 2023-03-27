package com.erina.todoapp

import androidx.room.*

@Dao
interface DAO {

    @Insert
    fun userInsert(userData: UserData)

    @Update
    fun userUpdate(userData: UserData)

    @Delete
    fun userDelete(userData: UserData)

    @Query("Delete from UserData")
    suspend fun deleteAll()

    @Query("Select * from UserData")
    suspend fun getTasks():List<UserData>




}