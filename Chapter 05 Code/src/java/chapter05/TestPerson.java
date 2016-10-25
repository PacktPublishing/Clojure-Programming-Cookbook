package chapter05;

import cljbook.chapter05.Person;

public class TestPerson {
    public static void main(String[] args) {
	Person p1 = new Person();
	p1.setName("Makoto");
	p1.setCountry("Japan");
	System.out.println(p1.getName() + " from " + p1.getCountry());

	Person p2 = new Person("Nico","France");
	System.out.println(p2.getName() + " from " + p2.getCountry());
    }
}
