package edu.unicauca.mindfulnesslayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.unicauca.mindfulnesslayout.ui.theme.MindfulnessLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindfulnessLayoutTheme {
                HomeScreen()
            }
        }
    }
}


@Composable
fun SearchBar(modifier: Modifier = Modifier){
    TextField(
        value = "",
        placeholder = {
            Text(text = stringResource(R.string.search_text))
        },
        onValueChange = {},
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.onSecondary,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Preview
@Composable
fun SearchBarPreview(){
    MindfulnessLayoutTheme {
        SearchBar()
    }
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawableId: Int,
    @StringRes textId: Int,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = modifier) {
        Image(
            painter = painterResource(drawableId),
            contentDescription = stringResource(textId),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape))
        Text(
            text = stringResource(textId),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElementPreview() {
    MindfulnessLayoutTheme {
        AlignYourBodyElement(
            drawableId = R.drawable.ab3_stretching,
            textId = R.string.stretching_txt,
            modifier = Modifier.padding(8.dp)
            )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawableId: Int,
    @StringRes textId: Int,
    modifier: Modifier = Modifier
){
    Surface (
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)

        ) {
            Image(
                painter = painterResource(drawableId),
                contentDescription = stringResource(textId),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(textId),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview() {
    MindfulnessLayoutTheme {
        FavoriteCollectionCard(
            drawableId = R.drawable.fc2_nature_meditations,
            textId = R.string.nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {

        items(alignYourBodyData) { item ->
            AlignYourBodyElement(item.drawable, item.text)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyRowPreview() {
    MindfulnessLayoutTheme {
        AlignYourBodyRow()
    }
}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    Surface {
        Column {
            SearchBar()
            AlignYourBodyRow()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    MindfulnessLayoutTheme {
        HomeScreen()
    }
}


//Create a map named alignYourBodyData to store the data
private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.inversions_txt,
    R.drawable.ab2_quick_yoga to R.string.quick_yoga_txt,
    R.drawable.ab3_stretching to R.string.stretching_txt,
    R.drawable.ab4_tabata to R.string.tabata_txt,
    R.drawable.ab5_hiit to R.string.hiit_txt
).map { DrawableResourcePair(it.first, it.second)  }

private data class DrawableResourcePair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)