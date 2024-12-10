import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.tastecode.R
import com.example.tastecode.ui.theme.Poppins

@Composable
fun RecipeDetailsScreen(navController: NavController) {
    val recipeData = SharedData.recipeData
    var selectedTab by remember { mutableIntStateOf(0) }
    val onBackPressedDispatcher =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher// 0 for Ingredients, 1 for Procedure


// Guna - Infinite transition to animate the flashy text color
    val infiniteTransition = rememberInfiniteTransition()
    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Transparent,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.padding(vertical = 12.dp),
                onClick = {
                    onBackPressedDispatcher?.onBackPressed()
                },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Gray
                )
            }

            IconButton(
                modifier = Modifier.padding(vertical = 12.dp),
                onClick = {
                    onBackPressedDispatcher?.onBackPressed()
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Share",
                    tint = Color(0xFF129575)
                )
            }
        }
        // Image Section with Overlays
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                //Guna - remove the following two lines so that it fit screen
                //.padding(horizontal = 12.dp)
                //.clip(RoundedCornerShape(20.dp))
        ) {
            AsyncImage(
                model = recipeData?.imageUrl,
                contentDescription = recipeData?.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(0.dp)),
                contentScale = ContentScale.Crop
            )
            // Guna -Gradient overlay to blend the image with the background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(60.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 1.0f)
                            ),
                            startY = 0f,
                            endY = 100f
                        )
                    )
            )

            // Back Button


            // Rating Badge
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd)
                    .background(
                        color = Color(0xFFFFF9C4),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    /*
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFB300),
                        modifier = Modifier.size(16.dp)
                    )*/

                    Image(
                        painter = painterResource(id = R.drawable.custom_star),
                        contentDescription = "star Icon",
                        modifier = Modifier.size(20.dp),
                        colorFilter = ColorFilter.tint(Color.Magenta)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = recipeData?.serving ?: "")
                }
            }

            // Cooking Time Badge
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd)
                    .background(
                        color = Color.White.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        //Guna
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = recipeData?.cookingTime ?: "",
                        color = animatedColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Title and Reviews
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = recipeData?.title ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            // Author Section
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Author Avatar
                /*Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                )*/

                Spacer(modifier = Modifier.width(12.dp))

                // Chef's Hat Icon
                AsyncImage(
                    model = R.drawable.chef_hat_2,
                    contentDescription = "Chef's Hat",
                    modifier = Modifier
                        .width(48.dp)
                        .height(36.dp)
                        .padding(end = 8.dp)
                )

                // chef's Name
                Text(
                    text = recipeData?.author ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                    /* Mark as favourite */
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                         Color.Transparent
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color(0xFF129575)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Favourite", color = Color(0xFF129575))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tabs
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { selectedTab = 0 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == 0) Color(0xFF129575) else Color.LightGray
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Ingredient")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { selectedTab = 1 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == 1) Color(0xFF129575) else Color.LightGray
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Procedure")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Serving Info
            if (selectedTab == 0) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(24.dp).padding(bottom = 4.dp),
                            painter = painterResource(id = R.drawable.serve_icon),
                            contentDescription = "Serve Icon"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${recipeData?.serving} Serves" ?: "",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Text(
                        text = "${recipeData?.ingridients?.size ?: 0} Items",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                when (selectedTab) {
                    0 -> {
                        // Ingredients List
                        recipeData?.ingridients?.forEach { ingredient ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFF5F5F5)
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Restaurant,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .background(Color.White, RoundedCornerShape(8.dp))
                                            .padding(8.dp)
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(
                                        text = ingredient,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight(600),
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }

                    1 -> {
                        // Procedure Steps
                        recipeData?.steps?.forEachIndexed { index, step ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFF5F5F5)
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${index + 1}",
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .background(Color.White, RoundedCornerShape(8.dp))
                                            .padding(8.dp),
                                        textAlign = TextAlign.Center,
                                        fontFamily = Poppins,
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(
                                        text = step,
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecipeDetails() {
    RecipeDetailsScreen(fakeNavController())
}
