import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

object SingleRecipeData {
    var recipeData: RecipeData? by mutableStateOf(null)
}