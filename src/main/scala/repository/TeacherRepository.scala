package repository
import scala.util.{Failure, Success}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import Connection.PostgresConnection
import model.Teacher;

object TeacherRepository {



  class Teachers(tag: Tag) extends Table[Teacher](tag, "teacher") {
    def teacherId = column[Option[Int]]("teacher_id", O.PrimaryKey, O.AutoInc)
    def education = column[String]("education")
    def qualification = column[String]("qualification")
    def experience = column[Int]("experience")
    def scheduleId = column[Int]("schedule_id")
    def salary = column[Int]("salary")
    def position = column[String]("position")
    def awards = column[String]("awards")
    def certificationId = column[Int]("certification_id")
    def attestationId = column[Int]("attestation_id")

    def * = (
      teacherId,
      education,
      qualification,
      experience,
      scheduleId,
      salary,
      position,
      awards,
      certificationId,
      attestationId
    ).mapTo[Teacher]
  }

  val teachers = TableQuery[Teachers]

  def getAllTeachers(): Future[Seq[Teacher]] =
    PostgresConnection.db.run(teachers.result)

  def getTeacherById(id: Int): Future[Option[Teacher]] =
    PostgresConnection.db.run(teachers.filter(_.teacherId === id).result.headOption)

  def addTeacher(teacher: Teacher): Future[Option[Int]] =
    PostgresConnection.db.run(teachers returning teachers.map(_.teacherId) += teacher)


  def updateTeacher(id: Int, updatedTeacher: Teacher): Future[Int] = {
    val updatedTeacherWithId = updatedTeacher.copy(teacherId = Some(id))

    PostgresConnection.db.run(teachers.filter(_.teacherId === id).update(updatedTeacherWithId)).map { rowsAffected =>
      println(s"Rows affected: $rowsAffected")
      rowsAffected
    }.recover {
      case ex =>
        println(s"Update failed: ${ex.getMessage}")
        throw ex
    }
  }
  def deleteTeacher(id: Int): Future[Int] =
    PostgresConnection.db.run(teachers.filter(_.teacherId === id).delete)


}
