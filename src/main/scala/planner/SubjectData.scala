package planner

import internalAPI.{Labolatory, Lecture}

/**
  * Created by sobota on 21.05.16.
  */
case class SubjectData(implicit s: Subject) {

  def labolatoryTime: Seq[Labolatory] = {

    //load data

    Seq(Labolatory(System.currentTimeMillis(), System.currentTimeMillis() + 3600000))
  }

  def lectureTime: Lecture = {

  }

}
