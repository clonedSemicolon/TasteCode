import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {
    @GET("food/ingredients/autocomplete")
    suspend fun getIngredientId(
        @Query("query") name: String,
        @Query("number") number: Int = 1,
        @Query("apiKey") apiKey: String
    ): List<IngredientResponse>
}

data class IngredientResponse(
    val id: Int,
    val name: String,
    val image: String
)

object RetrofitInstance {
    private const val BASE_URL = "https://api.spoonacular.com/"

    val api: SpoonacularApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpoonacularApi::class.java)
    }
}

suspend fun getIngredientImage(name: String): String? {
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.api.getIngredientId(name, apiKey = "83b59679e9b94cd1bb59069f5fcbee38")
            response.firstOrNull()?.image
        } catch (e: Exception) {
            null
        }
    }
}

@Composable
fun IngredientImage(name: String) {
    var imageUrl by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(name) {
        coroutineScope.launch {
            imageUrl = getIngredientImage(name)
        }
    }

    imageUrl?.let {
        Image(
            painter = rememberAsyncImagePainter(it),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
    } ?: Text("Image not available")
}