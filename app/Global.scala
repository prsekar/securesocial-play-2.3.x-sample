/**
 * Created by psekar on 9/29/14.
 */
import controllers.CustomRoutesService
import java.lang.reflect.Constructor
import scala.collection.immutable.ListMap
import securesocial.core.providers._
import securesocial.core.RuntimeEnvironment
import service.DemoUser
import service.DemoUser
import service.DemoUser
import service.DemoUser
import service.DemoUser
import service.DemoUser
import service.DemoUser
import service.DemoUser
import service.{ DemoUser, MyEventListener, InMemoryUserService }

object Global extends play.api.GlobalSettings {

  /**
   * The runtime environment for this sample app.
   */
  object MyRuntimeEnvironment extends RuntimeEnvironment.Default[DemoUser] {
    override lazy val routes = new CustomRoutesService()
    override lazy val userService: InMemoryUserService = new InMemoryUserService()
    override lazy val eventListeners = List(new MyEventListener())

    override lazy val providers = ListMap(
      // oauth 2 client providers
/*
      include(new FacebookProvider(routes, cacheService, oauth2ClientFor(FacebookProvider.Facebook))),
      include(new FoursquareProvider(routes, cacheService, oauth2ClientFor(FoursquareProvider.Foursquare))),
      include(new GitHubProvider(routes, cacheService, oauth2ClientFor(GitHubProvider.GitHub))),
*/
      include(new GoogleProvider(routes, cacheService, oauth2ClientFor(GoogleProvider.Google)))
/*
      include(new InstagramProvider(routes, cacheService, oauth2ClientFor(InstagramProvider.Instagram))),
      //include(new LinkedInOAuth2Provider(routes, cacheService,oauth2ClientFor(LinkedInOAuth2Provider.LinkedIn))),
      include(new VkProvider(routes, cacheService, oauth2ClientFor(VkProvider.Vk))),
      include(new DropboxProvider(routes, cacheService, oauth2ClientFor(DropboxProvider.Dropbox))),
      include(new WeiboProvider(routes, cacheService, oauth2ClientFor(WeiboProvider.Weibo))),
      // oauth 1 client providers
      include(new LinkedInProvider(routes, cacheService, oauth1ClientFor(LinkedInProvider.LinkedIn))),
      include(new TwitterProvider(routes, cacheService, oauth1ClientFor(TwitterProvider.Twitter))),
      include(new XingProvider(routes, cacheService, oauth1ClientFor(XingProvider.Xing))),
      // username password
      include(new UsernamePasswordProvider[U](userService, avatarService, viewTemplates, passwordHashers))
*/
    )
  }

  /**
   * An implementation that checks if the controller expects a RuntimeEnvironment and
   * passes the instance to it if required.
   *
   * This can be replaced by any DI framework to inject it differently.
   *
   * @param controllerClass
   * @tparam A
   * @return
   */
  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    val instance = controllerClass.getConstructors.find { c =>
      val params = c.getParameterTypes
      params.length == 1 && params(0) == classOf[RuntimeEnvironment[DemoUser]]
    }.map {
      _.asInstanceOf[Constructor[A]].newInstance(MyRuntimeEnvironment)
    }
    instance.getOrElse(super.getControllerInstance(controllerClass))
  }
}