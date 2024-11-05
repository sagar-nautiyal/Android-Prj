package com.businesscalling.noteapp.Components

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.HistoricalChange
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier,
    text: String,
    label: String,
    maxLine: Int,
    onTextChange:(String)-> Unit,
    onImeAction: ()-> Unit ={}
){
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(value = text,
        label = { Text(text = label)},
        onValueChange = onTextChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,

        ),
        maxLines = maxLine,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )

}



@Composable
fun NoteComponentButton(
    modifier: Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: ()-> Unit
){
    Button(onClick = onClick, shape = CircleShape,
        enabled = enabled, modifier = modifier) {
        Text(text)
    }

}