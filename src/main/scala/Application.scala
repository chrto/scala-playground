package chrto

import model.{User}
import chrto.typeClasses.htmlSerializer.{HtmlSerializer}
import chrto.typeClasses.htmlSerializer.HtmlSerializerImplicitsInstanciess.{UserSerializer}
import chrto.typeClasses.equalizer.TypeEnrichment.{EqualEnrichment}
import chrto.typeClasses.equalizer.EqualierImplicitsInstanciess.{NameEquality}

object Application extends App {
  val john: User = User("John", 34, "john@commpany.com")
  val anotherJohn:User = User("John", 23, "john2@comp.com")
  println(HtmlSerializer.serialize(john))
  println(john !== anotherJohn)

  println(john === anotherJohn)
  println(new EqualEnrichment[User](john).===(anotherJohn)(NameEquality))

   println(
    john.===(anotherJohn)(
      chrto.typeClasses.equalizer.EqualierImplicitsInstanciess.AgeEquality
    )
  )
}
