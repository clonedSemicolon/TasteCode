import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tastecode.business.route.Screen

@Composable
fun RecipeList(
    viewModel: RecipeViewModel = viewModel(factory = RecipeViewModelFactory(RecipeRepository())),
    navHostController: NavHostController
) {
    val recipes by viewModel.recipes.observeAsState(emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(recipes) { recipe ->
            RecipeItem(
                recipe = recipe,
                onClick = {
                    SharedData.recipeData = RecipeData(
                        title = recipe.name,
                        imageUrl = recipe.image,
                        author = recipe.author,
                        cookingTime = recipe.times?.get("Cooking"),
                        ingridients = recipe.ingredients,
                        steps = recipe.steps,
                        serving = recipe.serves.toString()
                    )
                    navHostController.navigate(Screen.RecipeDetailsScreen.route)
                }
            )
        }
    }
}