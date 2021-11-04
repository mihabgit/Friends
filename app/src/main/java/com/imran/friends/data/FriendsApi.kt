package com.imran.friends.data

import com.imran.friends.ui.model.Friend
import retrofit2.Call
import retrofit2.http.GET

interface FriendsApi {

    @GET("api/?results=10")
    fun getFriend(): Call<Friend>

}


// https://randomuser.me/api/?results=10