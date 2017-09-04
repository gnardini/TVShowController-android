package com.gnardini.tvshowcontroller.directories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gnardini.tvshowcontroller.R

class DirectoryAdapter(private val listener: DirectoryClickListener)
    : RecyclerView.Adapter<DirectoryAdapter.DirectoryViewHolder>() {

    private var directories = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectoryViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.directory_view, parent, false)
        return DirectoryViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: DirectoryViewHolder, position: Int) {
        holder.text = directories[position]
    }

    override fun getItemCount(): Int = directories.size

    fun setDirectories(directories: List<String>) {
        this.directories = directories
        notifyDataSetChanged()
    }

    class DirectoryViewHolder(
            itemView: View,
            private val listener: DirectoryClickListener)
        : RecyclerView.ViewHolder(itemView) {

        var directory: TextView = itemView.findViewById(R.id.directory)
        var text: String = ""
            set(value) {
                field = value
                directory.text = value
            }

        init {
            itemView.setOnClickListener { listener.invoke(text) }
        }
    }
}
