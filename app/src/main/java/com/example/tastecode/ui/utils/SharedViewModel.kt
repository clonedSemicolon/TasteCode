import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.tastecode.data.User

object SharedData {
    var recipeData: RecipeData? by mutableStateOf(null)
    var userData:User? by mutableStateOf(null)
}