import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun RecipeList(viewModel: RecipeViewModel = viewModel(factory = RecipeViewModelFactory(RecipeRepository()))) {
    val recipes by viewModel.recipes.observeAsState(emptyList())

    LazyColumn {
        items(recipes) { recipe ->
            RecipeItem(recipe)
        }
    }
}