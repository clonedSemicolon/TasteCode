import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.tastecode.data.Recipe
import com.example.tastecode.data.User

object SharedData {
    var recipe: Recipe? by mutableStateOf(null)
    var userData:User? by mutableStateOf(null)
}