package com.assignment.passwordmanager.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignment.passwordmanager.R
import com.assignment.passwordmanager.presentation.home.HomeEvent
import com.assignment.passwordmanager.presentation.home.view_models.HomeViewModel
import com.assignment.passwordmanager.presentation.utils.Utility

@Composable
fun NewAccountForm(modifier: Modifier = Modifier, viewModel: HomeViewModel){
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        DataTextField(
            value = viewModel.state.value.accountName,
            onChange = {
                viewModel.onEvent(HomeEvent.AccountNameChanged(it))
            },
            label = "Account Name"
        )
        Spacer(modifier = Modifier.height(20.dp))
        DataTextField(
            value = viewModel.state.value.username,
            onChange = {
                viewModel.onEvent(HomeEvent.UsernameChanged(it))
            },
            label = "Username/Email"
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = viewModel.state.value.password,
            onValueChange = {
                viewModel.onEvent(HomeEvent.PasswordChanged(it))
            },
            placeholder = {
                Text(
                    text = "Password",
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
            suffix = {
                Icon(
                    imageVector = if(viewModel.state.value.isAddViewPasswordVisible)
                        Icons.Rounded.VisibilityOff
                    else
                        Icons.Rounded.Visibility,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        viewModel.onEvent(HomeEvent.ToggleAddViewPasswordVisibility)
                    },
                    tint = Color(0xFFCCCCCC)
                )
            },
            visualTransformation = if(viewModel.state.value.isAddViewPasswordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.onEvent(HomeEvent.AddEditData)
            },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(Color(0xFF2C2C2C)),
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Add New Account",
                color = Color.White,
                fontWeight = FontWeight(700),
                fontSize = 15.sp
            )
        }
    }

}