import TescoProductResultsJsonProtocol.TescoProductResults
import scalaj.http.Http
import spray.json._


class TescoApiHandler(val apiKey: String) {

  def getProductDetails(gtin: String): TescoProductResults ={
    val request = Http("https://dev.tescolabs.com/product/").param("gtin", gtin).header("Ocp-Apim-Subscription-Key", apiKey)
    val responseJSON = request.asString
    val response = responseJSON.body.parseJson.convertTo[TescoProductResults](TescoProductResultsJsonProtocol.tescoProductResultsFormat)
    response
  }
}
