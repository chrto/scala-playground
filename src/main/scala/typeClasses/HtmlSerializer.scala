package chrto.typeClasses.htmlSerializer

import chrto.model.User

// Type Class
trait HtmlSerializer[T] {
  def serialize(value: T): String
}

// Companion Object
object HtmlSerializer {
  def serialize[T](value: T)(implicit serializer: HtmlSerializer[T]): String =
    serializer.serialize(value)

  def apply[T](implicit instance: HtmlSerializer[T]): HtmlSerializer[T] =
    instance
}

// Implicit Type Class Instancies
object HtmlSerializerImplicitsInstanciess {
  implicit object IntSerializer extends HtmlSerializer[Int] {
    override def serialize(value: Int): String = s"<div>$value</div>"
  }

  implicit object UserSerializer extends HtmlSerializer[User] {
    override def serialize(user: User): String =
      s"<div>${user.name} (${user.age} yo) <a href=${user.email}/></div>"
  }

  implicit object PartialUserSerializer extends HtmlSerializer[User] {
    override def serialize(user: User): String =
      s"<div>${user.name}</div>"
  }
}

// I mplicit conversion class
object TypeEnrichment {
  implicit class HtmlSerializerEnrichment[T](value: T) {
    def toHtml(implicit instance: HtmlSerializer[T]): String =
      instance.serialize(value)
  }
}

// // This is context bound
// // not sure about structure, just for example how to do it..
//   import TypeEnrichment.{HtmlSerializerEnrichment}

//   def htmlBoilerplate[T](content: T)(implicit
//       instance: HtmlSerializer[T]
//   ): String =
//     s"<html><body>${content.toHtml(instance)}</body></html>"

//   def htmlBoilerplateSugar(T: HtmlSerializer)(content: T): String =
//     s"<html><body>${content.toHtml}</body></html>"
//   // OR
//   def htmlBoilerplateSugar(T: HtmlSerializer)(content: T): String = {
//     val instance: HtmlSerializer[T] = implicitly[HtmlSerializer[T]]
//     s"<html><body>${content.toHtml(instance)}</body></html>"
//   }
