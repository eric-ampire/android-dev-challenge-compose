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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.model.testData
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@ExperimentalAnimationApi
@Composable
fun MyApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 4.dp,
                title = { Text(text = "Puppy Adoption") },
            )
        },
        content = {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    ListPuppy(
                        puppies = testData,
                        onItemClick = {
                            navController.navigate("detail/${it.id}")
                        }
                    )
                }
                composable("detail/{id}") { navBackStackEntry ->
                    DetailPuppy(navBackStackEntry.arguments?.getString("id"))
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailPuppyPreview() {
    DetailPuppy("1")
}

@Composable
fun DetailPuppy(id: String?) {
    val puppy = testData.first { it.id == id?.toInt() }
    Column {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(16.dp),
            content = {
                PuppyItem(
                    puppy = puppy,
                    onClick = { },
                    maxLine = Int.MAX_VALUE
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF75356)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            content = {
                Text(text = "Adopt", color = Color.White)
            }
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun ListPuppy(puppies: List<Puppy>, onItemClick: (Puppy) -> Unit) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val isButtonVisible by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    Box {
        LazyColumn(state = listState, contentPadding = PaddingValues(bottom = 80.dp)) {
            items(puppies) { puppy ->
                PuppyItem(
                    puppy = puppy,
                    onClick = { onItemClick(puppy) }
                )
                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(Color(0xFFF3F4F5))
                )
            }
        }

        AnimatedVisibility(
            visible = isButtonVisible,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = fadeIn(),
            exit = fadeOut(),
            content = {
                FloatingActionButton(
                    backgroundColor = MaterialTheme.colors.surface,
                    modifier = Modifier.padding(bottom = 16.dp),
                    onClick = {
                        coroutineScope.launch {
                            listState.scrollToItem(0)
                        }
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Rounded.ArrowUpward,
                            contentDescription = null
                        )
                    }
                )
            }
        )
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}


@Composable
fun PuppyItem(puppy: Puppy, onClick: (Puppy) -> Unit, maxLine: Int = 2) {
    ConstraintLayout(
        modifier = Modifier.clickable { onClick(puppy) },
        content = {
            val puppyNameText = createRef()
            val createdAtText = createRef()
            val shareButton = createRef()
            val userImage = createRef()
            val puppyImage = createRef()
            val bonesText = createRef()
            val snapsText = createRef()
            val divider = createRef()
            val description = createRef()

            Text(
                text = puppy.description,
                maxLines = maxLine,
                textAlign = TextAlign.Justify,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(description) {
                        top.linkTo(divider.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(16.dp)
            )

            Box(
                modifier = Modifier
                    .constrainAs(divider) {
                        top.linkTo(bonesText.bottom, 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .height(0.7.dp)
            )

            Row(
                modifier = Modifier.constrainAs(bonesText) {
                    top.linkTo(puppyImage.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                },
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_bones),
                        contentDescription = null
                    )
                    Text(text = "${puppy.bones} Bones", Modifier.padding(start = 16.dp))
                }
            )

            Row(
                modifier = Modifier.constrainAs(snapsText) {
                    top.linkTo(bonesText.top)
                    start.linkTo(bonesText.end, 24.dp)
                    bottom.linkTo(bonesText.bottom)
                },
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_snaps),
                        contentDescription = null
                    )
                    Text(text = "${puppy.snaps} Snaps", Modifier.padding(start = 16.dp))
                }
            )

            Image(
                painter = painterResource(id = puppy.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .constrainAs(puppyImage) {
                        top.linkTo(userImage.bottom, 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.ic_share),
                contentDescription = null,
                modifier = Modifier
                    .size(38.dp)
                    .constrainAs(shareButton) {
                        end.linkTo(parent.end, margin = 16.dp)
                        top.linkTo(userImage.top, margin = 16.dp)
                        bottom.linkTo(userImage.bottom, margin = 16.dp)
                    }
            )

            Image(
                painter = painterResource(id = puppy.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .constrainAs(userImage) {
                        top.linkTo(parent.top, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                    }
            )

            Text(
                text = AnnotatedString(puppy.name),
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(puppyNameText) {
                    top.linkTo(userImage.top)
                    start.linkTo(userImage.end, 16.dp)
                }
            )

            Text(
                text = AnnotatedString(puppy.createdAt),
                color = MaterialTheme.typography.caption.color,
                fontWeight = MaterialTheme.typography.caption.fontWeight,
                modifier = Modifier.constrainAs(createdAtText) {
                    start.linkTo(puppyNameText.start)
                    bottom.linkTo(userImage.bottom)
                }
            )
        }
    )
}

@Preview(name = "Puppy")
@Composable
fun PuppyItemPreview() {
    PuppyItem(
        puppy = testData.first(),
        onClick = {

        }
    )
}