package ph.edu.comteq.quiochojulieannelab3

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import ph.edu.comteq.quiochojulieannelab3.ui.theme.QuiochoJulieAnneLab3Theme

class ExhibitPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val Image = intent.getStringExtra("Image") ?: ""

            val artwork = Artwork(
                title = "",
                years = "",
                bornAt = "",
                comment = "" ,

                image = Image
            )
            ArtistExhibitScreen(artwork, modifier = Modifier)
        }
    }
}
fun loadArtworksFromJson(context: Context): List<Artwork> {
    val jsonString = context.assets.open("Cover/artwork.json").bufferedReader().use { it.readText() }
    return Gson().fromJson(jsonString, Array<Artwork>::class.java).toList()
}


@Composable
fun ArtistExhibitScreen(artwork: Artwork, modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
        .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Column(modifier = Modifier
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(artwork.image),
                contentDescription = null,
                modifier = Modifier
                    .height(450.dp)
                    .width(280.dp)
                    .clip(RoundedCornerShape(topStart = 200.dp, topEnd = 200.dp)),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFEEDC82),)

            ){
                Column (modifier = Modifier.padding(15.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFEEDC82),)
                ){
                    Text(
                        text = artwork.title,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "Born: ${artwork.bornAt}")
                }

            }
        }
        Row {
            Image(
                painter = painterResource(R.drawable.quote),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp),
                contentScale = ContentScale.Crop
            )
            Text(modifier = Modifier
                .width(200.dp),
                text = artwork.comment,
                color = Color.White
            )
        }

    }
}
