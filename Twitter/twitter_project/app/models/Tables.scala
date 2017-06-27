package models

import slick.backend.DatabaseConfig
import slick.driver.H2Driver.api._
import slick.driver.JdbcProfile
import slick.lifted.TableQuery
import slick.model.Table

import scala.concurrent.ExecutionContext.Implicits.global

//object Tables extends {
//  val profile = slick.driver.H2Driver
//} with Tables

trait Tables {

//  case class TweetsColumns(id: BigInt, userId: BigInt, content: String)

//  class Tweets(tag: Tag) extends Table[(BigInt, BigInt, String)](tag, "Tweets") {
//    def id = columns[BigInt]
//    def userId = columns[BigInt]
//    def content = columns[String]
//    def * = (id, userId, content)
//  }
//
//  val tweets = TableQuery[Tweets]
//
//  val dc = DatabaseConfig.forAnnotation[JdbcProfile]
//  val db = dc.db

//  db.run(
//    DBIO.seq(
//      tweets.filter(_.id == 1).update(2)
//    )
//  )

//  print("Tweets:")
//  db.run(tweets.result).map(_.foreach {
//    case (id, userId, content) =>
//      print(id + "/" + userId + "/" + content)
//  })

//
//  lazy val schema = Users.schema
//  @deprecated("Use .schema instead of .ddl", "3.0")
//  def ddl = schema
//
//  case class UsersRow(id: BigInt, name: String, Email: String, Password: String)
//
//  implicit def GetResultUsersRow(implicit e0: GR[BigInt], e1: GR[String], e2: GR[Option[String], e3: GR[Option[String]]): GR[UsersRow] = GR{
//    prs => import prs._
//      UsersRow.tupled((<<[BigInt], <<[String], <<[String], <<[String]))
//  }

}
