package com.assignment.passwordmanager.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.assignment.passwordmanager.R
import com.assignment.passwordmanager.presentation.utils.Utility

@Composable
fun DataTextField(
    modifier: Modifier=Modifier,
    value: String,
    onChange: (String)->(Unit),
    label: String
){
    TextField(
        value = value,
        onValueChange = {
            onChange(it)
        },
        placeholder = {
            Text(
                text = label,
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontWeight = FontWeight(500),
                fontSize = Utility().pxToSp(13.0, LocalContext.current),
                lineHeight = Utility().pxToSp(20.0, LocalContext.current),
                color = Color(0xFFCCCCCC)
            )
        },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
    )
}