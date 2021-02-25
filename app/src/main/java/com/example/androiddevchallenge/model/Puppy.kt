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

import com.example.androiddevchallenge.R

data class Puppy(
    val id: Int,
    val name: String,
    val description: String,
    val createdAt: String,
    val bones: Int,
    val snaps: Int,
    val imageRes: Int,
    val ownerImageRes: Int
)

val testData = listOf(
    Puppy(
        id = 1,
        name = "Rox",
        description = "Lorem ipsum dolor sit amet, consectetur adipi\u000Bcing elit, sed do eiusmod tempor incididunt ut\u000Blabore et dolore magna aliqua.",
        createdAt = "5:30 pm",
        bones = 23,
        snaps = 45,
        imageRes = R.drawable.p1,
        ownerImageRes = 2
    ),
    Puppy(
        id = 2,
        name = "Foxi",
        description = "Lorem ipsum dolor sit amet, consectetur adipi\u000Bcing elit, sed do eiusmod tempor incididunt ut\u000Blabore et dolore magna aliqua.",
        createdAt = "11:30 pm",
        bones = 34,
        snaps = 12,
        imageRes = R.drawable.p2,
        ownerImageRes = 2
    ),
    Puppy(
        id = 3,
        name = "Poxi",
        description = "Lorem ipsum dolor sit amet, consectetur adipi\u000Bcing elit, sed do eiusmod tempor incididunt ut\u000Blabore et dolore magna aliqua.",
        createdAt = "5:30 pm",
        bones = 4,
        snaps = 13,
        imageRes = R.drawable.p3,
        ownerImageRes = 2
    ),
    Puppy(
        id = 4,
        name = "Noxxi",
        description = "Lorem ipsum dolor sit amet, consectetur adipi\u000Bcing elit, sed do eiusmod tempor incididunt ut\u000Blabore et dolore magna aliqua.",
        createdAt = "1:30 pm",
        bones = 12,
        snaps = 43,
        imageRes = R.drawable.p4,
        ownerImageRes = 2
    ),
    Puppy(
        id = 5,
        name = "Doxxi",
        description = "Lorem ipsum dolor sit amet, consectetur adipi\u000Bcing elit, sed do eiusmod tempor incididunt ut\u000Blabore et dolore magna aliqua.",
        createdAt = "3:30 pm",
        bones = 54,
        snaps = 13,
        imageRes = R.drawable.p5,
        ownerImageRes = 2
    ),

)
