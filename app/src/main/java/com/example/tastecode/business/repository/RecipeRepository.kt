import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tastecode.data.Recipe
import com.google.firebase.firestore.FirebaseFirestore

class RecipeRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val recipeCollection = firestore.collection("recipe_list")

    fun getRecipes(): LiveData<List<Recipe>> {
        val recipesLiveData = MutableLiveData<List<Recipe>>()
        recipeCollection.get()
            .addOnSuccessListener { result ->
                val recipes = result.map { document ->
                    document.toObject(Recipe::class.java)
                }
                recipesLiveData.value = recipes
            }
            .addOnFailureListener { exception ->
                Log.w("RecipeRepository", "Error getting documents: ", exception)
            }
        return recipesLiveData
    }
}