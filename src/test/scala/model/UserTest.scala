package chrto.model.user

import org.scalatest.wordspec.AnyWordSpec
import chrto.model.{User}
import chrto.typeClasses.htmlSerializer.{HtmlSerializer}
import chrto.typeClasses.equalizer.{Equal}

class UserTest extends AnyWordSpec {
  val joe: User = User("Joe", 32, "joe@company.com")

  "A User" when {
    "instantiated" should {
      "create new instance, if all constructor parameters has been specified" in {
        assert(joe.name === "Joe")
        assert(joe.age === 32)
        assert(joe.email === "joe@company.com")
      }
    }
  }

  "A User" when {
    "full serialized to HTML" should {
      import chrto.typeClasses.htmlSerializer.HtmlSerializerImplicitsInstanciess.{
        UserSerializer
      }
      "return full user (HtmlSerializer.serialize(user))" in {
        assert(
          HtmlSerializer.serialize(
            joe
          ) === "<div>Joe (32 yo) <a href=joe@company.com/></div>"
        )
      }

      "return full user (HtmlSerializer.apply)" in {
        assert(
          HtmlSerializer[User].serialize(
            joe
          ) === "<div>Joe (32 yo) <a href=joe@company.com/></div>"
        )
      }
      "return full user (HtmlSerializerEnrichment)" in {
        import chrto.typeClasses.htmlSerializer.TypeEnrichment.{
          HtmlSerializerEnrichment
        }
        // new HtmlSerializerEnrichment[User](joe).toHtml(UserSerializer)
        assert(
          joe.toHtml === "<div>Joe (32 yo) <a href=joe@company.com/></div>"
        )
      }
    }

    "partial serialized to HTML" should {
      import chrto.typeClasses.htmlSerializer.HtmlSerializerImplicitsInstanciess.{
        PartialUserSerializer
      }
      "return partial user (HtmlSerializer.serialize(user))" in {
        assert(
          HtmlSerializer.serialize(joe) === "<div>Joe</div>"
        )
      }

      "return partial user (HtmlSerializer.apply)" in {
        assert(HtmlSerializer[User].serialize(joe) === "<div>Joe</div>")
      }

      "return partial user (HtmlSerializerEnrichment)" in {
        import chrto.typeClasses.htmlSerializer.TypeEnrichment.{
          HtmlSerializerEnrichment
        }
        // new HtmlSerializerEnrichment[User](joe).toHtml(PartialUserSerializer)
        assert(joe.toHtml === "<div>Joe</div>")
      }
    }
  }

  "A User" when {
    "compare by name (Instantiate by Equal companion object -> Equal.apply)" should {
      import chrto.typeClasses.equalizer.EqualierImplicitsInstanciess.{NameEquality}
      "return true, if two users have same name (Equal.apply)" in {
        val anotherJoe: User = User("Joe", 45, "anotherJoe@comp.com")
        assert(Equal(joe, anotherJoe))
      }

      "return false, if two users have diferent name" in {
        val john: User = User("John", 23, "john@comp.com")
        assert(!Equal(joe, john))
      }
    }

    "triple equality by name" should {
      import chrto.typeClasses.equalizer.EqualierImplicitsInstanciess.{
        NameEquality
      }
      "return true, if two users have same name (EqualEnrichment)" in {
        /*
         * should be same as joe === anotherJoe, but does not work, because of

         * type mismatch;
         * found   : UserTest.this.joe.type (with underlying type chrto.model.user.User)
         * required: ?{def ===(x$1: ? >: chrto.model.user.User): ?}
         * Note that implicit conversions are not applicable because they are ambiguous:
         * both method convertToEqualizer in trait TripleEquals of type [T](left: T): UserTest.this.Equalizer[T]
         * and method EqualEnrichment in object TypeEnrichment of type [T](value: T): chrto.typeClasses.equalizer.TypeEnrichment.EqualEnrichment[T]
         * are possible conversion functions from UserTest.this.joe.type to ?{def ===(x$1: ? >: chrto.model.user.User): ?}bloop
         */
        //
        import chrto.typeClasses.equalizer.TypeEnrichment.{EqualEnrichment}
        val anotherJoe: User = User("Joe", 45, "anotherJoe@comp.com")
        assert(
          new EqualEnrichment[User](joe).===(anotherJoe)
        )
      }
    }

    "triple no equality by name" should {
      import chrto.typeClasses.equalizer.EqualierImplicitsInstanciess.{
        NameEquality
      }
      "return false, if two users have same name (EqualEnrichment)" in {
        /*
         * should be same as joe !== anotherJoe, but does not work, because of

         * type mismatch;
         * found   : UserTest.this.joe.type (with underlying type chrto.model.user.User)
         * required: ?{def ===(x$1: ? >: chrto.model.user.User): ?}
         * Note that implicit conversions are not applicable because they are ambiguous:
         * both method convertToEqualizer in trait TripleEquals of type [T](left: T): UserTest.this.Equalizer[T]
         * and method EqualEnrichment in object TypeEnrichment of type [T](value: T): chrto.typeClasses.equalizer.TypeEnrichment.EqualEnrichment[T]
         * are possible conversion functions from UserTest.this.joe.type to ?{def ===(x$1: ? >: chrto.model.user.User): ?}bloop
         */
        //
        import chrto.typeClasses.equalizer.TypeEnrichment.{EqualEnrichment}
        val anotherJoe: User = User("Joe", 45, "anotherJoe@comp.com")
        assert(
          !new EqualEnrichment[User](joe).!==(anotherJoe)
        )
      }
    }

    "compare by age (Instantiate by Equal companion object -> Equal.apply)" should {
      import chrto.typeClasses.equalizer.EqualierImplicitsInstanciess.{
        AgeEquality
      }
      "return true, if two users have same age (Equal.apply)" in {
        val john: User = User("John", 32, "john@comp.com")
        assert(Equal(joe, john))
      }

      "return false, if two users have diferent age" in {
        val john: User = User("John", 23, "john@comp.com")
        assert(!Equal(joe, john))
      }
    }
  }
}
