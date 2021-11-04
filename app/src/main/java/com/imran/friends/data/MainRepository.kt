package com.imran.friends.data

class MainRepository {

    fun getFriend() = RetrofitInstance.api.getFriend()

}