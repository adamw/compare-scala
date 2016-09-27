package demo

object Patterns extends App {
  // sealed - compile-time check
  // Algebraic Data Types: focus on functions operation on data, as opposed to OO
  sealed trait Loco
  case class ElectricLoco(voltage: Int) extends Loco
  case object DieselLoco extends Loco

  sealed trait Vehicle
  case class Car(name: String) extends Vehicle
  case class Train(loco: Loco, cars: Int) extends Vehicle

  val v: Vehicle = Train(ElectricLoco(25000), 20)

  // everything is an expression: match
  val desc = v match {
    case Train(ElectricLoco(voltage), cars) if cars > 1 => s"Multi-car electric (${voltage}V) train"
    case Train(DieselLoco, _) => "Diesel train"
    case Car(name) => s"Car: $name"
  }

  println(desc)
}
