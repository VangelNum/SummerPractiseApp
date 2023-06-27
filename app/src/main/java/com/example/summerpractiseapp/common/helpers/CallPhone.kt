package com.example.summerpractiseapp.common.helpers

import android.content.Context
import android.content.Intent
import android.net.Uri

fun callPhone(phoneNumber: String, context: Context) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    context.startActivity(intent)
}