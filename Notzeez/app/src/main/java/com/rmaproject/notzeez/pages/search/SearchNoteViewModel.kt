package com.rmaproject.notzeez.pages.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.rmaproject.notzeez.util.database
import com.rmaproject.notzeez.util.getCurrentUser

class SearchNoteViewModel : ViewModel(), ValueEventListener {
    private val _searchNoteResult = MutableLiveData<DataSnapshot>()
    val searchNoteResult: LiveData<DataSnapshot> = _searchNoteResult

    fun searchNotes(query: String) {
        val queryUpperCase = query.uppercase()
        val queryLowerCase = query.lowercase()
        database.reference.child("notes")
            .child(getCurrentUser()!!.uid)
            .orderByChild("title").startAt(queryUpperCase).endAt("$queryLowerCase\uf8ff")
            .addValueEventListener(this)
    }

    override fun onDataChange(snapshot: DataSnapshot) {
        _searchNoteResult.postValue(snapshot)
    }

    override fun onCancelled(error: DatabaseError) {
        Log.d("SEARCH_ERROR", error.toString())
    }
}