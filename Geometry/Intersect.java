package geo;

public class Intersect {
	
	//whether segments p1p2 and p3p4 intersect
	public static boolean doIntersect(Point p1, Point p2, Point p3, Point p4){
		double d1 = direction(p3,p4,p1);
		double d2 = direction(p3,p4,p2);
		double d3 = direction(p1,p2,p3);
		double d4 = direction(p1,p3,p4);
		if(((d1 < 0 && d2 > 0) || (d1 > 0 && d2 < 0)) && 
		   ((d3 < 0 && d4 > 0) || (d3 > 0 && d4 < 0))) 
			return true;
		else if(d1 == 0) return onSegment(p3,p4,p1);
		else if(d2 == 0) return onSegment(p3,p4,p2);
		else if(d3 == 0) return onSegment(p1,p2,p3);
		else if(d4 == 0) return onSegment(p1,p2,p4);
		else return false;
	}
	
	
	/*the direction p0p1 rotate to p0p2
	  clockwise: positive
	  counterclockwise: negative
	*/
	public static double direction(Point p0, Point p1, Point p2){
		return (p1.x - p0.x)*(p2.y-p0.y) - (p1.y-p0.y)*(p2.x - p0.x);
	}
	
	//if p0 is on segment p1p2  
	//!!! Assumes p0 is colinear with p1p2 (on the line)
	private static boolean onSegment(Point p1, Point p2, Point p0){
		return p0.x <= Math.max(p1.x, p2.x) && p0.x >= Math.min(p1.x, p2.x) &&
			   p0.y <= Math.max(p1.y, p2.y) && p0.y >= Math.min(p1.y, p2.y);	
	}
}

class Point{
	double x;
	double y;
	Point(double x, double y){
		this.x = x;
		this.y = y;
	}
}