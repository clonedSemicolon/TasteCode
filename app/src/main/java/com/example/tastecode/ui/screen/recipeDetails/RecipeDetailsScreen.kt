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
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.tastecode.R
import com.example.tastecode.business.repository.RecipeDetailsUseCase
import com.example.tastecode.business.utilities.BusinessUtils.executeInBackground
import com.example.tastecode.data.db.RecipeDatabase
import com.example.tastecode.ui.theme.Poppins
import kotlinx.coroutines.launch

@Composable
fun RecipeDetailsScreen(navController: NavController) {
    val recipeData = SharedData.recipe
    var selectedTab by remember { mutableIntStateOf(0) }
    val onBackPressedDispatcher =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher// 0 for Ingredients, 1 for Procedure
    var isFavourite  by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Infinite transition to animate the flashy text color
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
                .height(300.dp) // Increased height to give more space for image stretching // Added top padding to move the image slightly down
        ) {
            AsyncImage(
                model = recipeData?.image,
                contentDescription = recipeData?.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight() // Ensures the image stretches to the full height of the container
                    .clip(RoundedCornerShape(0.dp)),
                contentScale = ContentScale.Crop // Ensures the image is cropped to maintain aspect ratio
            )
            // Gradient overlay to blend the image with the background
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

            // Cooking Time Badge (keeps its position)
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
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = recipeData?.times?.get("Cooking").toString() ?: "",
                        color = animatedColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Rating Badge (placed in front of the clock)
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd) // Aligns to the left side (front of the clock)
                    .background(
                        color = Color.Yellow,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.custom_star),
                        contentDescription = "star Icon",
                        modifier = Modifier.size(20.dp),
                        colorFilter = ColorFilter.tint(Color(0xFF129575))
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = recipeData?.serves.toString() ?: "", color = Color(0xFF129575), fontWeight = FontWeight.W500)
                }
            }
        }



        Column(
            modifier = Modifier
                .padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 32.dp) // Reduced top padding to move everything up
        ) {
            // Title and Reviews
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = recipeData?.name ?: "",
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
                Spacer(modifier = Modifier.width(12.dp))

                // Chef's Hat Icon
                AsyncImage(
                    model = R.drawable.chef_hat_2,
                    contentDescription = "Chef's Hat",
                    modifier = Modifier
                        .width(48.dp)
                        .height(36.dp)
                        .padding(end = 8.dp),
                    //colorFilter = ColorFilter.tint(Color(0xFF800080))
                )

                // Chef's Name
                Text(
                    text = recipeData?.author ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF800080)


                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        SharedData.recipe?.let {recipe ->
                            coroutineScope.launch {
                                executeInBackground({
                                    val db = RecipeDatabase.getFavRecipeDatabase(context)
                                    db.recipeDao().insertFavourite(recipe)
                                },{
                                    isFavourite = true
                                })
                            }
                        }

                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color.Transparent // Original color, not the same as rating badge
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if(isFavourite)Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            //tint = Color(0xFF129575)
                            tint = Color(0xFF800080)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Favourite", color = Color(0xFF800080))
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
                            text = "${recipeData?.serves} Serves" ?: "",
                            fontWeight = FontWeight.W600,
                            color = Color(0xFF129575),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Text(
                        text = "${recipeData?.ingredients?.size ?: 0} Items",
                        fontWeight = FontWeight.W600,
                        color = Color(0xFF129575),
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
                        recipeData?.ingredients?.forEach { ingredient ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFF129575).copy(0.2F)
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
                                        tint = Color(0xFF129575),
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
                                    containerColor = Color(0xFF129575).copy(0.2F)
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
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .background(Color(0xFF129575), RoundedCornerShape(8.dp))
                                            .padding(8.dp),
                                        textAlign = TextAlign.Center,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(
                                        text = step,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = Color.Black,
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

















