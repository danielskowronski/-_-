package internalAPI

import org.scalatra._

/**
  * Created by sobota on 21.05.16.
  */
class PlanPreferencesController extends ScalatraServlet{


  get("/") {
    <h1>Hello, world!</h1>
  }

  override protected def routeBasePath(implicit request: Any): String = ???

  override def requestPath(implicit request: Any): String = ???

  override protected implicit def configWrapper(config: Any): Config = ???
}
