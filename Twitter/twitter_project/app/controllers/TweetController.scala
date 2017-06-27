package controllers

import javax.inject.Inject

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.i18n.{I18nSupport, MessagesApi}
import slick.dbio.DBIO
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import play.api.mvc.Action
import play.api.mvc.Controller

import scala.concurrent.ExecutionContext

/**
  * Created by natsumi.sawa on 2017/06/27.
  *
  */
class TweetController @Inject()(val dbConfigProvider: DatabaseConfigProvider,
                                val messagesApi: MessagesApi,
                                implicit val ec: ExecutionContext) extends Controller
    with HasDatabaseConfigProvider[JdbcProfile] with I18nSupport {

    /**
      * 一覧表示
      */

    def insertTweets: DBIO[Int] =
    sqlu"""INSERT INTO TWEET (TWEET_ID,USER_ID, CONTENT, IMAGE, DATE) VALUES ('1','1','hello', '0000', '2017/06/27');"""

  def list = Action.async {
    db.run(insertTweets).map(_=> Ok)
  }

//    def list = Action.async { implicit rs =>
//      // IDの昇順にすべてのユーザ情報を取得
//      db.run(Tables.tweets.sortBy(t => t.id).result).map { tweets =>
//        // 一覧画面を表示
//        Ok(views.tweet.list(tweets))
//      }
//    }

    /**
      * 登録実行
      */
    def create = TODO

}
