package chapter05

import Math.{sqrt, pow}
import collection.JavaConversions._

trait Position {
  var x: Double
  var y: Double
}
trait MyPosition extends Position {
  def distance () {
    sqrt(pow(x,2) + pow(y,2))
  }
}


