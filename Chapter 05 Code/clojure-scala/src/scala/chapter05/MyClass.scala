package chapter05
import scala.beans.BeanProperty

class Student {
  var name  = new String("")
  var address  = new String("")
  }
class Person {
  @BeanProperty var name : String = _
  @BeanProperty var address  : String = _
}


