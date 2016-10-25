package chapter05;

public class CheckedException {
    public static void main(String args[])  {
	try {
	    Thread.sleep(10000);
	} catch (InterruptedException e) {
	    System.out.println("Interrupt occured");
	}
    }
}
