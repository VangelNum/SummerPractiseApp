package com.example.summerpractiseapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.summerpractiseapp.R
val UbuntyFonts = FontFamily(
        Font(R.font.ubuntulight),
        Font(R.font.ubuntubold, FontWeight.Bold),
        Font(R.font.ubuntulight, FontWeight.Normal),
        Font(R.font.ubuntumedium, FontWeight.Medium),
        Font(R.font.ubuntuitalic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.ubuntumediumitalic, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.ubuntubolditalic, FontWeight.Bold, FontStyle.Italic),
)

val Typography = Typography(
        bodyLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
        ),
        titleLarge = TextStyle(
                fontFamily = UbuntyFonts,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
        ),
        titleMedium = TextStyle(
                fontFamily = UbuntyFonts,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
        ),
        titleSmall = TextStyle(
                fontFamily = UbuntyFonts,
                fontWeight = FontWeight.Light,
                fontSize = 11.sp
        )
)