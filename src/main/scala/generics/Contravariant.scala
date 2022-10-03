package generics

object Contravariant extends App {
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  class Trainer[-A]

  // val animal: Trainer[Animal] = new Trainer[Cat] // Can not do this !!!
  // val cat_0: Trainer[Cat] = new Trainer[Dog]  // Can not do this !!!
  val cat_1: Trainer[Cat] = new Trainer[Animal]
  val cat_2: Trainer[Cat] = new Trainer[Cat]
  val animals: Trainer[Animal] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A)
  val cage_01 = new Cage(new Dog)

  class Car
  // only something which is subtype of Animal can be in cage ;o)
  // val cage_02 = new Cage(new Car) // Can not do this !!!
}
