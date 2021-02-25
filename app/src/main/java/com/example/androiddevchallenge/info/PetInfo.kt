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
package com.example.androiddevchallenge.info

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign.Center
import androidx.compose.ui.text.style.TextAlign.Justify
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.PetRepository
import com.example.androiddevchallenge.ui.theme.LightColorPalette
import dev.chrisbanes.accompanist.glide.GlideImage

private const val HAPPY_GIF = "https://media.giphy.com/media/THIvbrqPhSsTHxpuua/giphy.gif"

@Composable
fun PetInfo(petId: Int) {
    val adopted = remember { mutableStateOf(false) }
    val puppy = PetRepository.items.value!!.find { it.id == petId }!!

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.size(16.dp))
        GlideImage(
            data = if (!adopted.value) puppy.imageUrl else HAPPY_GIF,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .border(BorderStroke(16.dp, LightColorPalette.secondary), shape = CircleShape)
                .size(250.dp)
                .padding(4.dp)
                .clickable { adopted.value = true }
        )
        Spacer(Modifier.size(16.dp))
        Text(text = puppy.name, textAlign = Center, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.size(16.dp))
        Text(
            text = puppy.info,
            textAlign = Justify,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(Modifier.size(16.dp))
        Button(
            enabled = !adopted.value,
            onClick = { adopted.value = true },
            modifier = Modifier.size(width = 150.dp, height = 50.dp)
        ) {
            Text(
                text = if (!adopted.value) "Adopt" else "Thanks!!!",
                textAlign = Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
