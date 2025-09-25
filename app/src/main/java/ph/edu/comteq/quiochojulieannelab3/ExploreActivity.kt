package ph.edu.comteq.quiochojulieannelab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.comteq.quiochojulieannelab3.ui.theme.QuiochoJulieAnneLab3Theme

class ExploreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                    Explore()
        }
    }
}

@Composable
fun Explore() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Explore",
            color = Color(0xFFEEDC82),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Upcoming Event",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Tickets >",
                color = Color.White,
                fontSize = 16.sp
            )


        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.renaissance),
                    contentDescription = "Renaissance Exhibition",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally)
                    {

                            Text(
                                text = "10",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "OCT", color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Renaissance Exhibition",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "9:00 AM - 6:00 PM",
                                color = Color.LightGray,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "Indulge in the rich tapestry of Renaissance art",
                                color = Color(0xFFFFD54F),
                                textDecoration = Underline,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "+33 (0)1 23 45 67 89",
                                textDecoration = Underline,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }


                Button (
                    onClick = { /* TODO: Open Gallery */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD54F)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Visit Gallery",
                        color = Color.Black,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
@Composable
fun ExplorePreview() {
    QuiochoJulieAnneLab3Theme {
        Explore()
    }
}