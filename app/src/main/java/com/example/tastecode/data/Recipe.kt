data class Recipe(
    var author: String? = null,
    var description: String? = null,
    var difficult: String? = null,
    var dish_type: String? = null,
    var id: String? = null,
    var image: String? = null,
    var ingredients: List<String> = emptyList(),
    var maincategory: String? = null,
    var name: String? = null,
    var nutrients: Map<String, Any>? = null,
    var rattings: Int? = null,
    var serves: Int? = null,
    var steps: List<String> = emptyList(),
    var subcategory: String? = null,
    var times: Map<String, String>? = null,
    var url: String? = null,
    var vote_count: Int? = null
)



