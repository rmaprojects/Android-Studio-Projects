package com.rmaproject.notzeez.repository

import android.net.Uri
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.rmaproject.notzeez.data.NotesDao
import com.rmaproject.notzeez.model.Note
import com.rmaproject.notzeez.model.User
import com.rmaproject.notzeez.util.Status
import com.rmaproject.notzeez.util.safeCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class MainRepository(
    private val auth: FirebaseAuth,
    database: FirebaseDatabase,
    storage: FirebaseStorage,
    private val notesDao: NotesDao
) {
    private val notesReference = database.getReference("notes")
    private val userReference = database.getReference("users")
    private val userId = auth.currentUser?.uid
    private val storageReference = storage.reference.child("profile_photos")

    suspend fun signUpUser(
        username: String,
        email: String,
        password: String
    ): Status<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val userId = result.user?.uid!!
                val newUser = User(username)
                userReference.child(userId).setValue(newUser).await()
                Status.Success(result)
            }
        }
    }

    suspend fun loginUser(
        email: String,
        password: String
    ): Status<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                Status.Success(result)
            }
        }
    }

    suspend fun addNotes(
        title: String,
        content: String,
        noteId: String? = "",
    ) {
        return withContext(Dispatchers.IO) {
            safeCall {
                if (userId.isNullOrEmpty()) {
                    return@withContext
                }
                val id =
                    if (noteId.isNullOrEmpty()) notesReference.push().key.toString() else noteId
                val note = Note(
                    title = title,
                    content = content,
                    noteId = id
                )
                val result = notesReference.child(userId)
                    .child(id)
                    .setValue(note).await()
                Status.Success(result)
            }
        }
    }

    suspend fun uploadProfilePicture(
        imageUri: Uri
    ): Status<UploadTask.TaskSnapshot> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val status = storageReference.child(userId.toString()).putFile(imageUri).await()
                Status.Success(status)
            }
        }
    }

    suspend fun changeProfilePicture(
        imageUrl: String
    ): Status<Void> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val status = userReference
                    .child(userId.toString())
                    .child("profileUrl")
                    .setValue(imageUrl).await()
                Status.Success(status)
            }
        }
    }

    suspend fun insertNote(note: Note) {
        notesDao.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note)
    }

    suspend fun editNote(note: Note) {
        notesDao.updateNote(note)
    }

    suspend fun deleteAllNotes() {
        notesDao.deleteAllNotes()
    }

    fun getAllNotesInDatabase(): Flow<List<Note>> {
        return notesDao.getNotes()
    }

    fun getSingleNoteInDatabase(noteId: Int) : Flow<Note> {
        return notesDao.getSingleNote(noteId)
    }
}