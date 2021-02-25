package com.example.androiddevchallenge.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.model.PetRepository

class PetListViewModel(private val navController: NavController) : ViewModel() {

  val state: LiveData<List<Pet>> = PetRepository.items

  fun onClick(pet: Pet) {
    navController.navigate("info/${pet.id}")
  }

  companion object {

    fun factory(navController: NavController) = object : ViewModelProvider.Factory {
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PetListViewModel(navController) as T
      }
    }
  }
}