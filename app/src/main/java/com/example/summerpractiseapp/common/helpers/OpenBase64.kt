package com.example.summerpractiseapp.common.helpers

import android.content.Context
import android.content.Intent
import android.util.Base64
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream


fun openBase64File(context: Context, base64String: String, fileType: String?, fileName: String?) {
    val fileData = Base64.decode(base64String, Base64.DEFAULT)
    val fileFormat = fileType?.substringAfterLast("/")

    val tempFile = File(context.cacheDir, "$fileName.$fileFormat")

    FileOutputStream(tempFile).use { outputStream ->
        outputStream.write(fileData)
        outputStream.flush()
    }

    val uri = FileProvider.getUriForFile(context, context.packageName + ".fileprovider", tempFile)

    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(uri, fileType)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    context.startActivity(intent)
}
