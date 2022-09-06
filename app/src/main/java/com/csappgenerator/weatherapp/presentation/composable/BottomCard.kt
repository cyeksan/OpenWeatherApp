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
fun BottomCard(
    wind: String,
    direction: String,
    pressure: String
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
                    .weight(2.1f)
                    .padding(8.dp, 24.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.wind),
                        modifier = Modifier.size(64.dp),
                        contentDescription = ""
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = "Wind",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            text = wind,
                            fontSize = 12.sp
                        )

                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.ic_direction
                                ),
                                contentDescription = ""
                            )
                            Text(
                                modifier = Modifier.padding(start = 8.dp),
                                text = direction, style = MaterialTheme.typography.subtitle2
                            )
                        }


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
                    .padding(8.dp, 24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pressure),
                        contentDescription = ""
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)

                    ) {
                        Text(
                            text = "Press.",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            text = pressure,
                            fontSize = 12.sp
                        )

                    }
                }
            }
        }

    }
}
