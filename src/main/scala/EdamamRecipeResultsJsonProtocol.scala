import spray.json._

object EdamamRecipeResultsJsonProtocol extends DefaultJsonProtocol {
  case class RecipeResults(
                     q: String,
                     from: Int,
                     to: Int,
                     more: Boolean,
                     count: Int,
                     hits: Array[Hit]
                     )
  case class Hit(
           recipe: Recipe,
           bookmarked: Boolean,
           bought: Boolean
           )
  case class Recipe(
              uri: String,
              label: String,
              image: String,
              source: String,
              url: String,
              shareAs: String,
              serves: Int,
              dietLabels: Array[String],
              healthLabels: Array[String],
              cautions: Array[String],
              ingredientLines: Array[String],
              ingredients: Array[Ingredient],
              calories: Double,
              totalWeight: Double,
              totalTime: Int,
              totalNutrients: Map[String, Nutrient],
              totalDaily: Map[String, PercentageNutrient],
              digest: Array[DigestAspect]
              )

  case class Ingredient(
                  text: String,
                  weight: Int
                  )
  case class Nutrient(
                label: String,
                quantity: Double,
                unit: String
                )
  case class PercentageNutrient(
                          label: String,
                          quantity: Double
                          )
  case class DigestAspect(
                    label: String,
                    tag: String,
                    total: Double,
                    hasRDI: Boolean,
                    daily: Double,
                    unit: String,
                    )
  case class DigestAspectSub(
                              label: String,
                              tag: String,
                              total: Double,
                              hasRDI: Boolean,
                              daily: Double,
                              unit: String
                            )
  implicit val digestAspectSubFormat: RootJsonFormat[DigestAspectSub] = jsonFormat6(DigestAspectSub)
  implicit val digestAspectFormat: RootJsonFormat[DigestAspect] = jsonFormat6(DigestAspect)
  implicit val percentageNutrientFormat: RootJsonFormat[PercentageNutrient] = jsonFormat2(PercentageNutrient)
  implicit val nutrientFormat: RootJsonFormat[Nutrient] = jsonFormat3(Nutrient)
  implicit val ingredientFormat: RootJsonFormat[Ingredient] = jsonFormat2(Ingredient)
  implicit val recipeFormat: JsonFormat[Recipe] = FullRecipeFormat
  implicit val hitFormat: RootJsonFormat[Hit] = jsonFormat3(Hit)
  implicit val recipeResultsFormat: RootJsonFormat[RecipeResults] = jsonFormat6(RecipeResults)

  implicit object FullRecipeFormat extends JsonFormat[Recipe]{
    override def read(json: JsValue): Recipe = {
      val fields = json.asJsObject("Recipe object expected").fields
      Recipe(
        fields("uri").convertTo[String],
        fields("label").convertTo[String],
        fields("image").convertTo[String],
        fields("source").convertTo[String],
        fields("url").convertTo[String],
        fields("shareAs").convertTo[String],
        fields("yield").convertTo[Int],
        fields("dietLabels").convertTo[Array[String]],
        fields("healthLabels").convertTo[Array[String]],
        fields("cautions").convertTo[Array[String]],
        fields("ingredientLines").convertTo[Array[String]],
        fields("ingredients").convertTo[Array[Ingredient]],
        fields("calories").convertTo[Double],
        fields("totalWeight").convertTo[Double],
        fields("totalTime").convertTo[Int],
        fields("totalNutrients").convertTo[Map[String, Nutrient]],
        fields("totalDaily").convertTo[Map[String, PercentageNutrient]],
        fields("digest").convertTo[Array[DigestAspect]]
      )
    }
    override def write(recipe: Recipe): JsValue = JsObject(
      "uri" -> recipe.uri.toJson,
      "label" -> recipe.label.toJson,
      "image" -> recipe.image.toJson,
      "source" -> recipe.source.toJson,
      "url" -> recipe.url.toJson,
      "shareAs" -> recipe.shareAs.toJson,
      "serves" -> recipe.serves.toJson,
      "dietLabels" -> recipe.dietLabels.toJson,
      "healthLabels" -> recipe.healthLabels.toJson,
      "cautions" -> recipe.cautions.toJson,
      "ingredientLines" -> recipe.ingredientLines.toJson,
      "ingredients" -> recipe.ingredients.toJson,
      "calories" -> recipe.calories.toJson,
      "totalWeight" -> recipe.totalWeight.toJson,
      "totalTime" -> recipe.totalTime.toJson,
      "totalNutrients" -> recipe.totalNutrients.toJson,
      "totalDaily" -> recipe.totalDaily.toJson,
      "digest" -> recipe.digest.toJson
    )
  }
}
