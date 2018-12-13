import EdamamRecipeResultsJsonProtocol.{Nutrient, PercentageNutrient, Recipe}

object FeatureTester {
  def main(args: Array[String]): Unit ={
    println("Hello World!")
    val tescoApiHandler = new TescoApiHandler("8a9cf169a53e490282c332a17f40e8eb")
    println(tescoApiHandler.getProductDetails("5000111044226").products(0).description)
    val edamamApiHandler = new EdamamApiHandler("5765ff61","772e1cf28936db40c7ff4db6aefd73a4")
    println(edamamApiHandler.getProductDetails("carrots").hits(0).recipe.serves)

    val httpEventServer = new HTTPEventServer()
    httpEventServer.setGTINSearchCallback((gtin: String) => {
      edamamApiHandler.getProductDetails("carrots")
    })
    httpEventServer.serve()
  }
}
