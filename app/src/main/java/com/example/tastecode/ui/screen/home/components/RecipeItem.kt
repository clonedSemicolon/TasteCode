import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


@Composable
fun RecipeItem(recipe: Recipe) {
//    Column {
//        Text(text = recipe.name ?: "No Name")
//        Text(text = recipe.description ?: "No Instructions")
//        // Add more UI elements as needed
//    }


    Surface(shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(12.dp)
            .clickable(onClick = {
//                navController
//                    ?.navigate("${NavRoutes.SingleRecipe.name}/${recipe.recipeId!!}"){
//                        popUpTo(navController.graph.findStartDestination().id)
//                        launchSingleTop = true
//                    }
            })

    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)) {
            AsyncImage(model = recipe.image, contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(144.dp))
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                recipe.name?.let {
                    Text(
                        it,
                        modifier= Modifier,
                        style= MaterialTheme.typography.headlineMedium)
                }
                Text("${recipe.description}")
            }
        }
    }
}