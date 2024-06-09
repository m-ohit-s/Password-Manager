package com.assignment.passwordmanager.presentation.home.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.assignment.passwordmanager.domain.model.Account
import com.assignment.passwordmanager.presentation.utils.Utility

@Composable
fun DataField(
    modifier: Modifier = Modifier,
    account: Account
){
    Card(
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(50),
        modifier = modifier
            .height(70.dp)
            .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = account.accountName,
                    color = Color(0xFF333333),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight(600),
                    fontSize = Utility().pxToSp(20.0, LocalContext.current),
                    lineHeight = Utility().pxToSp(23.87, LocalContext.current),
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "********",
                    color = Color(0xFFC6C6C6),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight(600),
                    fontSize = Utility().pxToSp(20.0, LocalContext.current),
                    lineHeight = Utility().pxToSp(23.87, LocalContext.current)
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color(0xFF333333)
            )
        }
    }
}