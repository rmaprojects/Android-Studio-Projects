package com.rmaproject.notzeez.pages.dashboard

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaproject.notzeez.R
import com.rmaproject.notzeez.databinding.ItemNotesBinding
import com.rmaproject.notzeez.model.Note

class NotesAdapter(private val notesList: List<Note>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    var onNoteClick:((Note) -> Unit)?= null
    var onNoteLongClick:((Note) -> Unit)?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder =
        NotesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false))

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notesList[position]
        holder.binding.root.setOnClickListener {
            onNoteClick?.invoke(note)
        }
        holder.binding.root.setOnLongClickListener {
            onNoteLongClick?.invoke(note)
            true
        }
        holder.bindView(note)
    }

    override fun getItemCount() = notesList.size

    class NotesViewHolder(view:View) : RecyclerView.ViewHolder(view) {

        val binding: ItemNotesBinding by viewBinding()

        fun bindView(note: Note) {
            binding.txtContentText.text = note.content
            binding.txtTitleText.text = if (note.title.isNullOrEmpty()) itemView.context.getString(R.string.msg_no_title) else note.title
        }
    }
}