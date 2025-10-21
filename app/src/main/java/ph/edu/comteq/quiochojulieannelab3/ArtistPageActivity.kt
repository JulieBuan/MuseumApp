package ph.edu.comteq.quiochojulieannelab3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import coil.compose.rememberAsyncImagePainter
import ph.edu.comteq.quiochojulieannelab3.ui.theme.QuiochoJulieAnneLab3Theme

data class Artist(
    val name: String,
    val years: String,
    val avatar: String,
    val artworks: List<String>
)

data class Artwork(
    val title: String,
    val years: String,
    val bornAt: String,
    val comment: String,
    val image:String
)
class ArtistPageActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val tabItems = listOf("Artists", "Artworks")//tab names
            var selectedTab by remember { mutableStateOf(0) }

            val artistList = ArtistList()
            val navController = rememberNavController()
            val navGraph = navController.createGraph(startDestination = "artists"){
                composable("artists"){ ArtistCard(artistList, navController)}
                composable("artworks"){ Artwork(navController)}

            }
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            var selectedDestination by remember { mutableStateOf(0) }
            selectedDestination = when (currentRoute){
                "artists" -> 0
                "artworks" -> 1
                else -> 0
            } as Int
            QuiochoJulieAnneLab3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.background),
                            contentDescription = "Background",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 20.dp)
                                .padding(16.dp)
                        ) {

                            Text(
                                text = "Explore the art of",
                                color = Color.Black,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Renaissance",
                                color = Color(0xFFD4AF37),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(16.dp))


                           Column {
                               OutlinedTextField(
                                   value = "",
                                   onValueChange = {},
                                   placeholder = { Text("Type to search...") },
                                   leadingIcon = {
                                       Icon(
                                           imageVector = Icons.Outlined.Search,
                                           contentDescription = "Search"
                                       )
                                   },
                                   modifier = Modifier.fillMaxWidth()
                               )
                           }

                            Spacer(modifier = Modifier.height(10.dp))

                            Column (modifier = Modifier.padding(innerPadding)){
                                PrimaryTabRow(
                                    containerColor = Color.Transparent,
                                    selectedTabIndex = selectedDestination
                                ) {
                                    Tab(
                                        selected = selectedDestination == 0,
                                        onClick = {
                                            navController.navigate("artists")
                                            selectedDestination = 0},
                                        text = {Text(
                                            text = "Artists",
                                            color = Color(0xFFD4AF37)
                                        )},
                                        selectedContentColor = MaterialTheme.colorScheme.primary,
                                    )
                                    Tab(
                                        selected =  selectedDestination == 0,
                                        onClick = { navController.navigate("artworks")
                                            selectedDestination = 0},
                                        text = {Text(
                                            text = "Artworks",
                                            color = Color(0xFFD4AF37)
                                        )},
                                        selectedContentColor = MaterialTheme.colorScheme.primary,
                                    )
                                }
                            }
                            NavHost(
                                navController = navController,
                                graph = navGraph,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}




@Composable
fun ArtistCard(artists: List<Artist>, navController: NavHostController) {

    val context = LocalContext.current
    var artwork = String

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 32.dp),
        userScrollEnabled = true
    ) {
        items(artists) { artist ->

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)

            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = rememberAsyncImagePainter(artist.avatar),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(text = artist.name, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        Text(text = artist.years, color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(start = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    artist.artworks.forEach { artwork ->
                        Image(
                            painter = rememberAsyncImagePainter(artwork),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(180.dp)
                                .width(120.dp)
                                .clickable {

                                    val intent = Intent(context, ExhibitPageActivity::class.java).apply {
                                        putExtra("Image", artwork)
                                    }
                                    context.startActivity(intent)
                                }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Artwork( navController: NavController){
    Text(
        text = "List of Artworks",
        color = Color.Black

    )
}


fun ArtistList(): List<Artist> {
    val artists = listOf(
        Artist(
            name = "Leonardo da Vinci",
            years = "1452 - 1519",
            avatar = "file:///android_asset/Cover/leonardo_da_vinci/leonardo_da_vinci.jpeg",
            artworks = listOf(
                "file:///android_asset/Cover/leonardo_da_vinci/mona_lisa.jpg",
                "file:///android_asset/Cover/leonardo_da_vinci/lady_ermine.jpg",
                "file:///android_asset/Cover/leonardo_da_vinci/litta_madonna.jpg"
            )
        ),
        Artist(
            name = "Michael Angelo",
            years = "1475 - 1564",
            avatar = "file:///android_asset/Cover/michelangelo/michelangelo.jpg",
            artworks = listOf(
                "file:///android_asset/Cover/michelangelo/david.jpg",
                "file:///android_asset/Cover/michelangelo/delphic_sibyl.jpg",
                "file:///android_asset/Cover/michelangelo/torment_of_saint_anthony.jpg"
            )
        ),
        Artist(
            name = "Gustav Klimt",
            years = "1862 - 1918",
            avatar = "file:///android_asset/Cover/gustav_klimt/gustav_klimt.jpg",
            artworks = listOf(
                "file:///android_asset/Cover/gustav_klimt/adele_bloch_bauer.jpg",
                "file:///android_asset/Cover/gustav_klimt/lady_with_fan.jpg",
                "file:///android_asset/Cover/gustav_klimt/the_kiss.jpg"
            )
        )
    )

    return artists
}
