package com.rmaproject.notzeez.pages.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaproject.notzeez.R
import com.rmaproject.notzeez.databinding.FragmentSearchNotesBinding
import com.rmaproject.notzeez.model.Note
import com.rmaproject.notzeez.pages.dashboard.NotesAdapter
import com.rmaproject.notzeez.util.Keyboard
import com.rmaproject.notzeez.util.showOrHideKeyBoard

class SearchNoteFragment : Fragment(R.layout.fragment_search_notes) {

    private val binding: FragmentSearchNotesBinding by viewBinding()
    private val viewModel: SearchNoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edtSearchNote.requestFocus()
        showOrHideKeyBoard(Keyboard.SHOW, binding.edtSearchNote)
        binding.btnBack.setOnClickListener {
            showOrHideKeyBoard(Keyboard.HIDE, binding.edtSearchNote)
            findNavController().navigateUp()
        }
        binding.edtSearchNote.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == IME_ACTION_SEARCH) {
                val searchQuery = binding.edtSearchNote.text.toString()
                viewModel.searchNotes(searchQuery)
            }
            true
        }

        viewModel.searchNoteResult.observe(viewLifecycleOwner) { data ->
            val noteSearchList = data.children.map {
                it.getValue(Note::class.java) ?: Note()
            }
            binding.txtNoNotes.isVisible = noteSearchList.isEmpty()
            val adapter = NotesAdapter(noteSearchList)
            binding.notesRv.adapter = adapter
        }
    }
}