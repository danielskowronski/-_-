package internalAPI

import planner._

import scala.collection.mutable.ArrayBuffer

/**
  * Created by sobota on 21.05.16.
  */
class PlanGenerator {

  import planner.SubjectData

  def generate(pref: GlobalPrefs): Plan = {

    val prefs = SubjectsPreferenceLoader.loadPrefernces

    prefs.map(n => mapPropertiesToSubject(n, pref))

    Plan()
  }

  /**
    * Adjust Timetable to global option
    *
    */

  val mapPropertiesToSubject = { (n: Seq[SubjectProperties], g: GlobalPrefs) =>

    val out = ArrayBuffer[]()

    n.map { x =>

      val subject = SubjectLoader.loadSubjectByName(x.name)


      val lab = SubjectData(subject).labolatoryTime
      val lecture = SubjectData(subject).lectureTime

      g match {

        case GlobalPrefs(None, None, None) => {
          //without any restrictions

          out +=

        }

        case GlobalPrefs(day, None, None) => {
          //restrict only empty day


        }

        case GlobalPrefs(day, start, None) => {
          //restrict starttime and empty day


        }

        case GlobalPrefs(day, start, end) => {
          //restrict starttime, endtime and empty day


        }

      }

    }


  }

  val generateTimeTable = {


  }

}

case class GlobalPrefs(emptyDay: Option[Day], minHour: Option[Int], maxHour: Option[Int])

object SubjectsPreferenceLoader {

  /**
    * Acquire user preferneces for all subjects
    * @return
    */
  def loadPrefernces: Seq[SubjectProperties] = {

  }

}

object SubjectLoader {

  def loadSubjectByName(name: String): Subject = {

    //load from db
  }

}

case class Lecture(startTime: Long, endTime: Long, ommit: Boolean)

case class Labolatory(startTime: Long, endTime: Long)

case class SubjectProperties(name: String, lecture: Lecture, lab: Labolatory)


class Day extends Enumeration {

  val MON, THUS, WEND, THURS, FRID, SAT, SUN = values

}

case class Plan(subjects: Seq[SubjectProperties])


