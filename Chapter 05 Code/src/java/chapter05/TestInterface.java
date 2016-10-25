package chapter05;

import chapter05.calling_from_java.MyInt;

public class TestInterface {
    public static void main(String[] args) {
	MyInt myInt = new MyInt(15);
	System.out.println("add result = " + myInt.add(30));
	System.out.println("sub result = " + myInt.sub(11));
	System.out.println("x = " + myInt.getX());
	myInt.setX(10);
	System.out.println("x = " + myInt.getX());
    }
}
