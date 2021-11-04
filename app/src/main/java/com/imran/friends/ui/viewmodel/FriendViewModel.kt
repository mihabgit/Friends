package com.imran.friends.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imran.friends.data.MainRepository
import com.imran.friends.ui.model.Friend
import com.imran.friends.ui.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FriendViewModel(private val mainRepository: MainRepository): ViewModel() {

    private var friendList: MutableLiveData<List<Result>>? = null
    val errorMsg = MutableLiveData<String>()

    fun getFriendList(): LiveData<List<Result>> {
        if (friendList == null) {
            friendList = MutableLiveData<List<Result>>()
            loadFriendData()
        }
        return friendList as MutableLiveData<List<Result>>
    }

    private fun loadFriendData() {
        val response = mainRepository.getFriend()
        response.enqueue(object : Callback<Friend> {
            override fun onResponse(call: Call<Friend>, response: Response<Friend>) {
                val list = response.body()?.results
                friendList?.value = list!!
            }

            override fun onFailure(call: Call<Friend>, t: Throwable) {
                errorMsg.postValue(t.message)
            }
        })
    }

}








