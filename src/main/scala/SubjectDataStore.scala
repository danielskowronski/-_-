/**
  * Created by sobota on 23.04.16.
  */

import com.fasterxml.jackson.databind.JsonNode
import slick.driver.MySQLDriver.api._
import slick.jdbc.meta.MTable

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.duration.Duration

object SubjectDataStore extends App {

  val subjectQuery = TableQuery[SubjectEntity]
  val db = Database.forConfig("mysqlConf")

  val schema = subjectQuery.schema

  try {

    val subjectsData: Seq[Subject] = LoadSubjectData.load()

    val dataToSave: Seq[(Int, String, String)] = subjectsData.map { n =>

      (1, n.course_id, n.`match`)
    }

    val setup = DBIO.seq(

      MTable.getTables.map(t => {

        if (t.exists(_.name.name == subjectQuery.baseTableRow.tableName)) {

          schema.create
        }
      }),

      subjectQuery ++= dataToSave
    )

    Await.result(

      db.run(setup), Duration.Inf)


  } finally db.close

}
