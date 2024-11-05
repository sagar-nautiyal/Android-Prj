package com.businesscalling.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.businesscalling.noteapp.Screens.NoteScreen
import com.businesscalling.noteapp.Screens.NoteViewModel
import com.businesscalling.noteapp.data.NoteSourceData
import com.businesscalling.noteapp.model.Note
import com.businesscalling.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppTheme {
                val noteViewModel : NoteViewModel by viewModels()
                NotesApp(noteViewModel)
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel){
    val notesList = noteViewModel.getAllNotes()
    NoteScreen(notes = notesList,
        onAddNote = {
            noteViewModel.addNote(it)
        } ,
        onRemoveNote = {
            noteViewModel.removeNote(it)
        })

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteAppTheme {
        NoteScreen(notes = NoteSourceData().loadNotes(), onAddNote = {} , onRemoveNote = {})
    }
}