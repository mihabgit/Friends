package com.imran.friends.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.imran.friends.ui.FriendListAdapter
import com.imran.friends.R
import com.imran.friends.data.MainRepository
import com.imran.friends.ui.viewmodel.FriendViewModel
import com.imran.friends.ui.model.Result
import com.imran.friends.ui.viewmodel.FriendViewModelProviderFactory
import com.imran.friends.util.Constants
import com.imran.friends.util.Utility.autoFitColumns


class MainActivity : AppCompatActivity() {

    private var adapter: FriendListAdapter? = null
    private lateinit var viewModel: FriendViewModel
    private lateinit var rvFriend: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFriend = findViewById(R.id.rvFriend)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val viewModelProvider = FriendViewModelProviderFactory(MainRepository())
        viewModel = ViewModelProvider(this, viewModelProvider).get(FriendViewModel::class.java)

        viewModel.getFriendList().observe(this, { friendList ->
            loadList(friendList)
            progressBar.visibility = View.GONE
        })
        viewModel.errorMsg.observe(this, Observer {
            Toast.makeText(this, ""+it, Toast.LENGTH_SHORT).show()
        })

    }

    private fun loadList(list: List<Result>) {
        adapter = FriendListAdapter(this, list)
        rvFriend.autoFitColumns(180)
        rvFriend.adapter = adapter

        adapter?.setOnItemClickListener(object : FriendListAdapter.OnItemClickListener{
            override fun onItemClickListener(result: Result) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(Constants.FRIEND_DATA, result)
                startActivity(intent)
            }
        })
    }

}