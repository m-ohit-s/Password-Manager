package com.assignment.passwordmanager.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignment.passwordmanager.R
import com.assignment.passwordmanager.presentation.home.HomeEvent
import com.assignment.passwordmanager.presentation.home.view_models.HomeViewModel

@Composable
fun EditAccountForm(modifier: Modifier = Modifier, viewModel: HomeViewModel){
    Column(modifier = modifier){
        Text(text = "Account Details",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight(600),
            fontSize = 20.sp,
            lineHeight = 5.sp,
            color = Color(0xFF3F7DE3),
            modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Account Type", modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontWeight = FontWeight(500),
            fontSize = 15.sp,
            color = Color.DarkGray
        )
        Text(text = viewModel.state.value.accountName, modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
            lineHeight = 20.sp,
            color = Color(0xFF333333)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Username/Email", modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontWeight = FontWeight(500),
            fontSize = 15.sp,
            color = Color.DarkGray
        )
        Text(text = viewModel.state.value.username, modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
            lineHeight = 20.sp,
            color = Color(0xFF333333)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Password",modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontWeight = FontWeight(500),
            fontSize = 15.sp,
            color = Color.DarkGray
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if(viewModel.state.value.isEditViewPasswordVisible) viewModel.state.value.password else "*******", modifier = Modifier
                .padding(10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                lineHeight = 20.sp,
                color = Color(0xFF333333))
            
            Icon(
                imageVector = if(viewModel.state.value.isEditViewPasswordVisible) Icons.Rounded.VisibilityOff else Icons.Filled.Visibility,
                contentDescription = null,
                modifier = Modifier.clickable {
                    viewModel.onEvent(HomeEvent.ToggleEditViewPasswordVisibility)
                },
                tint = Color.Black
            )

        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)
        ) {
            Button(
                onClick = { viewModel.onEvent(HomeEvent.OpenAddDataSheet) },
                colors = ButtonDefaults.buttonColors(Color(0xFF2C2C2C)),
                modifier = Modifier.padding(2.dp).width(130.dp).height(50.dp)
            ) {
                Text(text = "Edit", color = Color.White)
            }
            Button(
                onClick = {
                    viewModel.onEvent(HomeEvent.DeleteData)
                    viewModel.onEvent(HomeEvent.CloseDataSheet)
                },
                colors = ButtonDefaults.buttonColors(Color(0xFFF04646)),
                modifier = Modifier.padding(2.dp).width(130.dp).height(50.dp)
            )
            {
                Text(text = "Delete", color = Color.White)
            }
        }
    }
}