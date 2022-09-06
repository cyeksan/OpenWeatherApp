package com.csappgenerator.weatherapp.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.csappgenerator.weatherapp.common.Constants

@Composable
fun TopInfo(
    location: String,
    temperature: String,
    icon: String,
    description: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = Icons.Default.Place, contentDescription = "")
        Text(text = location, style = MaterialTheme.typography.subtitle1)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = temperature, style = MaterialTheme.typography.h1,
            fontWeight = FontWeight.Bold
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constants.IMAGE_BASE_URL + icon + Constants.IMAGE_SUFFIX)
                .build(),
            modifier = Modifier
                .size(80.dp),
            contentDescription = null
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )
    }
}