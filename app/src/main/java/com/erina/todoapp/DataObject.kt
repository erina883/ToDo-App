package com.erina.todoapp

object DataObject {

    var listdata = mutableListOf<UserData>()

    fun setData(title: String, priority: String, deatls: String) {
        listdata.add(UserData(0, title, priority, deatls))
    }

    fun getAllData(): List<UserData> {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int): UserData {
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    fun updateData(pos:Int,title:String,priority:String, deatls:String)
    {
        listdata[pos].title=title
        listdata[pos].priority=priority
        listdata[pos].deatls=deatls
    }


}