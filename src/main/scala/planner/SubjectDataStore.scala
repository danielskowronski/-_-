package planner

import slick.driver.MySQLDriver.api._
import userInfoDownloader.{USOSwebInteractor, UserEntity}

import scala.collection.JavaConverters._
import scala.concurrent.Await
import scala.concurrent.duration.Duration


object SubjectDataStore extends App {

  val subjectQuery = TableQuery[SubjectEntity]
  val userInfoQuery = TableQuery[UserEntity]

  val db = Database.forConfig("mysqlConf")


  def acquireSubjectData() = {

    val subjectSchema = subjectQuery.schema

    try {

      val subjectsData: Seq[Subject] = LoadSubjectData.load()

      val subjectDataToSave: Seq[(Int, String, String)] = subjectsData.map { n =>

        (Math.random().toInt, n.course_id, n.`match`)
      }


      val setup = DBIO.seq(

        /**
          * If you WANT create table-> uncomment this
          */
        //        subjectSchema.create,
        subjectQuery ++= subjectDataToSave
      )

      Await.result(

        db.run(setup),
        Duration.Inf)


    }

  }

  def acquireUserInfoData(): Unit = {

    val usersInfoSchema = userInfoQuery.schema

    val usosUserDataLoader = new USOSwebInteractor()
    //load user
    val user = usosUserDataLoader.loadUserData()

    val userDataToSave: (Int, String, String, String, String) = (Math.random().toInt, user.indexNumber, user.facultyCode, user.regPeriod, user.regCodes.asScala.mkString(","))

    try {
      val setup = DBIO.seq(

        /**
          * If you WANT create table-> uncomment this
          */
        //        usersInfoSchema.create,

        userInfoQuery += userDataToSave
      )

      Await.result(

        db.run(setup),
        Duration.Inf)


    }

  }


  def close() = db.close

  /**
    * Entry POINT
    *
    * Before FIRST RUN set your dayta in following code
    *
    * @see UserCreditailsStore
    */

  acquireSubjectData()
  acquireUserInfoData()

  close()

}
