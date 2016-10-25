package chapter05;

import chapter05.calling_from_java.Point;
import chapter05.calling_from_java.Point3D;
import chapter05.calling_from_java.IPoint;

public class TestProtocol {
    public static void main(String[] args) {
	Point point = new Point(10,1);
	//System.out.println(point.distance());

	Point3D point3d = new Point3D(1,2,3);
    }
}

