package me.stefanallen.chatapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter for binding user data to views in a RecyclerView
class UserAdapter(val context: Context, val userList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // Create a new ViewHolder layout for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        return UserViewHolder(view)
    }

    // Bind data to the views in the RecyclerView
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val currentUser = userList[position]

        // Set the name of the user to the TextView
        holder.textName.text = currentUser.name

        // Set OnClickListener to launch a ChatActivity when clicked
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)

            // Passes name and ID in the intent
            intent.putExtra("name", currentUser.name)
            intent.putExtra("uid", currentUser.uid)

            // Start the ChatActivity
            context.startActivity(intent)
        }
    }

    // total number of items in the user list
    override fun getItemCount(): Int {
        return userList.size
    }

    // views inside each item view of the RecyclerView
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.txt_name)
    }
}