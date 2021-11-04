package com.imran.friends.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imran.friends.R
import com.imran.friends.ui.model.Result
import com.squareup.picasso.Picasso

class FriendListAdapter(private val context: Context, private var friendList: List<Result>) :
    RecyclerView.Adapter<FriendListAdapter.FriendViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.friend_item, parent, false)
        return FriendViewHolder(view)
    }

    override fun getItemCount(): Int {
        return friendList.size
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {

        val friend = friendList[position]
        holder.tvName.text = friend.name.first+" "+friend.name.last
        holder.tvCountry.text = friend.location.country

        Picasso.get().load(friend.picture.medium).into(holder.ivProfilePicture)


        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClickListener(friend)
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(result: Result)
    }

    class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProfilePicture: ImageView = itemView.findViewById(R.id.ivProfilePicture)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvCountry: TextView = itemView.findViewById(R.id.tvCountry)
    }
}