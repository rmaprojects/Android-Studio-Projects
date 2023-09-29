package com.rmaproject.notzeez.pages.addnote

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.rmaproject.notzeez.R
import com.rmaproject.notzeez.databinding.FragmentAddNoteBinding
import com.rmaproject.notzeez.factory.ViewModelFactory
import com.rmaproject.notzeez.model.Note
import com.rmaproject.notzeez.util.*
import com.rmaproject.notzeez.util.Status.*

class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider, ValueEventListener {

    private val binding: FragmentAddNoteBinding by viewBinding()
    private val viewModel: AddNoteViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    private val noteId by lazy {
        arguments?.getString(NOTE_ID_KEY)
    }
    private val noteRoomId by lazy {
        arguments?.getString(NOTE_ROOM_ID_KEY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        if (!noteId.isNullOrEmpty()) {
            database.reference.child("notes")
                .child(getCurrentUser()!!.uid)
                .child(noteId!!)
                .addListenerForSingleValueEvent(this)
        }

        viewModel.addNoteStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Loading -> {
                    createSnackBar(
                        binding.root,
                        getString(R.string.msg_status_save_note),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is Error -> {
                    createSnackBar(
                        binding.root,
                        getString(R.string.msg_err_save_note),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is Success -> {
                    createSnackBar(
                        requireActivity().findViewById(R.id.root),
                        getString(R.string.msg_success_save_note),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    showOrHideKeyBoard(Keyboard.HIDE, binding.edtContent)
                    findNavController().navigateUp()
                }
            }
        }
        if (!noteRoomId.isNullOrEmpty()) {
            viewModel.getSingleNoteFromDatabase(noteRoomId.toString().toInt())
                .asLiveData().observe(viewLifecycleOwner) { note ->
                    binding.edtTitle.setText(note.title)
                    binding.edtContent.setText(note.title)
                }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.save_note_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        val title = binding.edtTitle.text.toString()
        val content = binding.edtContent.text.toString()
        return when (menuItem.itemId) {
            R.id.item_save -> {
                if (noteId.isNullOrEmpty()) {
                    validateContentAndTitle(title, content)
                    viewModel.inputNotes(title, content)
                } else {
                    viewModel.editNotes(noteId!!, title, content)
                    showOrHideKeyBoard(Keyboard.HIDE, binding.edtContent)
                }
                true
            }
            R.id.item_save_offline -> {
                val noteRoomId = arguments?.getString(NOTE_ROOM_ID_KEY)
                Log.d("ID", noteRoomId.toString())
                if (noteRoomId.isNullOrEmpty()) {
                    viewModel.insertNoteToDatabase(Note(title, content, id = null))
                    createSnackBar(
                        binding.root,
                        getString(R.string.msg_success_save_note),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    findNavController().navigateUp()

                } else {
                    viewModel.editNoteFromDatabase(Note(title, content, id = noteRoomId.toInt()))
                    createSnackBar(
                        binding.root,
                        getString(R.string.msg_success_save_note),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    findNavController().navigateUp()
                }
                true
            }
            else -> false
        }
    }

    private fun validateContentAndTitle(title: String, content: String): Boolean {
        return !(title.isEmpty() && content.isEmpty())
    }

    override fun onDataChange(snapshot: DataSnapshot) {
        val note = snapshot.getValue(Note::class.java)
        binding.edtTitle.setText(note?.title)
        binding.edtContent.setText(note?.title)
    }

    override fun onCancelled(error: DatabaseError) {
        Log.d("ERROR", error.toString())
    }

    companion object {
        const val NOTE_ID_KEY = "NOTE_ID_VALUE"
        const val NOTE_ROOM_ID_KEY = "NOTE_ID_VALUE_ROOM"
    }

}