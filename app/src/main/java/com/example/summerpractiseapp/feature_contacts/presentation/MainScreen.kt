package com.example.summerpractiseapp.feature_contacts.presentation

import android.content.Context
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import android.util.Base64
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.example.summerpractiseapp.R
import com.example.summerpractiseapp.common.Resource
import com.example.summerpractiseapp.common.components.ErrorComponent
import com.example.summerpractiseapp.common.components.LoadingComponent
import com.example.summerpractiseapp.common.helpers.callPhone
import com.example.summerpractiseapp.common.helpers.openBase64File
import com.example.summerpractiseapp.feature_contacts.data.model.UserData
import com.example.summerpractiseapp.feature_contacts.presentation.components.IconComponent
import com.example.summerpractiseapp.feature_favourite.data.model.FavouriteContactsEntity
import com.example.summerpractiseapp.ui.theme.BackgroundColorForPhoneItem
import com.example.summerpractiseapp.ui.theme.SummerPractiseAppTheme
import java.io.File
import java.io.FileOutputStream


@Composable
fun MainScreen(
    favouriteState: Resource<List<FavouriteContactsEntity>>,
    userState: Resource<List<UserData>>,
    addToFavourite: (contact: FavouriteContactsEntity) -> Unit,
    deleteFromFavourite: (contact: FavouriteContactsEntity) -> Unit
) {
    when (userState) {
        is Resource.Loading -> {
            LoadingComponent()
        }

        is Resource.Error -> {
            ErrorComponent(valueText = userState.message)
        }

        is Resource.Success -> {
            UserTable(userState.data, addToFavourite, deleteFromFavourite, favouriteState)
        }
    }
}

@Composable
fun UserTable(
    userList: List<UserData>,
    addToFavourite: (contact: FavouriteContactsEntity) -> Unit,
    deleteFromFavourite: (contact: FavouriteContactsEntity) -> Unit,
    favouriteState: Resource<List<FavouriteContactsEntity>>
) {
    val context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(userList) { userData ->
            Card(
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = BackgroundColorForPhoneItem)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = userData.name,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = userData.phone,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleLarge
                        )
                        userData.fileName?.let {
                            Text(
                                text = it,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                        userData.fileExtension?.let {
                            Text(
                                text = it,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                    userData.file?.let { base64String ->
                        IconComponent(
                            icon = R.drawable.outline_file_open_24,
                            contentDescription = null,
                            circleColor = Color.Gray,
                            modifier = Modifier.clickable {
                                openBase64File(
                                    context,
                                    base64String,
                                    userData.fileExtension,
                                    userData.fileName ?: "file"
                                )
                            })
                    }
                    if (favouriteState is Resource.Success) {
                        val isFavourite =
                            favouriteState.data.map { it.phone }.contains(userData.phone)
                        if (isFavourite) {
                            IconComponent(
                                icon = R.drawable.baseline_favorite_24,
                                contentDescription = null,
                                circleColor = Color.Gray,
                                modifier = Modifier.clickable {
                                    deleteFromFavourite(
                                        FavouriteContactsEntity(
                                            phone = userData.phone,
                                            name = userData.name,
                                            file = userData.file,
                                            fileName = userData.fileName,
                                            fileExtension = userData.fileExtension
                                        )
                                    )
                                },
                                iconTint = Color.Red
                            )
                        } else {
                            IconComponent(
                                icon = R.drawable.outline_favorite_border_24,
                                contentDescription = null,
                                circleColor = Color.Gray,
                                modifier = Modifier.clickable {
                                    addToFavourite(
                                        FavouriteContactsEntity(
                                            phone = userData.phone,
                                            name = userData.name,
                                            file = userData.file,
                                            fileName = userData.fileName,
                                            fileExtension = userData.fileExtension
                                        )
                                    )
                                },
                            )
                        }
                    }
                    IconComponent(
                        icon = R.drawable.outline_phone_24,
                        contentDescription = null,
                        circleColor = Color.Gray,
                        modifier = Modifier.clickable {
                            callPhone(userData.phone, context)
                        }
                    )
                }
            }
        }
    }

}


@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewMainScreen() {
    SummerPractiseAppTheme {
        MainScreen(
            userState = Resource.Success(
                listOf(
                    UserData("+799999999999", "Александр", "file", "Документы", "application/pdf"),
                    UserData("+799999999999", "name", null, "myfileName", null),
                    UserData("+799999999999", "name", "file", "myFileName", "pdf"),
                    UserData("+799999999999", "name", "file", "myFileName", "pdf"),
                )
            ),
            addToFavourite = {},
            favouriteState = Resource.Success(
                data = listOf(
                    FavouriteContactsEntity(
                        "+799999999999", "Александр", "file", "Документы", "application/pdf"
                    )
                )
            ),
            deleteFromFavourite = {}
        )
    }
}