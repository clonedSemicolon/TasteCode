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
import com.example.tastecode.data.Recipe

@Composable
fun RecipeList(
    viewModel: RecipeViewModel,
    recipeList:List<Recipe> = emptyList(),
    navHostController: NavHostController
) {
    val recipes by viewModel.recipes.observeAsState(emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(recipeList) { recipe ->
            RecipeItem(
                recipe = recipe,
                onClick = {
                    SharedData.recipe = recipe
                    navHostController.navigate(Screen.RecipeDetailsScreen.route)
                }
            )
        }
    }
}