package com.example.summerpractiseapp.feature_favourite.presentation

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.summerpractiseapp.R
import com.example.summerpractiseapp.common.Resource
import com.example.summerpractiseapp.common.components.ErrorComponent
import com.example.summerpractiseapp.common.components.LoadingComponent
import com.example.summerpractiseapp.common.helpers.callPhone
import com.example.summerpractiseapp.common.helpers.openBase64File
import com.example.summerpractiseapp.feature_contacts.presentation.components.IconComponent
import com.example.summerpractiseapp.feature_favourite.data.model.FavouriteContactsEntity
import com.example.summerpractiseapp.feature_recent_calls.data.model.RecentCallsEntity
import com.example.summerpractiseapp.ui.theme.BackgroundColorForPhoneItem
import com.example.summerpractiseapp.ui.theme.SummerPractiseAppTheme

@Composable
fun FavouriteScreen(
    favouriteState: Resource<List<FavouriteContactsEntity>>,
    deleteFromFavourite: (contact: FavouriteContactsEntity) -> Unit,
    insertToRecentCalls: (recentCall: RecentCallsEntity) -> Unit,
    deleteUser: (String) -> Unit
) {
    when (favouriteState) {
        is Resource.Loading -> {
            LoadingComponent()
        }

        is Resource.Error -> {
            ErrorComponent(valueText = favouriteState.message)
        }

        is Resource.Success -> {
            val context = LocalContext.current
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favouriteState.data) { userData ->
                    val phoneNumber = "+" + userData.phone
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
                                    text = phoneNumber,
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
                            Column {
                                IconComponent(
                                    icon = R.drawable.baseline_close_24,
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
                                )
                                userData.file?.let { base64String ->
                                    Spacer(modifier = Modifier.height(8.dp))
                                    IconComponent(
                                        icon = R.drawable.outline_file_open_24,
                                        contentDescription = null,
                                        circleColor = Color.Gray,
                                        modifier = Modifier.clickable {
                                            openBase64File(
                                                context,
                                                base64String,
                                                userData.fileExtension,
                                                userData.fileName
                                            )
                                        })
                                }
                            }
                            Column {
                                IconComponent(
                                    icon = R.drawable.baseline_delete_outline_24,
                                    contentDescription = null,
                                    circleColor = Color.Gray,
                                    modifier = Modifier.clickable {
                                        deleteUser(userData.phone)
                                    })
                                Spacer(modifier = Modifier.height(8.dp))
                                IconComponent(
                                    icon = R.drawable.outline_phone_24,
                                    contentDescription = null,
                                    circleColor = Color.Gray,
                                    modifier = Modifier.clickable {
                                        callPhone(phoneNumber, context)
                                        insertToRecentCalls(
                                            RecentCallsEntity(
                                                userData.phone,
                                                userData.name,
                                                userData.file,
                                                userData.fileName,
                                                userData.fileExtension
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewFavouriteScreen() {
    SummerPractiseAppTheme {
        FavouriteScreen(
            favouriteState = Resource.Success(
                listOf(
                    FavouriteContactsEntity("799999999991", "Alexandr", "file.name", "file", "pdf")
                )
            ),
            deleteFromFavourite = {},
            insertToRecentCalls = {},
            deleteUser = {}
        )
    }
}
