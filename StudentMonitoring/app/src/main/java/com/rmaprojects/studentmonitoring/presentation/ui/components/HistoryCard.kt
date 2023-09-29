package com.rmaprojects.studentmonitoring.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HistoryCard(
    studentName: String,
    description: String,
    teacherName: String,
    point: Int,
    modifier: Modifier = Modifier
) {
    var isCardExpanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = studentName,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Dilaporkan oleh: $teacherName",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                IconButton(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = { isCardExpanded = !isCardExpanded }
                ) {
                    Icon(
                        imageVector = if (!isCardExpanded) Icons.Default.KeyboardArrowRight
                        else Icons.Default.KeyboardArrowDown,
                        contentDescription = "See description"
                    )
                }
            }
            if (isCardExpanded) {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(2F)
                    ) {
                        Text(text = "Deskripsi pelanggaran:")
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "$point",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Point",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

            }
        }
    }
}