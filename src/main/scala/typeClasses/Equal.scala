package chrto.typeClasses.equalizer

import chrto.model.User

// Type Class
trait Equal[T] {
  def apply(a: T, b: T): Boolean
}

// Companion Object
object Equal {
  def apply[T](x: T, y: T)(implicit instance: Equal[T]): Boolean =
    instance(x, y)
}

// Implicit Type Class Instancies
object EqualierImplicitsInstanciess {
  implicit object IntEqualizer extends Equal[Int] {
    override def apply(a: Int, b: Int): Boolean = a == b
  }

  implicit object NameEquality extends Equal[User] {
    override def apply(user: User, anotherUser: User): Boolean =
      user.name == anotherUser.name
  }

  implicit object AgeEquality extends Equal[User] {
    override def apply(user: User, anotherUser: User): Boolean =
      user.age == anotherUser.age
  }
}

// Implicit conversion class
object TypeEnrichment {
  implicit class EqualEnrichment[T](value: T) {
    def ===(anotherValue: T)(implicit instance: Equal[T]): Boolean =
      instance(value, anotherValue)

    def !==(anotherValue: T)(implicit instance: Equal[T]): Boolean =
      !instance(value, anotherValue)
  }
}
