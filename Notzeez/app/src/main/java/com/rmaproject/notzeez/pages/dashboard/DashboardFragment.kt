package com.rmaproject.notzeez.pages.dashboard

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.rmaproject.notzeez.R
import com.rmaproject.notzeez.databinding.FragmentDashboardBinding
import com.rmaproject.notzeez.model.Note
import com.rmaproject.notzeez.pages.addnote.AddNoteFragment
import com.rmaproject.notzeez.util.createAlertDialog
import com.rmaproject.notzeez.util.createSnackBar
import com.rmaproject.notzeez.util.getCurrentUser

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val binding: FragmentDashboardBinding by viewBinding()
    private val viewModel: DashboardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddNotes.setOnClickListener {
            findNavController().navigate(R.id.action_nav_dashboard_to_nav_add_notes)
        }

        binding.cardSearch.setOnClickListener {
            findNavController().navigate(R.id.action_nav_dashboard_to_nav_search)
        }

        binding.btnAccount.setOnClickListener {
            findNavController().navigate(R.id.action_nav_dashboard_to_nav_profile)
        }

        viewModel.notesList.observe(viewLifecycleOwner) { snapshot ->
            val listNote = snapshot.children.map { it.getValue(Note::class.java) ?: Note() }

            binding.txtNoNotes.isVisible = listNote.isEmpty()

            val adapter = NotesAdapter(listNote)
            binding.notesRv.adapter = adapter

            adapter.onNoteClick = { note ->
                val noteId = note.noteId
                val bundle = bundleOf(AddNoteFragment.NOTE_ID_KEY to noteId)
                findNavController().navigate(
                    R.id.action_nav_dashboard_to_nav_add_notes,
                    bundle
                )
            }

            adapter.onNoteLongClick = { note ->
                createAlertDialog(
                    requireContext(),
                    getString(R.string.msg_warn_delete_note),
                    getString(R.string.msg_warn_message_delete_note)
                ).setIcon(R.drawable.ic_baseline_delete_24)
                    .setPositiveButton(getString(R.string.decision_yes)) { _, _ ->
                        removeNote(note)
                    }
                    .setNegativeButton(getString(R.string.decision_no)) { _, _ -> }
                    .create().show()
            }
        }
    }

    private fun removeNote(note: Note) {
        viewModel.removeNotes(note).addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                createSnackBar(
                    binding.root,
                    task.exception!!.localizedMessage!!,
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            createSnackBar(
                binding.root,
                getString(R.string.msg_success_delete_note),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onStart() {
        super.onStart()
        if (getCurrentUser()?.uid == null) {
            findNavController().navigate(R.id.action_nav_dashboard_to_nav_login)
            return
        }
    }

}