import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tastecode.business.route.Screen

@Composable
fun RecipeList(
    viewModel: RecipeViewModel = viewModel(factory = RecipeViewModelFactory(RecipeRepository())),
    navHostController: NavHostController
) {
    val recipes by viewModel.recipes.observeAsState(emptyList())

    LazyColumn {
        items(recipes) { recipe ->
            RecipeItem(
                recipe,
                onClick = {
                    SingleRecipeData.recipeData = RecipeData(
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