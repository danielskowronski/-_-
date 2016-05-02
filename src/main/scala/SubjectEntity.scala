import slick.driver.MySQLDriver.api._
import slick.lifted.Tag


class SubjectEntity(tag: Tag) extends Table[(Int, String, String)](tag, "SUBJECTS") {

  val id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  val usosID = column[String]("USOS_CODE")
  val subjectName = column[String]("SUBJECT_NAME")

  def * = (id, usosID, subjectName)

}
