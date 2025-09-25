package ph.edu.comteq.quiochojulieannelab3

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.comteq.quiochojulieannelab3.ui.theme.QuiochoJulieAnneLab3Theme

val playfairdisplayregular = FontFamily(
    Font(R.font.playfairdisplayregular, FontWeight.Normal)

)
val optima = FontFamily(
    Font(R.font.optima, FontWeight.Normal)
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                    Homepage(
                    )
                }
    }
}

@Composable
fun Homepage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "upper Logo",

            modifier = Modifier
                .padding(bottom = 16.dp)
                .height(100.dp)

        )
        Text (
            text = "Experience Art",
            color = Color.White,
            fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
            modifier = Modifier
//                .align(Alignment.BottomCenter)
                .padding(16.dp)

        )
            Image (
                painter = painterResource( R.drawable.louvre),
                contentDescription = "louvre",
                contentScale = ContentScale.Crop,
                modifier = Modifier

                    .height(400.dp)
            )



        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text ="We are thrilled to invite you to join us for an extraordinary event that will immerse you in the world of art.",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
        val context = LocalContext.current
        Button(
            onClick = {
                val intent = Intent(context, ExploreActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier.padding(vertical = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFD54F)
            )
        ) {
            Text(text = "Explore Now", color = Color.Black)
        }
    }
    }



@Preview(showBackground = true)
@Composable
fun HomepagePreview() {
    QuiochoJulieAnneLab3Theme {
        Homepage()
    }
}