package com.csappgenerator.weatherapp.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csappgenerator.weatherapp.R
import com.csappgenerator.weatherapp.presentation.ui.theme.cardColor
import com.csappgenerator.weatherapp.presentation.ui.theme.dividerColor

@Composable
fun TopCard(
    humidity: String,
    clouds: String,
    visibility: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp, 8.dp),
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.cardColor
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp, 24.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_humidity),
                        contentDescription = ""
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = "Hum.", style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            text = humidity,
                            fontSize = 12.sp
                        )

                    }
                }
            }
            Divider(
                color = MaterialTheme.colors.dividerColor,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp, 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cloud),
                        contentDescription = ""
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)

                    ) {
                        Text(
                            text = "Clouds", style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            text = clouds,
                            fontSize = 12.sp
                        )

                    }
                }
            }
            Divider(
                color = MaterialTheme.colors.dividerColor,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp, 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_visibility),
                        contentDescription = ""
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)

                    ) {
                        Text(
                            text = "Vis.",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            text = visibility,
                            fontSize = 12.sp
                        )

                    }
                }
            }
        }
    }

}