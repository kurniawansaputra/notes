package com.example.notes.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.data.model.NotesData
import com.example.notes.databinding.RowLayoutBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var dataList = emptyList<NotesData>()

    class ViewHolder(private val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(notesData: NotesData) {
            binding.notesData = notesData
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    fun setData(notesData: List<NotesData>) {
        this.dataList = notesData
        notifyDataSetChanged()
    }


}