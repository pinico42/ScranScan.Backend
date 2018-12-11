import EdamamRecipeResultsJsonProtocol.RecipeResults
import scalaj.http.Http
import spray.json._

class EdamamApiHandler(val appId: String, val appKey: String) {
  def getProductDetails(query: String): RecipeResults ={
    val request = Http("https://api.edamam.com/search")
      .param("q", query)
      .param("app_id", appId)
      .param("app_key", appKey)
    val responseJSON = request.asString
    val response = responseJSON.body.parseJson.convertTo[RecipeResults](EdamamRecipeResultsJsonProtocol.recipeResultsFormat)
    response
  }
}
