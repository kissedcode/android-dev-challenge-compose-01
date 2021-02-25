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
package com.example.androiddevchallenge.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.LightColorPalette
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun PetItem(pet: Pet, onClick: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(Modifier.size(8.dp))
            GlideImage(
                data = pet.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .border(BorderStroke(4.dp, LightColorPalette.secondary), shape = CircleShape)
            )
            Text(
                text = pet.name,
                color = Color.Black,
                fontStyle = MaterialTheme.typography.caption.fontStyle,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(widthDp = 200, heightDp = 200)
@Composable
fun PuppyItemPreview() {
    Box(contentAlignment = Alignment.Center) {
        PetItem(
            Pet(id = 0, name = "name", info = "info", imageUrl = ""),
            onClick = {}
        )
    }
}
