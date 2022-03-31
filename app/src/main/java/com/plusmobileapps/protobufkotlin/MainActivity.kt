package com.plusmobileapps.protobufkotlin

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.plusmobileapps.protobufkotlin.ui.theme.ProtobufKotlinTheme
import com.plusmobileapps.protobufkotlin.viewmodel.MainViewModel
import com.plusmobileapps.protobufkotlin.viewmodel.MainViewModelFactory
import com.plusmobileapps.protobufkotlin.viewmodel.MainViewState
import tutorial.DogOuterClass.Dog
import tutorial.dog

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProtobufKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(
                        viewModel = viewModel,
                        onRefreshClicked = viewModel::onRefreshClicked
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel, onRefreshClicked: () -> Unit) {
    val state by viewModel.state.collectAsState()
    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(
            text = { Text(text = "Refresh") },
            onClick = onRefreshClicked,
        )
    }) {
        Column(modifier = Modifier.fillMaxSize()) {
            when (val currentState = state) {
                is MainViewState.Error -> Text(text = currentState.message)
                is MainViewState.Loaded -> DogList(dogs = currentState.dogs)
                MainViewState.Loading -> CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun DogList(dogs: List<Dog>) {
    LazyColumn {
        items(dogs) { dog ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(model = dog.imageUrl, placeholder = painterResource(id = android.R.drawable.ic_menu_report_image), contentDescription = null)
                    Text(text = dog.breedName)
                }
            }
        }
    }
}

@Preview
@Composable
fun DogListPreview() {
    DogList(dogs = listOf(
        dog {
            id = 1
            breedName = "Doodle"
        }
    ))
}