package com.engineerakash.tweetsy

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.engineerakash.tweetsy.api.Resource
import com.engineerakash.tweetsy.ui.screens.CategoryList
import com.engineerakash.tweetsy.ui.theme.TweetsyTheme
import com.engineerakash.tweetsy.viewmodel.QuotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TweetsyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TweetsyApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
@Composable
fun TweetsyApp(
    modifier: Modifier = Modifier,
    viewModel: QuotesViewModel = viewModel<QuotesViewModel>()
) {

    val resource by viewModel.categories.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getCategories()
    }

    if (resource == Resource.Loading) {
        ProgressBar(LocalContext.current)

    } else if (resource is Resource.Success<*>) {

        if ((resource as Resource.Success<Any?>).data is List<*>) {
            CategoryList(
                (resource as Resource.Success<Any?>).data as List<String>,
                modifier = modifier
            )
        } else {
            Text("Failed to get Categories", modifier = modifier)
        }

    } else if (resource is Resource.Error) {

        Text(
            (resource as Resource.Error).errorMessage.ifBlank { "Failed to get Categories" },
            modifier = modifier
        )

    } else {

        Text("Failed to get Categories", modifier = modifier)
    }

}

@Preview(showBackground = true)
@Composable
fun TweetsyAppPreview() {
    TweetsyTheme {
        TweetsyApp()
    }
}