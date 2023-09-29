package com.rmaproject.notzeez.pages.favourites

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaproject.notzeez.R
import com.rmaproject.notzeez.databinding.FragmentFavoriteBinding
import com.rmaproject.notzeez.factory.ViewModelFactory
import com.rmaproject.notzeez.pages.addnote.AddNoteFragment
import com.rmaproject.notzeez.pages.dashboard.NotesAdapter
import com.rmaproject.notzeez.util.createAlertDialog

class FavouriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding: FragmentFavoriteBinding by viewBinding()
    private val viewModel: FavouriteViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllNotes().asLiveData().observe(viewLifecycleOwner) { listNote ->
            binding.txtNoNotes.isVisible = listNote.isEmpty()

            val adapter = NotesAdapter(listNote)
            binding.notesRv.adapter = adapter

            adapter.onNoteClick = { note ->
                val bundle = bundleOf(
                    AddNoteFragment.NOTE_ROOM_ID_KEY to note.id.toString()
                )
                findNavController().navigate(R.id.action_nav_favorite_to_nav_add_notes, bundle)
            }

            adapter.onNoteLongClick = { note ->
                createAlertDialog(
                    requireContext(),
                    getString(R.string.msg_warn_delete_note),
                    getString(R.string.msg_warn_message_delete_note)
                ).setIcon(R.drawable.ic_baseline_delete_24)
                    .setPositiveButton(getString(R.string.decision_yes)) { _, _ ->
                        viewModel.deleteNote(note)
                    }
                    .setNegativeButton(getString(R.string.decision_no)) { _, _ -> }
                    .create().show()
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}