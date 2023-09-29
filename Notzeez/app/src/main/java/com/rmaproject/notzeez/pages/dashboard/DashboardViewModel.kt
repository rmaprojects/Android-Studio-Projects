package com.rmaproject.notzeez.pages.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.rmaproject.notzeez.model.Note
import com.rmaproject.notzeez.model.User
import com.rmaproject.notzeez.repository.MainRepository
import com.rmaproject.notzeez.util.database
import com.rmaproject.notzeez.util.getCurrentUser

class DashboardViewModel : ViewModel(), ValueEventListener {

    private val _notesList = MutableLiveData<DataSnapshot>()
    val notesList: LiveData<DataSnapshot> = _notesList

    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> = _userData

    fun removeNotes(note: Note): Task<Void> {
        return database.reference.child("notes")
            .child(getCurrentUser()!!.uid)
            .child(note.noteId.toString())
            .removeValue()
    }

    init {
        getNotes()
        getUserData()
    }

    private fun getNotes() {
        if (getCurrentUser() == null) {
            return
        }
        database.reference.child("notes")
            .child(getCurrentUser()!!.uid).orderByChild("updatedAt")
            .addValueEventListener(this)
    }

    private fun getUserData() {
        if (getCurrentUser() == null) {
            return
        }
        database.reference.child("users")
            .child(getCurrentUser()!!.uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue<User>()
                    _userData.postValue(user)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("USER_RETRIEVE_ERROR", error.toString())
                }

            })
    }

    override fun onDataChange(snapshot: DataSnapshot) {
        _notesList.postValue(snapshot)
    }

    override fun onCancelled(error: DatabaseError) {
        Log.d("NOTE LIST ERROR", error.message)
    }
}