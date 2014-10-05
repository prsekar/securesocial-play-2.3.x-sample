package controllers.security

import securesocial.controllers.ProviderControllerHelper
import securesocial.core.providers.UsernamePasswordProvider
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import securesocial.core._
import securesocial.core.utils._
import Play.current

/**
 * Created by psekar on 10/3/14.
 */
trait CustomLoginPage[U] extends SecureSocial[U] {
  private val logger = play.api.Logger("controller.security.CustomLoginPage")
  val onLogoutGoTo = "securesocial.onLogoutGoTo"
  /**
   *
   * Renders the login page
   * @return
   */
  def login = UserAwareAction { implicit request =>
    val to = ProviderControllerHelper.landingUrl
    if (request.user.isDefined) {
      // if the user is already logged in just redirect to the app
      logger.debug("User already logged in, skipping login page. Redirecting to %s".format(to))
      Redirect(to)
    } else {
      if (SecureSocial.enableRefererAsOriginalUrl) {
        SecureSocial.withRefererAsOriginalUrl(Ok(env.viewTemplates.getLoginPage(UsernamePasswordProvider.loginForm)))
      } else {
        Ok(env.viewTemplates.getLoginPage(UsernamePasswordProvider.loginForm))
      }
    }
  }


  /**
   * Logs out the user by clearing the credentials from the session.
   * The browser is redirected either to the login page or to the page specified in the onLogoutGoTo property.
   *
   * @return
   */
  def logout = UserAwareAction.async {
    implicit request =>
      val redirectTo = Redirect(Play.configuration.getString(onLogoutGoTo).getOrElse(env.routes.loginPageUrl))
      val result = for {
        user <- request.user
        authenticator <- request.authenticator
      } yield {
        import ExecutionContext.Implicits.global
        redirectTo.discardingAuthenticator(authenticator).map {
          _.withSession(Events.fire(new LogoutEvent(user)).getOrElse(request.session))
        }
      }
      result.getOrElse {
        Future.successful(redirectTo)
      }
  }
}
