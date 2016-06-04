package userInfoDownloader

import slick.driver.MySQLDriver.api._
import slick.lifted.Tag


case class UserEntity(tag: Tag) extends Table[(Int, String, String, String, String)](tag, "USERS_INFO") {

  val id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  var indexNumber = column[String]("INDEX_NUMBER")
  var factultyCode = column[String]("FACULTY_CODE")
  var regPeriod = column[String]("REG_PERIOD")

  var registratiosnAvailable = column[String]("REG_CODES")


  def * = (id, indexNumber, factultyCode, regPeriod, registratiosnAvailable)

}
