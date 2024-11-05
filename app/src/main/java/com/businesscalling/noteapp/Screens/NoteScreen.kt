package com.businesscalling.noteapp.Screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.businesscalling.noteapp.Components.NoteComponentButton
import com.businesscalling.noteapp.Components.NoteInputText
import com.businesscalling.noteapp.R
import com.businesscalling.noteapp.data.NoteSourceData
import com.businesscalling.noteapp.model.Note


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
){
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current;
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }, actions = {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = "Edit Icon")
            }, colors  =
                TopAppBarDefaults.topAppBarColors(containerColor = Color(color = 0xFFDADFE3)))

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(modifier = Modifier,
                text = title,
                label = "Title",
                maxLine = 2,
                onTextChange = {
                    if(it.all { char ->
                        char.isLetter() || char.isWhitespace()
                        }) title = it
                }
                )

            NoteInputText(modifier = Modifier,
                text = description,
                label = "Add a note",
                maxLine = 2,
                onTextChange = {
                    if(it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                }
            )


            NoteComponentButton(modifier = Modifier,
                text = "Save", onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()){
                        //add/save note

                        onAddNote(Note(title = title, description = description))

                        title = ""
                        description = ""

                        Toast.makeText(context, "Note Added",
                            Toast.LENGTH_SHORT).show()
                    }

                } )
        }

        Divider(modifier = Modifier.padding(8.dp))

        LazyColumn {
            items(notes){note->
                NoteRow(note = note , onClickNote = {
                        onRemoveNote(note)
                })
            }
        }


    }


}


@Composable
fun NoteRow(modifier: Modifier = Modifier,
            note: Note,
            onClickNote: (Note) -> Unit){

    Surface(modifier = Modifier.padding(4.dp)
        .clip(shape = RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
        .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        shadowElevation = 4.dp
    ) {
        Column(modifier = modifier.clickable {
            onClickNote(note)
        }.padding(horizontal = 14.dp, vertical = 14.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = note.title, style = MaterialTheme.typography.titleMedium)
            Text(text = note.description, style = MaterialTheme.typography.titleSmall)
        }

    }

}



@Preview
@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = NoteSourceData().loadNotes(), onAddNote = {} , onRemoveNote = {})
}