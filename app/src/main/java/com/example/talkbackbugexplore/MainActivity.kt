package com.example.talkbackbugexplore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val COLUMN_CAPACITY = 4

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                MainPage()
            }
        }
    }
}

@Composable
fun MainPage() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Toolbar()
        Spacer(modifier = Modifier.weight(1f))
        Block(text = "Some text", color = Color.Red)
        Pager()
        Spacer(modifier = Modifier.weight(1f))

    }
}

@Composable
fun Toolbar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(Color.Blue)
    ) {
        Text(text = "Toolbar")
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(modifier: Modifier = Modifier) {
    val state = rememberPagerState { 5 }
    HorizontalPager(
        state = state,
        modifier = modifier.semantics {
            collectionInfo = CollectionInfo(
                columnCount = 5,
                rowCount = COLUMN_CAPACITY
            )
        }
    ) {
        PagerContent(it)
    }
}

@Composable
fun PagerContent(page: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        repeat(COLUMN_CAPACITY) {
            Block(
                text = "Block ${it + COLUMN_CAPACITY * page + 1}",
                modifier = Modifier.semantics {
                    collectionItemInfo = CollectionItemInfo(
                        rowIndex = it,
                        columnIndex = page,
                        rowSpan = 1,
                        columnSpan = 1
                    )
                }
            )
        }
    }
}

@Composable
fun Block(text: String, modifier: Modifier = Modifier, color: Color = Color.Cyan) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainPage()
}