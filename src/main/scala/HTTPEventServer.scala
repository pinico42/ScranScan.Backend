import EdamamRecipeResultsJsonProtocol.{Recipe, RecipeResults}
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.io.StdIn

class HTTPEventServer(port: Int = 7070) {
  object callbacks {
    var gtinSearch: (String) => RecipeResults = (s: String)=>{null}
  }

  def setGTINSearchCallback(gtinSearchCallback: (String) => RecipeResults): Unit ={
    this.callbacks.gtinSearch = gtinSearchCallback
  }

  def recipesToJSON(r: RecipeResults): String = r.toJson.prettyPrint

  def serve() {
    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val route =
      parameters('gtin) { (gtin) =>
        complete(HttpEntity(ContentTypes.`application/json`, recipesToJSON(this.callbacks.gtinSearch(gtin))))
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", port)

    println(s"Server online at http://localhost:"+port+"/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
