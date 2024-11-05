package com.businesscalling.noteapp.Screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.businesscalling.noteapp.data.NoteSourceData
import com.businesscalling.noteapp.model.Note

class NoteViewModel : ViewModel(){
    private val noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NoteSourceData().loadNotes())
    }

    fun addNote(note: Note){
        noteList.add(note)
    }

    fun removeNote(note: Note){
        noteList.remove(note)
    }


    fun getAllNotes(): List<Note>{
        return noteList
    }
}