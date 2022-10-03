package generics

object Invariant extends App {

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  class List[A] {
    // def test_S[S >: A](s: S): List[S] = s.getClass().getName()
    def test_A[A](a: A) = a.getClass().getName()
  }
//   val list: List[Animal] = new List[Cat] // Can not do this !!!
//   val cats: List[Cat] = new List[Animal] // Can not do this !!!
  val animals: List[Animal] = new List[Animal]

  val x = new List[Cat]
  println(x.test_A(new Cat))
  // println(x.test_S(new Cat))
}
