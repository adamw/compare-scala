package demo

object Typeclass extends App {
  trait Functor[F[_]] { // type constructor
    def map[A, B](c: F[A])(f: A => B): F[B]
  }

  // implicit objects
  implicit object OptionFunctor extends Functor[Option] {
    override def map[A, B](c: Option[A])(f: (A) => B) = c.map(f)
  }
  implicit object ListFunctor extends Functor[List] {
    override def map[A, B](c: List[A])(f: (A) => B) = c.map(f)
  }

  // no need to implement a common trait
  case class Labeled[T](name: String, value: T)
  implicit object LabeledFunctor extends Functor[Labeled] {
    override def map[A, B](c: Labeled[A])(f: (A) => B) = c.copy(value = f(c.value))
  }

  // ---

  // generic function - operates on any container
  def add10[F[_]](c: F[Int])(implicit f: Functor[F]) = f.map(c)(_ + 10)

  println(add10(Option(5)))
  println(add10(List(1, 2, 3)))
  println(add10(Labeled("x", 4)))
}
