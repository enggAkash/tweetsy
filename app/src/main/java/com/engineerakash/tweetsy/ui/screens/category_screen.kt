package com.engineerakash.tweetsy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CategoryItem(category: String) {

    Box(
        modifier = Modifier
            .size(100.dp)
            .border(0.5.dp, Color.Gray, RectangleShape)
            .background(Color.Red)
            .padding(8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(category, style = MaterialTheme.typography.bodyLarge)
    }

}

@Composable
fun CategoryList(categories: List<String>, modifier: Modifier = Modifier) {

    if (categories.isEmpty()) {
        CircularProgressIndicator(modifier = modifier.padding(10.dp))
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(5.dp),
            modifier = modifier
        ) {
            items(categories.size) {
                CategoryItem(category = categories[it])
            }
        }
    }

}


@Preview
@Composable
fun CategoryItemPreview() {
    CategoryItem("Android")
}

@Preview
@Composable
fun CategoryListPreview() {
    CategoryList(emptyList())
}