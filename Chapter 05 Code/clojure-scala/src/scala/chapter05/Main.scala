package chapter05

object Main {
 def hello(txt: String) = {
   "hello from Scala: " + txt + " !"
 }
}

class Calculation {
  def factorial(n: BigInt): BigInt = {  
    if (n <= 1) 1  else  n * factorial(n - 1)
  }
  def add(a:Int, b:Int):Int = a + b
}

class MyTuple {
  def tuple(x:Int, y:Int) = (x,y) 
}
