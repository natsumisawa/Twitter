package controllers

import javax.inject.Inject

import play.api.db.slick._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import slick.driver.JdbcProfile

class UserController @Inject()(val dbConfigProvider: DatabaseConfigProvider,
                               val messagesApi: MessagesApi) extends Controller
  with HasDatabaseConfigProvider[JdbcProfile] with I18nSupport {

  /**
    * 一覧表示
    */

  /**
    * 登録実行
    */
  def create = TODO

}