import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import play.api.libs.json._

import scala.collection.mutable
import scala.io.Source


case class Subject(course_id: String, `match`: String)

object LoadSubjectData {


  private val converter: (Int) => JsValue = { start =>

    val url = s"https://apps.usos.uj.edu.pl/services/courses/search?lang=pl&fac_id=UJ.WMI.II&num=20&start=$start"
    val data = Source.fromURL(url, "UTF-8")

    Json.parse(data.mkString)
  }
  /**
    * Produce flat objects structure
    */
  val flatObjects: Seq[JsonNode] => Seq[Subject] = { n =>

    implicit val toSubjectConveter: Reads[Subject] = Json.reads[Subject]

    n.map(x => x.as[List[Subject]] )

    val subjects = (n \ "items").as[List[Subject]]


    subjects
  }

  def load(): Seq[Subject] = flatObjects(travelseToEnd(0, converter))

  private def travelseToEnd(begin: Int, d: (Int) => JsValue): Seq[JsonNode] = {

    val delta = 20

    var start = begin

    val jsonObject = d(start)
    var goToNext = jsonObject.findValue("next_page").booleanValue()
    jsonObject.

    val buff = mutable.Buffer[JsonNode]()

    while (goToNext) {

      start += delta

      val obj = d(start)
      goToNext = obj.findValue("next_page").booleanValue()

      buff += obj
    }

    buff
  }
}
