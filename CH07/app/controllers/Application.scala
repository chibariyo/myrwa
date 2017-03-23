package controllers

import javax.inject.Inject
import play.api.db._
import play.api.i18n.{I18nSupport, MessagesApi}
import org.jooq.{DSLContext, SQLDialect}
import org.jooq.impl.DSL
import generated.Tables._
import generated.tables.records._
import play.api.mvc._

class Application @Inject() (val db: Database, val messagesApi: MessagesApi) extends Controller with I18nSupport {
  def login = Action { implicit request =>
    db.withConnection { connection =>
      val sql: DSLContext =
        DSL.using(connection, SQLDialect.POSTGRES_9_4)
      val users = sql.selectFrom[UserRecord](USER).fetch()
      Ok(users.toString)
    }
  }
}
