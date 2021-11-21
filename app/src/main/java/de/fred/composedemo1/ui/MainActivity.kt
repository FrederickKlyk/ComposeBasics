package de.fred.composedemo1.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import de.fred.composedemo1.R
import de.fred.composedemo1.navigation.NavigationComponent
import de.fred.composedemo1.navigation.Navigator
import de.fred.composedemo1.ui.theme.ComposeDemo1Theme
import de.fred.designsystem.buttons.Buttons.DefaultButton
import de.fred.designsystem.buttons.Buttons.DefaultFAB
import org.koin.androidx.compose.get

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemo1Theme {
                val navigator = get<Navigator>()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavigationComponent(navController = navController, navigator = navigator)
                }
            }
        }
    }
}

@Composable
fun HomeScreenContent(viewModel: MainViewModel) {
    val items by viewModel.items.observeAsState()
    LaunchedEffect(items) {
        viewModel.initialize()
    }
    HomeScreenContent(items, viewModel::navigateToDetailsView)
}

@Composable
fun HomeScreenContent(items: MutableList<MainViewModelItem>?, navigateToDetailsView: () -> Unit) {
    Column {
        Button(onClick = navigateToDetailsView) {
            Text(text = "Go to detail")
        }
        ContentComponent(items)
    }
}

@Composable
fun ContentComponent(
    items: MutableList<MainViewModelItem>? = null,
) {
    Log.d("itemsize", "Size: ${items?.size}")
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopRowHeader()
        },
        content = {
            Box {
                Spacer(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.LightGray)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LeftComponent(modifier = Modifier.weight(2f))
                    MiddleComponent(
                        modifier = Modifier
                            .paddingFromBaseline(top = 8.dp)
                            .weight(1f), context = context
                    )
                    RightListComponent(items = items, context = context)
                }
            }
        },
        floatingActionButton = {
            DefaultFAB(drawable = android.R.drawable.ic_input_add, color = Color.Red) {
                showToast("Fab gedrückt", context = context)
            }
        }
    )
}

@Preview
@Composable
fun ContentComponentPreview() {
    ContentComponent(mutableListOf(MainViewModelItem("test", "test2")))
}

@Composable
fun TopRowHeader() {
    Row(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
    ) {
        Text(text = "test")
    }
}

@Preview(showBackground = true)
@Composable
fun TopRowHeaderPreview() {
    TopRowHeader()
}

@Composable
fun LeftComponent(modifier: Modifier) {
    Text(
        text = "Ich bin auf der linken Seite",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun LeftComponentPreview() {
    LeftComponent(modifier = Modifier.fillMaxWidth(1f))
}

@Composable
fun MiddleComponent(modifier: Modifier, context: Context) {
    Column(modifier = modifier.clickable {
        showToast("column", context)
    }) {
        Text(text = "1", modifier = Modifier.clickable {
            showToast("1", context)
        })
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        DefaultButton(text = "Press me", color = Color.Red) {
            showToast("Press me wurde gedrückt", context = context)
        }
        Text(text = "4", modifier = Modifier
            .offset(x = 16.dp)
            .clickable {
                showToast("4", context)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun MiddleComponentPreview() {
    val context = LocalContext.current
    MiddleComponent(modifier = Modifier.fillMaxWidth(1f), context = context)
}

@Composable
fun RightListComponent(items: MutableList<MainViewModelItem>?, context: Context) {
    LazyColumn {
        items(items = items ?: emptyList()) { item ->
            Text("Item is: ${item.name}", Modifier.clickable {
                item.onItemClick()
                showToast(item.name, context)
            })
        }
    }
}

@Composable
fun Clipcard() {
    Row {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "bild"
        )
        Column {
            Text(
                text = "hi",
                fontSize = 18.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = Bold,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(text = "was geht")
        }
    }
}

@Preview
@Composable
fun ClipcardPreview() {
    Clipcard()
}

@Composable
fun BoxExample() {
    Box(Modifier.fillMaxSize()) {
        Text(text = "This is first text", modifier = Modifier.align(Alignment.TopCenter))
        Box(
            Modifier
                .align(Alignment.TopCenter)
                .fillMaxHeight()
                .width(50.dp)
                .background(Color.Blue)
        )
        Text("This is second text", modifier = Modifier.align(Alignment.Center))
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp),
            onClick = {}
        ) {
            Text("+")
        }
    }
}

@Preview
@Composable
fun BoxExamplePreview() {
    BoxExample()
}

@Preview(showBackground = true)
@Composable
fun FABPreview() {
    DefaultFAB(drawable = android.R.drawable.ic_input_add, color = Color.Red) {}
}


fun showToast(msg: String, context: Context) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}