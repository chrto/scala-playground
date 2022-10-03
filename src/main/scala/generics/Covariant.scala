package generics

object Covariant extends App {

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  class List[+A] {
    /*
      add does not work, because list can be Animal or Cat,
      if I have list of cats, and I add a dog (which is animal to),  type will not match.
     */
    // def add(element: A): List[A] = ???

    def add[B >: A](element: B): List[B] = new List[B]
    // A is a Cat
    // C is a Dog
    // B is a Animal (Cat, Dog, Animal)
    // => B is supertype of A and B is new list type
    // => you can add A, C and B


  }

  // val cats: List[Cat] = new List[Animal] // Can not do this !!!
  val list: List[Animal] = new List[Cat] // Can not do this !!!
  val animals: List[Animal] = new List[Animal]

  val cats_01 = new List[Cat] // list is list of Cats
  val cats_02 = cats_01.add(new Cat) // list is list of Cats
  val animals_03 = cats_02.add(new Dog) // type has been changed to common supertype => Animas
  val animals_04 =animals_03.add(new Animal)

    // def add[B >: Cat](element: B): List[B] = new List[B]
  // => B is superclass of Cat
}
