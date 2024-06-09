package com.assignment.passwordmanager.presentation.utils

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Utility {
    fun pxToDp(px: Double, context: Context): Dp {
        val density = context.resources.displayMetrics.density
        return (px / density).dp
    }

    fun pxToSp(px: Double, context: Context): TextUnit {
        val scaledDensity = context.resources.configuration.fontScale
        return (px / scaledDensity).sp
    }
}