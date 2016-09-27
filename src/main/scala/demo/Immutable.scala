package demo

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Immutable extends App {
  // immutable by default
  case class Person(name: String, surname: String, age: Int)

  val me = Person("adam", "warski", 33)
  val olderMe = me.copy(age = me.age + 1)

  println(me)
  println(olderMe)

  // collections are immutable by default
  val adams = Set(me, olderMe) // Map, List, ...
  val adamss = adams + olderMe.copy(age = olderMe.age + 1)

  println(adams)
  println(adamss)

  // can be freely passed around threads
  println(Thread.currentThread().getId)
  Future {
    println(Thread.currentThread().getId)
    println(me.copy(name = "staś", age = 3))
  }

  Thread.sleep(1000L)
}
