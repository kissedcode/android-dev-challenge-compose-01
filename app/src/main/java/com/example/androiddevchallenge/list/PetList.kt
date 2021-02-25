package com.example.androiddevchallenge.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PetList(viewModel: PetListViewModel) {

  val state by viewModel.state.observeAsState()

  if (state.isNullOrEmpty()) {
    Box(Modifier.fillMaxSize()) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.align(Alignment.Center)
      ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Loading 100 cats...")
      }
    }
  } else {
    Scaffold(
      topBar = {
        TopAppBar(title = { Text(text = "Choose a pet to adopt") })
      }
    ) {
      LazyColumn {
        item { Spacer(modifier = Modifier.size(4.dp)) }
        items(state!!) { puppy ->
          PetItem(puppy) {
            viewModel.onClick(puppy)
          }
          Spacer(modifier = Modifier.size(4.dp))
        }
      }
    }
  }
}


@Preview("Puppy list", widthDp = 360, heightDp = 640)
@Composable
fun PetListPreview() {
  MyTheme {
    PetList(viewModel = viewModel())
  }
}

