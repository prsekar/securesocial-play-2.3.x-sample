package controllers.security

import securesocial.controllers.{ChangeInfo, RegistrationInfo, ViewTemplates}
import play.api.mvc.{RequestHeader, Controller}
import play.api.data.Form
import play.api.i18n._
import play.api.templates._
import securesocial.core.RuntimeEnvironment

/**
 * Created by psekar on 10/3/14.
 */
class CustomLoginTemplates(env: RuntimeEnvironment[_]) extends ViewTemplates {
  implicit val implicitEnv = env
    /**
     * Returns the html for the login page
     */
    def getLoginPage(form: Form[(String, String)], msg: Option[String] = None)(implicit request: RequestHeader, lang: Lang): Html = {
      views.html.custom.login(form, msg)(request, lang, env)
    }

  /**
   * Returns the html for the signup page
   */
  def getSignUpPage(form: Form[RegistrationInfo], token: String)(implicit request: RequestHeader, lang: play.api.i18n.Lang): Html = ???

  /**
   * Returns the html for the start signup page
   */
  def getStartSignUpPage(form: Form[String])(implicit request: RequestHeader, lang: Lang): Html = ???

  /**
   * Returns the html for the reset password page
   */
  def getResetPasswordPage(form: Form[(String, String)], token: String)(implicit request: RequestHeader, lang: Lang): Html = ???

  /**
   * Returns the html for the start reset page
   */
  def getStartResetPasswordPage(form: Form[String])(implicit request: RequestHeader, lang: Lang): Html = ???

  /**
   * Returns the html for the change password page
   */
  def getPasswordChangePage(form: Form[ChangeInfo])(implicit request: RequestHeader, lang: Lang): Html  = ???

  /**
   * Returns the html for the not authorized page
   */
  def getNotAuthorizedPage(implicit request: RequestHeader, lang: Lang): Html = ???
}
