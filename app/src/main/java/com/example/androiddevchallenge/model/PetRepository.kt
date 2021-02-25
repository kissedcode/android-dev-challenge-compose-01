/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.Url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray

object PetRepository {

    private val _items: MutableLiveData<List<Pet>> = MutableLiveData(listOf())
    val items: LiveData<List<Pet>> = _items

    private val httpClient = HttpClient()

    init {
        GlobalScope.launch {
            repeat(100) { id ->
                launch {
                    val response =
                        httpClient.get<String>(url = Url("https://api.thecatapi.com/v1/images/search"))
                    val url = JSONArray(response).getJSONObject(0).getString("url")
                    withContext(Dispatchers.Main) {
                        _items.value = _items.value!!.plus(
                            Pet(
                                id,
                                name = generateRandomName(),
                                info = generateRandomInfo(),
                                imageUrl = url,
                            )
                        )
                    }
                }
            }
        }
    }

    private fun generateRandomName(): String {
        return listOf(
            "Aidan",
            "Alfred",
            "Baird",
            "Balfour",
            "Baron",
            "Benson",
            "Calder",
            "Charles",
            "Chester",
            "Corbin",
            "Damien",
            "Darby",
            "Devlin",
            "Dolan",
            "Fairfax",
            "Finn",
            "Gallagher",
            "Gavin",
            "George",
            "Keely",
            "Kingston",
            "Ladd",
            "Maddock",
            "Manfred",
            "Murray",
            "Rhys",
            "Scully",
            "Sherman",
            "Wallace",
            "Waite",
            "Winston",
            "William",
            "Wynn",
        ).random()
    }

    private fun generateRandomInfo(): String {
        return listOf(
            """
        The domestic cat is the most tamed four-legged animal and the favorite pet in America. 
        According to a survey conducted by American pet product manufacturers association (APPMA), there are approximately 73 million cats in houses through out the country while there are 68 million dogs.
            """.trimIndent()
        ).random()
    }
}
