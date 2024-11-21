data class RecipeData(
    val title:String?,
    val imageUrl:String?,
    val author:String?,
    val cookingTime:String?,
    val ingridients: List<String>,
    val steps:List<String>,
    val serving: String
)