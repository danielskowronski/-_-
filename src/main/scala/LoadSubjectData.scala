import play.api.libs.json._

import scala.collection.mutable
import scala.io.Source


case class Subject(course_id: String, `match`: String)

object LoadSubjectData {

  implicit val toSubjectConverter: Reads[Subject] = Json.reads[Subject]

  private val converter: (Int, String) => JsValue = { (start, facId) =>

    val url = s"https://apps.usos.uj.edu.pl/services/courses/search?lang=pl&fac_id=$facId&num=20&start=$start"
    val data = Source.fromURL(url, "UTF-8")

    Json.parse(data.mkString)
  }

  def load(): Seq[Subject] = travelsToEnd(0, converter(_, "UJ.WMI.II"))

  private def travelsToEnd(begin: Int, d: (Int) => JsValue): Seq[Subject] = {

    val delta = 20

    var start = begin

    var jsonObject = d(start)

    val buff = mutable.Buffer[Subject]()
    var hasNext = true

    while (hasNext) {

      //      println(jsonObject)
      buff ++= (jsonObject \ "items").as[List[Subject]]

      start += delta
      jsonObject = d(start)

      hasNext = (jsonObject \ "next_page").as[Boolean]
    }

    buff
  }
}
