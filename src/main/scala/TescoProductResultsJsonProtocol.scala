import TescoProductResultsJsonProtocol.jsonFormat4
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

object TescoProductResultsJsonProtocol extends DefaultJsonProtocol {
  case class TescoProductResults(products: Array[TescoProductDetails])
  case class TescoProductDetails(brand: String,
                                 description: String,
                                 gtin: String,
                                 pkgDimensions: Array[TescoPackageDimensions],
                                 productCharacteristics: TescoProductCharacteristics,
                                 qtyContents: TescoProductContentQuantities,
                                 tpnb: String,
                                 tpnc: String
                                )
  case class TescoPackageDimensions(depth: Float,
                                    dimensionUom: String,
                                    height: Float,
                                    no: Int,
                                    volume: Float,
                                    volumeUom: String,
                                    weight: Float,
                                    weightUom: String,
                                    width: Float
                                   )
  case class TescoProductCharacteristics(containsLoperamide: Boolean,
                                         healthScore: Int,
                                         isDrink: Boolean,
                                         isFood: Boolean,
                                         isHazardous: Boolean,
                                         isNonLiquidAnalgesic: Boolean,
                                         storageType: String)
  case class TescoProductContentQuantities(avgMeasure: String,
                                           netContents: String,
                                           quantity: Float,
                                           quantityUom: String,
                                           totalQuantity: Float)

  implicit val tescoPackageDimensionsFormat: RootJsonFormat[TescoPackageDimensions] = jsonFormat9(TescoPackageDimensions)
  implicit val tescoProductCharacteristicsFormat: RootJsonFormat[TescoProductCharacteristics] = jsonFormat7(TescoProductCharacteristics)
  implicit val tescoProductContentQuantitiesFormat: RootJsonFormat[TescoProductContentQuantities] = jsonFormat5(TescoProductContentQuantities)
  implicit val tescoProductDetailsFormat: RootJsonFormat[TescoProductDetails] = jsonFormat8(TescoProductDetails)
  implicit val tescoProductResultsFormat: RootJsonFormat[TescoProductResults] = jsonFormat1(TescoProductResults)
}
